package ru.job4j.forum.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.Theme;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.CommentService;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.ThemeService;
import ru.job4j.forum.service.UserService;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @MockBean
    private ThemeService themeService;

    @MockBean
    private CommentService commentService;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/theme/1/post"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("posts"));
    }

    @Test
    @WithMockUser
    public void whenLoadPostPage() throws Exception {
        Optional<Post> mockValue = Optional.of(Post.idStub(1));
        Mockito.when(postService.findById(1)).thenReturn(mockValue);
        Mockito.when(themeService.findById(1)).thenReturn(Optional.of(Theme.idStub(1)));
        Mockito.when(commentService.findByPost(mockValue.get())).thenReturn(List.of());
        this.mockMvc.perform(get("/theme/1/post/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    @Test
    @WithMockUser(username = "test")
    public void whenCreatePost() throws Exception {
        User user = new User();
        user.setUsername("test");
        Mockito.when(userService.findByUsername("test")).thenReturn(Optional.of(user));
        Theme theme = new Theme();
        theme.setName("theme");
        Mockito.when(themeService.findById(1)).thenReturn(Optional.of(theme));
        mockMvc.perform(
                post("/theme/1/post/create")
                .param("id", "0")
                .param("name", "name")
                .param("desc", "desc")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        Mockito.verify(postService).saveOrUpdate(argument.capture());
        Post out = argument.getValue();
        Assert.assertEquals("test", out.getAuthor().getUsername());
        Assert.assertEquals("theme", out.getTheme().getName());
        Assert.assertEquals(0, out.getId());
        Assert.assertEquals("name", out.getName());
        Assert.assertEquals("desc", out.getDescription());
    }

}
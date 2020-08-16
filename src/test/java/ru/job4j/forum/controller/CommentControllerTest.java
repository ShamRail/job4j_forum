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
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.CommentService;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private PostService postService;

    @MockBean
    private CommentService commentService;

    @Test
    @WithMockUser(username = "test")
    public void whenCreateComment() throws Exception {
        User user = new User();
        user.setUsername("test");
        Post post = new Post();
        post.setName("post");
        Mockito.when(userService.findByUsername("test")).thenReturn(Optional.of(user));
        Mockito.when(postService.findById(1)).thenReturn(Optional.of(post));
        mockMvc.perform(
                post("/theme/1/post/1/create")
                .param("text", "msg")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Comment> argument = ArgumentCaptor.forClass(Comment.class);
        Mockito.verify(commentService).saveOrUpdate(argument.capture());
        Comment out = argument.getValue();
        Assert.assertEquals("test", out.getAuthor().getUsername());
        Assert.assertEquals("post", out.getPost().getName());
        Assert.assertEquals("msg", out.getMessage());
    }

}
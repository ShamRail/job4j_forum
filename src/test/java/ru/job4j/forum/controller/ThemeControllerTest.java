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
import ru.job4j.forum.model.Theme;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.ThemeService;
import ru.job4j.forum.service.UserService;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class ThemeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private ThemeService themeService;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/theme/create"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit-theme"));
    }

    @Test
    @WithMockUser(username = "test")
    public void whenCreateTheme() throws Exception {
        User user = new User();
        user.setUsername("test");
        Mockito.when(userService.findByUsername("test")).thenReturn(Optional.of(user));
        this.mockMvc.perform(post("/theme/create")
                .param("name", "name")
                .param("desc", "desc")
                .param("id", "0")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Theme> argument = ArgumentCaptor.forClass(Theme.class);
        Mockito.verify(themeService).saveOrUpdate(argument.capture());
        Theme out = argument.getValue();
        Assert.assertEquals("name", out.getName());
        Assert.assertEquals("desc", out.getDescription());
        Assert.assertEquals(0, out.getId());
        Assert.assertEquals("test", out.getAuthor().getUsername());
    }


}
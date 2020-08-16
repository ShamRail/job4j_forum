package ru.job4j.forum.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repo.AuthorityRepository;
import ru.job4j.forum.service.UserService;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class RegControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthorityRepository authorityRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("reg"));
    }

    @Test
    @WithMockUser
    public void whenSuccessfullyReg() throws Exception {
        Mockito.when(userService.findByEmailOrUsername("email", "username")).thenReturn(Optional.empty());
        Authority authority = new Authority();
        authority.setAuthority("ROLE_USER");
        Mockito.when(authorityRepository.findByAuthority("ROLE_USER")).thenReturn(authority);
        Mockito.when(passwordEncoder.encode("password")).thenReturn("password");
        mockMvc.perform(
                post("/reg")
                .param("username", "username")
                .param("email", "email")
                .param("password", "password")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userService).save(argument.capture());
        User out = argument.getValue();
        Assert.assertEquals("username", out.getUsername());
        Assert.assertEquals("email", out.getEmail());
        Assert.assertEquals("password", out.getPassword());
        Assert.assertEquals("ROLE_USER", out.getAuthority().getAuthority());
    }

    @Test
    @WithMockUser(username = "username")
    public void whenUserExistsThenRedirect() throws Exception {
        User user = new User();
        Mockito.when(userService.findByEmailOrUsername("email", "username")).thenReturn(Optional.of(user));
        mockMvc.perform(
                post("/reg")
                        .param("username", "username")
                        .param("email", "email")
                        .param("password", "password")
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

}
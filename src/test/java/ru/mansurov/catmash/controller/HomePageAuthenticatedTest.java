package ru.mansurov.catmash.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.mansurov.catmash.model.Target;
import ru.mansurov.catmash.model.service.TargetServiceImpl;
import ru.mansurov.catmash.model.service.UserService;
import ru.mansurov.catmash.model.service.UserServiceImpl;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("user1")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/mashes-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/mashes-after.sql", "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class HomePageAuthenticatedTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HomePageController homePageController;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TargetServiceImpl targetService;

    @Value("${mash.min.message.length}")
    private int minMashMessage;

    @Value("${pictures.min.count}")
    private int picturesMinCount;

    @Value("${mash.min.name.length}")
    private int minMashNameLength;

    @Test
    public void mainPageTest() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='username']").string("user1"));
    }

    @Test
    public void mashesListTest() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='mashes-list']/a").nodeCount(4));
    }

    @Test
    public void addMash() throws Exception {

        MockMultipartHttpServletRequestBuilder multipartBuilder = multipart("/addMash");

        String mashName = generateNameForMash(true, minMashNameLength);

        addTargetsForMash(true, picturesMinCount, mashName, multipartBuilder);

        multipartBuilder.param("mashName", mashName)
                .param("respondentMessage", generateMessageForMash(true, minMashMessage))
                .with(csrf());

        this.mockMvc.perform(multipartBuilder)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(redirectedUrl("/"));

        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//*[@id='mashes-list']/a").nodeCount(5))
                .andExpect(xpath("//*[@id='mashes-list']/a[@id='mash_50']").exists());
    }

    @Test
    public void vote() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/mash/Мимимиметр/vote")
                .param("target", "1")
                .param("otherTarget", "4")
                .with(csrf());

        this.mockMvc.perform(requestBuilder);

        Target target = targetService.getTargetById(Long.parseLong("1"));

        Assert.assertEquals(1, target.getRating());

    }

    public static String generateMessageForMash(boolean fitMessage, int minMashMessage) {
        StringBuilder sb = new StringBuilder("testMessage");
        while (sb.length() < minMashMessage) {
            sb.append("testMessage");
        }
        if (fitMessage) {
            return sb.substring(0, minMashMessage);
        }
        return sb.substring(0, minMashMessage - 1);
    }

    public static void addTargetsForMash(boolean fitCount, int minTargetsCount, String mashName,
                                         MockMultipartHttpServletRequestBuilder multipartBuilder) {
        for (int i = 0; i < minTargetsCount - 2; i++) {
            multipartBuilder.file("files", ("testTarget_" + mashName + i).getBytes());
        }
        if (fitCount) {
            multipartBuilder.file("files", ("testTarget_" + mashName + (minTargetsCount - 1)).getBytes());
            multipartBuilder.file("files", ("testTarget_" + mashName + minTargetsCount).getBytes());
        }
    }

    public static String generateNameForMash(boolean fitMessage, int minMashNameLength) {
        StringBuilder sb = new StringBuilder("testName");
        while (sb.length() < minMashNameLength) {
            sb.append("testMessage");
        }
        if (fitMessage) {
            return sb.substring(0, minMashNameLength);
        }
        return sb.substring(0, minMashNameLength - 1);
    }
}

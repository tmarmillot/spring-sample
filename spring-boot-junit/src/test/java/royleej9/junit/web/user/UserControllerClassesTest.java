package royleej9.junit.web.user;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import royleej9.junit.web.user.config.TestUserConfig;

/*-
 * @SpringBootTest /@AutoConfigureMockMvc 
 * - 사용하기 간단한 테스트 방법
 * - 테스트 대상을 제외한 전체 bean을 스캔함- 시간이 많이 걸림
 * 
 * @author royleej9
 * @param <O>
 *
 */
@Slf4j
@MapperScan("royleej9.junit.web.user")
@AutoConfigureMybatis
@EnableAutoConfiguration
@SpringBootTest(classes= {UserController.class, UserService.class})
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UserControllerClassesTest {
    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper objectMapper = new ObjectMapper();

    // @formatter:off
    final User user1 = User.builder()
                           .id("id1")
                           .name("test1")
                           .password("pwd123")
                           .createdDate(new Timestamp(System.currentTimeMillis()))
                           .build();
    // @formatter:on
    
    @Before
    public void setup() throws Exception {
        String param = objectMapper.writeValueAsString(user1);
        
        // @formatter:off
        this.mockMvc.perform(post("/users")
                    .contentType(MediaType.APPLICATION_JSON).content(param))
                    .andExpect(status().isOk())
                    .andDo(print());
        // @formatter:on
    }

    @Test
    public void testGetUsers() throws Exception {
        // when // then
        this.mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andDo(print());
    }

    @Test
    public void testGetUsersResult() throws Exception {
        // when // then
        MvcResult result = this.mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                                       .andExpect(status().isOk())
                                       .andExpect(jsonPath("$", hasSize(1)))
                                       .andDo(print())
                                       .andReturn();

        String stringResult = result.getResponse().getContentAsString();
        log.info(stringResult);

//        String param = objectMapper.writeValueAsString(users);
//        boolean doesContain = stringResult.contains(param);
//        assertTrue(doesContain);
    }
}
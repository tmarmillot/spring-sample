package royleej9.junit.web.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import lombok.extern.slf4j.Slf4j;

/**
 * 실제 데이터가 아닌 Mockito를 사용한 임의 데이터를 조회하도록 설정 
 * @author a
 *
 */
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class UserServiceBDDTest {

    // https://www.baeldung.com/mockito-annotations

//    @Rule
//    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private UserMapper userMapper;

//    @Spy
    @InjectMocks
    private UserService userService;

    final User user1 = User.builder()
            .id("id1")
            .name("test1")
            .password("pwd123")
            .createdDate(new Timestamp(System.currentTimeMillis()))
            .build();

    final User user2 = User.builder()
            .id("id2")
            .name("test2")
            .password("pwd321")
            .createdDate(new Timestamp(System.currentTimeMillis()))
            .build();

    final User user3 = User.builder()
            .id("id3")
            .name("test3")
            .password("pwd321")
            .createdDate(new Timestamp(System.currentTimeMillis()))
            .build();

    @Before
    public void before() {
        log.info("before=============================");
//        when(userMapper.getUsers(any(User.class))).thenAnswer(new Answer<List<User>>() {
//            final List<User> savedUsers = Arrays.asList(userInfo1, userInfo2);
//            @Override
//            public List<User> answer(InvocationOnMock invocation) throws Throwable {
//                User userParam = invocation.getArgument(0);                
//                return savedUsers.stream()
//                                 .filter(u -> u.getId().equals(userParam.getId()))
//                                 .collect(Collectors.toList());
//            }           
//         });
        
        // given
        final List<User> savedUsers = Arrays.asList(user1, user2);
        given(userMapper.getUsers(any(User.class))).willAnswer(invocation->{
            final User userParam = invocation.getArgument(0);                
            return savedUsers.stream()
                             .filter(u -> u.getId().equals(userParam.getId()))
                             .collect(Collectors.toList());
        });
        
        // when
        final List<User> searchedUser1 = userMapper.getUsers(user1);
        
        
        then(userMapper).should().getUsers(user1);
        assertThat(1, is(searchedUser1.size()));
        assertThat(user1.getId(), is(searchedUser1.get(0).getId()));
        
        final List<User> searchedUser2 = userMapper.getUsers(user2);
        assertThat(1, is(searchedUser2.size()));
        assertThat(user2.getId(), is(searchedUser2.get(0).getId()));
        
        final List<User> searchedUser3 = userMapper.getUsers(user3);
        assertThat(0, is(searchedUser3.size()));        
    }


    @Test
    public void testGetUserInfo1() throws Exception {
        log.info("testGetUserInfo1");

        doReturn(Arrays.asList(user1)).when(userMapper).getUsers(user1);

        final List<User> users = userService.getUsers(user1);
        assertThat(users.size(), is(1));
        assertTrue(users.get(0).getId().equals(user1.getId()));
    }

//    @Test
//    public void testGetUserAll() throws Exception {
//        log.info("testGetUserAll");
//
//        List<User> result = userService.getUsers(new User());
//        assertThat(result.size(), is(2));
//    }

    @Test
    public void testInsertUser() throws Exception {
        log.info("testInsertUser");
        
        // given
        doReturn(1).when(userMapper).insertUser(any(User.class));
        
        // when
        final boolean isAdded = userService.insertUser(user3);
        
        // then
        assertTrue(isAdded);
        verify(userMapper).insertUser(user3);
    }

    @Test
    public void testInsertUserDupli() throws Exception {
        log.info("testInsertUserDupli");
        final boolean isAdded = userService.insertUser(user1);
        assertFalse(isAdded);
    }
}

package royleej9.junit.web.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {
    
    public UserController() {
        log.info("INIT UserController============================================");
    }

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public List<User> getUsers() {
        List<User> users = userService.getUsers(null);
        return users;
    }

    @GetMapping(value = "/users/{userId}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public List<User> getUsersById(@PathVariable String userId) {
        List<User> users = userService.getUsers(User.builder().id(userId).build());
        return users;
    }
}

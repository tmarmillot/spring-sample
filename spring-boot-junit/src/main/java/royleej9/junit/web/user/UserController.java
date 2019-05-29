package royleej9.junit.web.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    public List<User> getUsers() {
        List<User> users = userService.getUsers(null);

        return users;
    }

}

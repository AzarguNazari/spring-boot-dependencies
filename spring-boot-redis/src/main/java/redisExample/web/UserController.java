package redisExample.web;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redisExample.model.User;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class UserController {

    @RequestMapping("/getUser")
    @Cacheable(value="user-key")
    public User getUser() {
        User user=new User("aa@126.com", "aa", "aa123456", "aa","123");
        System.out.println("here after getting the user, it will be cachced on the kredis");
        return user;
    }


    @RequestMapping("/uid")
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }
}
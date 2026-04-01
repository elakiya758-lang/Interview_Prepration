package t.com.libary.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import t.com.libary.BookService.Userservice;
import t.com.libary.Entity.User;

import java.util.List;

@RestController
@RequestMapping("/users")
public class Usercontroller {

    @Autowired
    private Userservice userService;

    @GetMapping("/login")
    public List<User> getallusers() {
        return userService.getallusers();
    }

    @PostMapping("/adduser")
    public User createuser(@RequestBody User u) {
        u.setUserid(0);
        return userService.saveuser(u);
    }

    @PutMapping("/{id}")
    public User updateuser(@PathVariable("id") int userid, @RequestBody User u) {
        return userService.updateuser(userid, u);
    }

    @DeleteMapping("/{id}")
    public String deleteuser(@PathVariable("id") int userid) {
        return userService.deleteuser(userid);
    }
}
package t.com.libary.BookService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t.com.libary.BookRepository.UserRepository;
import t.com.libary.Entity.User;

import java.util.List;

@Service
public class Userservice {

    @Autowired
    private UserRepository userRepo;


    public List<User> getallusers() {
        return userRepo.findAll();
    }


    public User saveuser(User user) {
        return userRepo.save(user);
    }


    public User updateuser(int userid, User user) {
        User existing = userRepo.findById(userid)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        existing.setUsername(user.getUsername());

        return userRepo.save(existing);
    }


    public String deleteuser(int userid) {
        if (!userRepo.existsById(userid)) {
            throw new EntityNotFoundException("User not found");
        }
        userRepo.deleteById(userid);
        return "User deleted successfully";
    }
}

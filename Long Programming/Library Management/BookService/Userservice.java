
package t.com.libary.BookService;


import org.springframework.stereotype.Service;
import t.com.libary.BookRepository.UserRepository;
import t.com.libary.Entity.User;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User addUser(User u) {
        return repo.save(u);
    }

    public User updateUser(int id, User u) {
        User exists = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        exists.setUsername(u.getUsername());

        return repo.save(exists);
    }

    public String deleteUser(int id) {
        repo.deleteById(id);
        return "User deleted";
    }
}

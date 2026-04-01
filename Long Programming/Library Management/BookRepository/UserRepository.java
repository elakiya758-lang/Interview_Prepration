
package t.com.libary.BookRepository;


import org.springframework.data.jpa.repository.JpaRepository;
import t.com.libary.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}

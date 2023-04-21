package lk.notes.app.repo;

import lk.notes.app.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User,Integer> {

    boolean existsUserByEmail(String email);

    User getUsersByEmailAndPassword(String email,String password);
}

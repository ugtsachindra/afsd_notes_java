package lk.notes.app.dao;

import lk.notes.app.model.User;
import lk.notes.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDao {

    @Autowired
    UserRepo userRepo;

    public boolean saveUser(User user){
        boolean status;
        User savedUser = null;
        try {
            savedUser = userRepo.save(user);
            status = true;
        }catch (Exception e){
            status=false;
        }

        return status;
    }

    public boolean checkUser(String email){
        boolean status = userRepo.existsUserByEmail(email);
        return status;
    }

    public User userLogin(String email, String password) {

        User user = null;
        try {
//            System.out.println(email);
            user = userRepo.getUsersByEmailAndPassword(email, password);

        } catch (Exception e) {
            System.out.println(e);
        }

        return user;

    }
}

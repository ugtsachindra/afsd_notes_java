package lk.notes.app.controller;

import lk.notes.app.dao.UserDao;
import lk.notes.app.dto.UserLoginDto;
import lk.notes.app.dto.UserSaveDto;
import lk.notes.app.model.User;
import lk.notes.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserDao userDao;

    @PostMapping
    public boolean saveUser(@RequestBody UserSaveDto userDto){
        boolean status = false;
        User user = new User();
        System.out.println(userDto);
        try {
            user.setEmail(userDto.getEmail());
            user.setName(userDto.getName());
            user.setPassword(userDto.getPassword());
            user.setActive(true);
            userDao.saveUser(user);
            status=true;
        }catch(Exception e){
            System.out.println(e);
            System.out.println(user);
        }

        return status;
    }

    @GetMapping("/check/{email}")
    public boolean checkUser(@PathVariable(value="email") String email){
        boolean status = false;
        try {
            status = userDao.checkUser(email);

        }catch (Exception e){
            System.out.println(e);
        }
        return status;
    }

    @PostMapping("/login")
    public ResponseEntity<User> userLogin (@RequestBody UserLoginDto loginDto){
        User user = null;
        try {
            user = userDao.userLogin(loginDto.getEmail(),loginDto.getPassword());

        }catch(Exception e){
            System.out.println(e);
        }
        if (user==null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok().body(user);
        }
    }


}

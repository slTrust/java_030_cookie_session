package com.xiedaimala.shopping_cart.web.user;

import com.xiedaimala.shopping_cart.web.user.model.User;
import com.xiedaimala.shopping_cart.web.user.model.UserDao;
import com.xiedaimala.shopping_cart.web.user.view.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserDao userDao;

    public UserController( UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * List users
     */
    @GetMapping("/users")
    public ResponseEntity<ListUserResponse> listProducts() {
        List<User> users = userDao.findAll();
        return new ResponseEntity<>(new ListUserResponse(users), HttpStatus.OK);
    }

    /**
     * Get user
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity<GetUserResponse> getProduct(@PathVariable long userId) {
        User user = userDao.getById(userId);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new GetUserResponse(user), HttpStatus.OK);
        }
    }

    /**
     * Update user
     */
    @PutMapping("/users/{userId}")
    public ResponseEntity<UpdateUserResponse> updateProduct(@PathVariable long userId, @RequestBody UpdateUserRequest updateProductRequest) {
        User user = userDao.getById(userId);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        user.setName(updateProductRequest.getName());
        user.setPassword(updateProductRequest.getPassword());
        user = userDao.save(user);

        return new ResponseEntity<>(new UpdateUserResponse(user), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        User user = new User(createUserRequest.getName(), createUserRequest.getPassword());
        User newUser = userDao.save(user);

        UserView userView = new UserView(newUser.getId(), newUser.getName());
        return new ResponseEntity<>(new CreateUserResponse(userView), HttpStatus.CREATED);
    }
}

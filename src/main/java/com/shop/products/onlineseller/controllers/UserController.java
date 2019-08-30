package com.shop.products.onlineseller.controllers;

import com.shop.products.onlineseller.models.User;
import com.shop.products.onlineseller.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path="/add/user", method = RequestMethod.POST)
    public ResponseEntity AddUser(@Valid @RequestBody User user){
        try {
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/user/id/{userId}", method = RequestMethod.GET)
    public ResponseEntity getUserId(@PathVariable Integer userId){
        User user = userRepository.findByid(userId);
        if(user == null){
            return new ResponseEntity<>("User not found ", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/user/city/{city}", method = RequestMethod.GET)
    public ResponseEntity getUserCity(@PathVariable String city){
        List<User> users = userRepository.findBycity(city);
        if(users == null){
            return new ResponseEntity<>("Users not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>( users, HttpStatus.ACCEPTED);
    }

    @RequestMapping(path = "/user/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody User user, @PathVariable Integer id){
        User existingUser = userRepository.findByid(id);
        if(existingUser == null){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        user.setId(id);
        try {
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/user/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable Integer id){
        User user = userRepository.findByid(id);
        if(user == null){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        try {
            userRepository.delete(user);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

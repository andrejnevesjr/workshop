package com.norbertoenomoto.workshop.controller;

import java.util.Optional;

import javax.validation.Valid;

import com.norbertoenomoto.workshop.dto.UserDto;
import com.norbertoenomoto.workshop.exceptions.ServiceException;
import com.norbertoenomoto.workshop.model.UserModel;
import com.norbertoenomoto.workshop.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Iterable<UserModel>> getUsers() {

        Iterable<UserModel> users = null;
        users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody @Valid UserDto userDto){
        UserModel user = userService.saveUser(userDto);

        return ResponseEntity.ok(user);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findUserById(@PathVariable("id") Integer id){

        UserModel user = userService.findUserById(id);

        return ResponseEntity.ok(user);
        
    }

    @PutMapping("{id}")
    public String updateUser(@PathVariable("id") Integer id, @RequestBody @Valid UserDto userDto) throws ServiceException{
        
        String message ="User not found on database";
                       
        if ( userService.findUserById(id) != null) {

            UserModel user = new UserModel(id, userDto.getName(), userDto.getEmail());
            userService.updateUser(user);
            message = "User Saved";
        }

        return message;
        
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable("id") Integer id, @RequestBody @Valid UserDto userDto) throws ServiceException{
        
        String message ="User not found on database";
                       
        if ( userService.findUserById(id) != null) {

            UserModel user = new UserModel(id, userDto.getName(), userDto.getEmail());
            userService.deleteUser(user);
            message = "User Deleted";
        }

        return message;
        
    }
    

}

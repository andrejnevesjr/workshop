package com.norbertoenomoto.workshop.service;

import com.norbertoenomoto.workshop.dto.UserDto;
import com.norbertoenomoto.workshop.exceptions.ServiceException;
import com.norbertoenomoto.workshop.model.UserModel;
import com.norbertoenomoto.workshop.repository.UserRepository;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<UserModel> getUsers() {
        log.info("Getting all users...");
        return userRepository.findAll();

    }

    public UserModel saveUser(UserDto userDto) {
        log.info("Saving user...");
        UserModel userModel = UserModel.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .build();

        return userRepository.save(userModel);

    }

    public UserModel findUserById(Integer id) {
        log.info("Finding user by id " + id);
        return userRepository.findById(id).orElse(null);

    }

    public UserModel updateUser(UserModel user)  throws ServiceException{

        UserModel userToUpdate = userRepository.findById(user.getId()).orElseThrow(() -> 
        {log.info("User Not found!");
         return new ServiceException("User Not found on Database!");
        });

        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());

        return userRepository.save(userToUpdate);



    }

    public void deleteUser(UserModel user)  throws ServiceException{

        UserModel userToDelete = userRepository.findById(user.getId()).orElseThrow(() -> 
        {log.info("User Not found!");
         return new ServiceException("User Not found on Database!");
        });

        userRepository.delete(userToDelete);
    
    }

}

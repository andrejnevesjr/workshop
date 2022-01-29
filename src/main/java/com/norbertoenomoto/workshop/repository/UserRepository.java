package com.norbertoenomoto.workshop.repository;

import com.norbertoenomoto.workshop.model.UserModel;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel, Integer> {
    
}

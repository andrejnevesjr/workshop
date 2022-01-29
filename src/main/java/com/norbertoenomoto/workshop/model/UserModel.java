package com.norbertoenomoto.workshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "tb_user")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @NotEmpty(message="Name is required")
    @Column(nullable = false, length=100)
    private String name;


    @NotEmpty(message="Email is required")
    @Column(nullable = false, length=100)
    private String email;
    
}

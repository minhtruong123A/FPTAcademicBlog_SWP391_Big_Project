/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8swp.fptblog.oldrestcontroller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.group8swp.fptblog.model.ResponseObj;
import com.group8swp.fptblog.model.UserDTO;
import com.group8swp.fptblog.repositories.UserRepository;

/**
 *
 * @author pc
 */
@RestController
@RequestMapping(path = "/api/userlist")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("")
    List<UserDTO> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    //userModel
    ResponseEntity<ResponseObj> findbyid(@PathVariable String id) {
        Optional<UserDTO> founduser = repository.findById(id);
        if (founduser.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObj("ok", "query product successfully", founduser));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObj("no", "cannot found user: ", founduser));
        }

    }

    @PostMapping("/add")
    ResponseEntity<ResponseObj> insertUser(@RequestBody UserDTO newUser) {
        List<UserDTO> foundUser = repository.findByUserName(newUser.getUserName().trim());
        if (foundUser.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObj("failed", "user already registetred !!!", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObj("ok", "Insert user successfully", repository.save(newUser))
        );

    }

    @PutMapping("/update/{id}")
    ResponseEntity<ResponseObj> updateUser(@RequestBody UserDTO newUser, @PathVariable String id) {
        UserDTO updateUser = repository.findById(id).map(user -> {
            user.setUserName(newUser.getUserName());
            user.setPassword(user.getPassword());
            user.setRoleId(newUser.getRoleId());
            user.setBirthdate(user.getBirthdate());
            user.setAward(user.getAward());
            user.setNumberOfPosts(user.getNumberOfPosts());
            user.setStatus(user.getStatus());
            user.setDescription(user.getDescription());
            user.setMajor(user.getMajor());
            return repository.save(user);
        }).orElseGet(() -> {
            newUser.setUserID(id);
            return repository.save(newUser);
        });
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObj("ok", "update successfully", updateUser));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<ResponseObj> deleteUser(@PathVariable String id) {
        boolean exist = repository.existsById(id);
        if (exist) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObj("ok", "delete successfully", ""));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObj("failed", "delete failed", ""));
    }

}

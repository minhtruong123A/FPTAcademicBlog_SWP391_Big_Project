/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8swp.fptblog.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.group8swp.fptblog.model.UserDTO;

/**
 *
 * @author pc
 */
public interface UserRepository extends JpaRepository<UserDTO, String> {
    List<UserDTO> findByUserName(String userName);
    List<UserDTO> findByUserID(String userID);

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.group8swp.fptblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.group8swp.fptblog.model.CommentDTO;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface CommentRepository extends JpaRepository<CommentDTO, String>{
    List<CommentDTO> findAllByStatus(int status);
}

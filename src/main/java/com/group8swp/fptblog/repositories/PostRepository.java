/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.group8swp.fptblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.group8swp.fptblog.model.PostDTO;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface PostRepository extends JpaRepository<PostDTO, Integer>{
//    extends JpaRepository<postModel, Integer>
    List<PostDTO> findByAuthor(String author);
    PostDTO findByPostId(int id);
    List<PostDTO> findBycategoryId(int categoryId);
}

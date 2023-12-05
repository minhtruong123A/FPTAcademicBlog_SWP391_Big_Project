/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.group8swp.fptblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.group8swp.fptblog.model.TagDTO;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface TagRepository extends JpaRepository<TagDTO, Integer>{
    List<TagDTO> findByTagName(String tagName);
}

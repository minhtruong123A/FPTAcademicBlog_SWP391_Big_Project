/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.group8swp.fptblog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.group8swp.fptblog.model.CategoryDTO;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface CategoryRepository extends JpaRepository<CategoryDTO, Integer>{
     List<CategoryDTO> findByCategoryName(String categoryName);
     CategoryDTO findByCategoryId(int id);
}

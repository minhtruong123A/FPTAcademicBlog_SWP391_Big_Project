/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.group8swp.fptblog.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.group8swp.fptblog.model.SubjectDTO;

/**
 *
 * @author Dell
 */
public interface SubjectRepository extends JpaRepository<SubjectDTO, Integer>{
  List<SubjectDTO> findBySubjectName(String subjectName);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8swp.fptblog.model;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "[dbo].[Subject]")
public class SubjectDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SubjectID")
    private int subjectId;
    
    @Column(name = "SubjecName")
    private String subjectName;
    
    @Column(name = "Description")
    private String description;
    
    @Column(name = "Status")
    private int status;
    
    @OneToMany
    @JoinColumn(name = "SubjectID")
    private List<PostDTO> postModel;
    
    public SubjectDTO() {
    }

    public SubjectDTO(int subjectId, String subjectName, String description, int status) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.description = description;
        this.status = status;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "subjectModel{" + "subjectId=" + subjectId + ", subjectName=" + subjectName + ", description=" + description + ", status=" + status + '}';
    }
    
    
}

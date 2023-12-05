/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8swp.fptblog.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "[dbo].[Tag]")
public class TagDTO {
    @Id
    @Column(name = "TagID")
    private int tagId;
    
    @Column(name = "TagName")
    private String tagName;
    
    @Column(name = "TagDescription")
    private String tagDescription;
    
    @Column(name = "Status")
    private int status;
    
    @ManyToMany(mappedBy = "tagModels")
    private List<PostDTO> postModel =new ArrayList<>() ;

    public TagDTO() {
    }

    public TagDTO(int tagId, String tagName, String tagDescription, int status) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.tagDescription = tagDescription;
        this.status = status;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "tagModel{" + "tagId=" + tagId + ", tagName=" + tagName + ", tagDescription=" + tagDescription + ", status=" + status + '}';
    }
    
    
    
}

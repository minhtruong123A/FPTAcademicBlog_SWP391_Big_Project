/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8swp.fptblog.model;

import java.util.List;
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
 * @author pc
 */
@Entity
@Table(name = "[dbo].[tblUser]")
public class UserDTO {

    @Id
    @Column(name = "UserID")
    private String userID;
    @Column(name = "UserName")
    private String userName;
     @Column(name = "Password")
    private String password;
    @Column(name = "RoleID")
    private String roleId;
     @Column(name = "Birthdate")
    private String birthdate;
     @Column(name = "Award")
    private int award;
     @Column(name = "NumberOfPosts")
    private int numberOfPosts;
    @Column(name = "Status")
    private int status;
     @Column(name = "Description")
    private String description;
     @Column(name = "Major")
    private String major;
     @Column(name = "Image")
    private String image;
    
    @OneToMany
    @JoinColumn(name = "UserID")
    private List<PostDTO> postModel;
    
    @OneToMany
    @JoinColumn(name = "UserID")
    private List<CommentDTO> commentModel;

    public List<PostDTO> getPostModel() {
        return postModel;
    }

    public void setPostModel(List<PostDTO> postModel) {
        this.postModel = postModel;
    }

    public List<CommentDTO> getCommentModel() {
        return commentModel;
    }

    public void setCommentModel(List<CommentDTO> commentModel) {
        this.commentModel = commentModel;
    }
    
 
    
    public UserDTO() {

    }

    public UserDTO(String userID, String userName, String password, String roleId, String birthdate, int award, int numberOfPosts, int status, String description, String major, String image) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.roleId = roleId;
        this.birthdate = birthdate;
        this.award = award;
        this.numberOfPosts = numberOfPosts;
        this.status = status;
        this.description = description;
        this.major = major;
        this.image = image;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public int getAward() {
        return award;
    }

    public void setAward(int award) {
        this.award = award;
    }

    public int getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(int numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "userID=" + userID + ", userName=" + userName + ", password=" + password + ", roleId=" + roleId + ", birthdate=" + birthdate + ", award=" + award + ", numberOfPosts=" + numberOfPosts + ", status=" + status + ", description=" + description + ", major=" + major + ", image=" + image + '}';
    }

  
}

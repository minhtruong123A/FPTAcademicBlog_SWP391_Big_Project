/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8swp.fptblog.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "[dbo].[Post]")
public class PostDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PostID")
    private int postId;

    //blog many to one category (check)
    @Column(name = "PostCategoryID")
    private int categoryId;

    //blog many to many tag
    @Column(name = "PostTagID")
    private int tagId;

    //blog many to one subject (check)
    @Column(name = "PostSubjectID")
    private int subjectId;

    //blog one to many comment (check)
    @Column(name = "PostComment")
    private String comment;

    @Column(name = "AppovedBy")
    private String approvedBy;

    //blog many to one userId (author)(check)
    @Column(name = "PostAuthor")
    private String author;

    @Column(name = "Title")
    private String title;

    @Column(name = "PostContent",length = 5000)
    private String postContent;

    @Column(name = "Award")
    private int award;

    @Column(name = "Approve")
    private int approve;

    @Column(name = "Status")
    private int status;

    @Column(name = "ApproveTime")
    private String approveTime;

    @Column(name = "Image")
    private String image;

//    @ManyToOne
//    @JoinColumn(name = "PostCategoryID", insertable = false, updatable = false)
//    private categoryModel categoryModel;
//    
//    @ManyToOne
//    @JoinColumn(name = "PostSubjectID", insertable = false, updatable = false)
//    private subjectModel subjectModel;
//    
//    @ManyToOne
//    @JoinColumn(name = "PostAuthor", insertable = false, updatable = false)
//    private userModel userModel;
    
    @OneToMany(mappedBy = "postModel")
    private List<CommentDTO> commentModel;
    
    
    //giu nguyen
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Posts_Tags",
            joinColumns = @JoinColumn(name = "PostTagID"),
            inverseJoinColumns = @JoinColumn(name = "TagID")
    )
    private List<TagDTO> tagModels =new ArrayList<>();

    public PostDTO() {
    }

    public PostDTO(int postId, int categoryId, int tagId, int subjectId, String comment, String approvedBy, String author, String title, String postContent, int award, int approve, int status, String approveTime, String image) {
        this.postId = postId;
        this.categoryId = categoryId;
        this.tagId = tagId;
        this.subjectId = subjectId;
        this.comment = comment;
        this.approvedBy = approvedBy;
        this.author = author;
        this.title = title;
        this.postContent = postContent;
        this.award = award;
        this.approve = approve;
        this.status = status;
        this.approveTime = approveTime;
        this.image = image;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public int getAward() {
        return award;
    }

    public void setAward(int award) {
        this.award = award;
    }

    public int getApprove() {
        return approve;
    }

    public void setApprove(int approve) {
        this.approve = approve;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(String approveTime) {
        this.approveTime = approveTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "postModel{" + "postId=" + postId + ", categoryId=" + categoryId + ", tagId=" + tagId + ", subjectId=" + subjectId + ", comment=" + comment + ", approvedBy=" + approvedBy + ", author=" + author + ", title=" + title + ", postContent=" + postContent + ", award=" + award + ", approve=" + approve + ", status=" + status + ", approveTime=" + approveTime + ", image=" + image + '}';
    }

}

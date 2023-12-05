/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8swp.fptblog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "[dbo].[Comment]")
public class CommentDTO {
    @Id
    @Column(name = "CommentID")
    private String commentId;
    
    @Column(name = "Author")
    private String author;
    
    @Column(name = "Context")        
    private String context;
    
    @Column(name = "PublishTime")        
    private String publishTime;
    
    @Column(name = "Status")        
    private int status;
    
    @Column(name = "Award")
    private int award;
    
    @ManyToOne
    @JoinColumn(name="PostComment")
    private PostDTO postModel;
    
    public CommentDTO() {
    }

    public CommentDTO(String commentId, String author, String context, String publishTime, int status, int award) {
        this.commentId = commentId;
        this.author = author;
        this.context = context;
        this.publishTime = publishTime;
        this.status = status;
        this.award = award;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAward() {
        return award;
    }

    public void setAward(int award) {
        this.award = award;
    }

    @Override
    public String toString() {
        return "commentModel{" + "commentID=" + commentId + ", author=" + author + ", context=" + context 
                + ", publishTime=" + publishTime + ", status=" + status + ", award=" + award + '}';
    }
}

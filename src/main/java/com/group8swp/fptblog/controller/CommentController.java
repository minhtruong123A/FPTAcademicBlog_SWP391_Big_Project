/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8swp.fptblog.controller;

import com.group8swp.fptblog.model.CommentDTO;
import com.group8swp.fptblog.model.PostDTO;
import com.group8swp.fptblog.model.UserDTO;
import com.group8swp.fptblog.repositories.CommentRepository;
import com.group8swp.fptblog.repositories.PostRepository;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author pc
 */
@Controller
public class CommentController {

    @Autowired
    private CommentRepository _commentRep;

    //comments
    @RequestMapping(value = "/comments")
    public String AddComment(@RequestParam(value = "comment") String comment, HttpSession session, Model model) {

        UserDTO user = (UserDTO) session.getAttribute("user");
        PostDTO post = (PostDTO) session.getAttribute("postSession");

        if (comment.isBlank()) {
            return "redirect:/details/" + post.getPostId(); //enter fail page
        }
        if (comment.length() > 255) {
            return "redirect:/details/" + post.getPostId();
        }

        CommentDTO newcomment = new CommentDTO();
        newcomment.setCommentId(String.valueOf(_commentRep.findAll().size() + 1));
        newcomment.setAuthor(user.getUserName());
        // because of the failure of the database, status have chosen to be used as postID's definition
        newcomment.setStatus(post.getPostId());
        newcomment.setContext(comment);

        _commentRep.save(newcomment);

        session.setAttribute("user", user);
        session.setAttribute("postSession", post);
        model.addAttribute("post", post);

        return "redirect:/details/" + post.getPostId();
    }

}

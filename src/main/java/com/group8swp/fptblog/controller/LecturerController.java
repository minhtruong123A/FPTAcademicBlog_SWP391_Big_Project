/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8swp.fptblog.controller;

import com.group8swp.fptblog.model.CategoryDTO;
import com.group8swp.fptblog.model.PostDTO;
import com.group8swp.fptblog.model.UserDTO;
import com.group8swp.fptblog.repositories.CategoryRepository;
import com.group8swp.fptblog.repositories.PostRepository;
import com.group8swp.fptblog.repositories.UserRepository;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Dell
 */
@Controller
public class LecturerController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PostRepository postRep;

    @Autowired
    private CategoryRepository categoryRep;

    @RequestMapping(value = "/approval")
    public String Approval(HttpSession session, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("user");
        List<PostDTO> post = postRep.findAll();
        Collections.reverse(post);
        model.addAttribute("post", post);
        model.addAttribute("user", user);

        List<CategoryDTO> category = categoryRep.findAll();
        Collections.reverse(category);
        model.addAttribute("category", category);

        return "aprovalblog";
    }

    @RequestMapping("/approved")
    public String ApprovedBlog(@RequestParam(value = "postId") int postId) {
        PostDTO post = postRep.findByPostId(postId);
        if (post.getStatus() != 0) {
            return "redirect:/approval";
        }

        post.setStatus(1);
        postRep.save(post);
        return "redirect:/approval";
    }
//th:if="${post.status == 1}"

    @RequestMapping("/denied")
    public String updateUserPost(@RequestParam(value = "postId") int postId) {
        PostDTO post = postRep.findByPostId(postId);
        if (post.getStatus() != 0) {
            return "redirect:/approval";
        }

        post.setStatus(-1);
        postRep.save(post);
        return "redirect:/approval";
    }
}

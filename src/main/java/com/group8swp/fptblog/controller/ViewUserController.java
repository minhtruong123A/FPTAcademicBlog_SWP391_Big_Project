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

/**
 *
 * @author Dell
 */
@Controller
public class ViewUserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PostRepository postRep;

    @Autowired
    private CategoryRepository categoryRep;

    @RequestMapping(value = "/viewauthor")
    public String viewProfile(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        model.addAttribute("user", user);

        List<PostDTO> post = postRep.findByAuthor(user.getUserName());
        Collections.reverse(post);
        model.addAttribute("post", post);

        List<CategoryDTO> category = categoryRep.findAll();
        Collections.reverse(category);
        model.addAttribute("category", category);

        List<PostDTO> getnotification = postRep.findByAuthor(user.getUserName());
        Collections.reverse(getnotification);
        model.addAttribute("getnotification", getnotification);

        return "profileaccount";
    }
}

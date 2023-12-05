/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8swp.fptblog.controller;

import com.group8swp.fptblog.model.UserDTO;
import com.group8swp.fptblog.repositories.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static org.hibernate.criterion.Projections.id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Dell
 */
@Controller
public class UpdateUserController {

    @Autowired
    private UserRepository repository;

    private boolean isPasswordValid(String password) {
        if (password.length() < 8) {
            return false;
        }

        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";
        return password.matches(regex);
    }

//<---------------------------update profile function----------------------------->    
    @RequestMapping(value = "/viewprofilee", method = RequestMethod.POST)
    public String updateuser(HttpSession session,
            @RequestParam(value = "userid") String userid,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "birthdate") String birthdate,
            @RequestParam(value = "description") String description,
            @RequestParam(value = "confirmpassword") String confirm,
            Model model) {
        boolean isValid = isPasswordValid(password);
        LocalDate parsedDate = LocalDate.parse(birthdate);
        LocalDate currentDate = LocalDate.now();

        if (!isValid) {
            model.addAttribute("passwordFormatError", "Password must have at least 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character, and be at least 8 characters long.");
            return "redirect:/viewprofile";
        }

        if (parsedDate.isAfter(currentDate)) {
            return "redirect:/viewprofile";
        }

        if (description.length() > 255) {
            return "redirect:/viewprofile";
        }

        if (password.matches(confirm)) {

            System.out.println(username);
            System.out.println(password);
            System.out.println(birthdate);
            System.out.println(description);
            List<UserDTO> founduser = repository.findByUserID(userid);
            founduser.get(0).setUserName(username);
            founduser.get(0).setPassword(password);
            founduser.get(0).setBirthdate(birthdate);
            founduser.get(0).setDescription(description);

            repository.save(founduser.get(0));

            session.setAttribute("user", founduser.get(0));

            return "redirect:/viewauthor";
        } else {
            List<UserDTO> founduser2 = repository.findByUserID(userid);
            session.setAttribute("user", founduser2.get(0));
            model.addAttribute("PasswordConfirmError", "ERROR: Confirm password is not match !");
            return "redirect:/viewprofile";
        }
    }

//<----------------------------view profile function----------------------------->            
    @RequestMapping(value = "/viewprofile")
    public String viewProfile(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        model.addAttribute("user", user);
        return "profile";
    }
}

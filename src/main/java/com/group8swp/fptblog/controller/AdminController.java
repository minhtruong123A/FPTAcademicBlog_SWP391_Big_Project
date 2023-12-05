/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8swp.fptblog.controller;

import com.group8swp.fptblog.model.CategoryDTO;
import com.group8swp.fptblog.model.CommentDTO;
import com.group8swp.fptblog.model.PostDTO;
import com.group8swp.fptblog.model.UserDTO;
import com.group8swp.fptblog.repositories.CategoryRepository;
import com.group8swp.fptblog.repositories.CommentRepository;
import com.group8swp.fptblog.repositories.PostRepository;
import com.group8swp.fptblog.repositories.SubjectRepository;
import com.group8swp.fptblog.repositories.TagRepository;
import com.group8swp.fptblog.repositories.UserRepository;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Dell
 */
@Controller
public class AdminController {

    //re update
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @RequestMapping(value = "/loginadmin")
    public String loginadmin(HttpSession session,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password) {

        String failed = "auth-login";
        String success = "redirect:/landingpage";

        String admin = "admin";
        String result = null;

        List<UserDTO> founduser = userRepository.findByUserName(username);
        if (founduser.isEmpty()) {
            result = failed;
            return result;
        }
        if (password.matches(founduser.get(0).getPassword())
                && username.equals(founduser.get(0).getUserName())
                && founduser.get(0).getRoleId().equalsIgnoreCase(admin)) {

            System.out.println("admin : " + founduser.get(0).getUserName() + "password:" + founduser.get(0).getPassword() + " login successfully ");
            session.setAttribute("user", founduser.get(0));
            result = success;
        } else {
            System.out.println("login failed");
            result = failed;
        }

        return result;

    }

    @RequestMapping(value = "/loginadmin", method = RequestMethod.GET)
    public String loginPage() {
        return "auth-login";
    }

    @RequestMapping(value = "/landingpage")
    public String viewdashboard(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        model.addAttribute("user", user);

        session.setAttribute("user", user);

        List<UserDTO> userList = userRepository.findAll();
        int countUser = userList.size();
        List<PostDTO> postList = postRepository.findAll();
        Collections.reverse(postList);
        int countPost = postList.size();
        List<CommentDTO> commentList = commentRepository.findAll();
        int countComment = commentList.size();
        List<CategoryDTO> category = categoryRepository.findAll();
        Collections.reverse(category);

        model.addAttribute("category", category);
        model.addAttribute("userList", userList);
        model.addAttribute("post", postList);
        model.addAttribute("countUser", countUser);
        model.addAttribute("countPost", countPost);
        model.addAttribute("countComment", countComment);
        return "landing_page";
    }

    @RequestMapping(value = "/landingprofile")
    public String viewadminprofile(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        session.setAttribute("user", user);
        model.addAttribute("user", user);
        return "landing_profile";
    }

    @RequestMapping(value = "/landingsystem")
    public String viewsystem(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        session.setAttribute("user", user);
        model.addAttribute("user", user);

        List<CategoryDTO> cateList = categoryRepository.findAll();
        model.addAttribute("cateList", cateList);
        return "landing_system";
    }
//--------------------------------------------------------------------------------------------------------------------

//    @RequestMapping(value = "/showuserlist")
//    public String viewuserlist(HttpServletRequest request, Model model) {
//        HttpSession session = request.getSession();
//        UserDTO user = (UserDTO) session.getAttribute("user");
//        model.addAttribute("user", user);
//        session.setAttribute("user", user);
//
//        List<UserDTO> userList = userRepository.findAll();
//        int countUser = userList.size();
//        int countPost = postRepository.findAll().size();
//        int countComment = commentRepository.findAll().size();
//
//        model.addAttribute("userList", userList);
//        model.addAttribute("countUser", countUser);
//        model.addAttribute("countPost", countPost);
//        model.addAttribute("countComment", countComment);
//
//        return "";
//    }
    @RequestMapping(value = "/ban")
    public String deactivateUser(HttpServletRequest request, Model model, @RequestParam(value = "userId") String userId) {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        session.setAttribute("user", user);
        List<UserDTO> banUser = userRepository.findByUserID(userId);
        UserDTO getBannedUser = banUser.get(0);
        getBannedUser.setStatus(0);
        userRepository.save(getBannedUser);
//        HttpSession sessionUser = (HttpSession) getBannedUser;
//        sessionUser.invalidate();
        return "redirect:/landingpage";
    }

    @RequestMapping(value = "/unban")
    public String activateUser(HttpServletRequest request, Model model, @RequestParam(value = "userId") String userId) {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        session.setAttribute("user", user);
        List<UserDTO> banUser = userRepository.findByUserID(userId);
        UserDTO getBannedUser = banUser.get(0);
        getBannedUser.setStatus(1);
        userRepository.save(getBannedUser);
        return "redirect:/landingpage";
    }

    @RequestMapping(value = "/addUserAdmin")
    public String addNewUser(HttpServletRequest request, Model model,
            @RequestParam(value = "Id") String Id,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "confirm") String confirm) {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        session.setAttribute("user", user);

        //------------------
        String lecturerEmail = "^[a-z0-9]+@fe\\.edu\\.vn$";
        String lecturerId = "^LECTURER_(SE|SA|SS|GD)$";

        if (Id.contains(" ")
                || email.contains(" ")
                || password.contains(" ")
                || confirm.contains(" ")) {
            return "redirect:/landingpage";
        }

        if (!Id.matches(lecturerId)) {
            return "redirect:/landingpage";
        }

        if (!email.matches(lecturerEmail)) {
            return "redirect:/landingpage";
        }

        if (email.length() > 255) {
            return "redirect:/landingpage";
        }

        if (password.length() > 255) {
            return "redirect:/landingpage";
        }

        List<UserDTO> foundusername = userRepository.findByUserName(email);
        if (foundusername.size() != 0) {
            return "redirect:/landingpage";
        }

        if (!password.equals(confirm)) {
            return "redirect:/landingpage";
        }
        //------------------
        UserDTO newUser = new UserDTO();
        newUser.setUserID(Id);
        newUser.setUserName(email);
        newUser.setPassword(password);
        newUser.setRoleId("Lecturer");
        newUser.setStatus(1);

        userRepository.save(newUser);

        return "redirect:/landingpage";
    }

//    @GetMapping("/chart-data")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> getChartData() {
//        Map<String, Object> chartData = new HashMap<>();
//
//        // Chart 1 data
//        Map<String, Object> chart1 = new HashMap<>();
//        chart1.put("labels", Arrays.asList("Student", "Lecturer", "Admin"));
//        List<UserDTO> foundLecturer = userRepository.findByroleId("Lecturer");
//        int lecturerNum = foundLecturer.size();
//        List<UserDTO> foundStudent = userRepository.findByroleId("Student");
//        int studentNum = foundStudent.size();
//        List<UserDTO> foundAdmin = userRepository.findByroleId("admin");
//        int AdminNum = foundAdmin.size();
//        chartData.put("data", Arrays.asList(studentNum, lecturerNum, AdminNum));
//        chart1.put("backgroundColor", Arrays.asList(
//                "rgba(54, 162, 235, 1)",
//                "rgba(255, 99, 132, 1)",
//                "rgba(255, 206, 86, 1)"
//        ));
//        chartData.put("chart1", chart1);
//
//        // Chart 2 data
//        Map<String, Object> chart2 = new HashMap<>();
//        chart2.put("labels", Arrays.asList("Student", "Lecturer", "Admin"));
//        chartData.put("data", Arrays.asList(studentNum, lecturerNum, AdminNum));
//        chart2.put("backgroundColor", Arrays.asList(
//                "rgba(54, 162, 235, 1)",
//                "rgba(255, 99, 132, 1)",
//                "rgba(255, 206, 86, 1)"
//        ));
//        chartData.put("chart2", chart2);
//
//        return ResponseEntity.ok(chartData);
//    }
    //-------------------------------------------------------------------------------------------------------------------
//     @RequestMapping(value = "/listCategoty")
//    public String listCategory(HttpServletRequest request, Model model) {
//        HttpSession session = request.getSession();
//        UserDTO user = (UserDTO) session.getAttribute("user");
//        session.setAttribute("user", user);
//        
//
//        return "";
//    }
    //--------------------------------------------Create/Update/Delete Category------------------------------------------------------
    @RequestMapping(value = "/createCategory")
    public String createCategory(HttpServletRequest request, Model model,
            @RequestParam(value = "categoryName") String categoryName,
            @RequestParam(value = "categoryDescription") String categoryDescription) {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        session.setAttribute("user", user);

        //------------------
        if (categoryName.isBlank()
                || categoryDescription.isBlank()) {
            return "redirect:/landingsystem";
        }

        if (categoryDescription.length() > 255) {
            return "redirect:/landingsystem";
        }

        if (categoryName.length() > 255) {
            return "redirect:/landingsystem";
        }

        List<CategoryDTO> foundCategory = categoryRepository.findByCategoryName(categoryName);
        if (foundCategory.size() != 0) {
            return "redirect:/landingsystem";
        }
        //------------------
        CategoryDTO newCategory = new CategoryDTO();

        newCategory.setCategoryName(categoryName);
        newCategory.setCategoryDescription(categoryDescription);
        newCategory.setStatus(1);

        categoryRepository.save(newCategory);

        return "redirect:/landingsystem";
    }

    @RequestMapping("/deleteCategory")
    public String deleteCategory(@RequestParam(value = "categoryId") int categoryId, Model model) {
        CategoryDTO deletedCategory = categoryRepository.findByCategoryId(categoryId);
        deletedCategory.setStatus(0);
        categoryRepository.save(deletedCategory);
        return "redirect:/landingsystem";
    }

    @RequestMapping(value = "/landingupdate")
    public String updateCategory(HttpServletRequest request, Model model,
            @RequestParam(value = "categoryId") int categoryId) {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        session.setAttribute("user", user);
        model.addAttribute("user", user);

        CategoryDTO updateCategory = categoryRepository.findByCategoryId(categoryId);

        model.addAttribute("updateCategory", updateCategory);
        return "landing_update";
    }

    @RequestMapping(value = "/updateCategoryDetail")
    public String updateCategoryDetail(HttpServletRequest request, Model model,
            @RequestParam(value = "categoryName") String categoryName,
            @RequestParam(value = "categoryDescription") String categoryDescription,
            @RequestParam(value = "categoryId") int categoryId) {

        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        session.setAttribute("user", user);

        //------------------
        if (categoryName.isBlank()
                || categoryDescription.isBlank()) {
            return "redirect:/landingsystem";
        }

        if (categoryDescription.length() > 255) {
            return "redirect:/landingsystem";
        }

        if (categoryName.length() > 255) {
            return "redirect:/landingsystem";
        }
        //----------------
        CategoryDTO newCategory = categoryRepository.findByCategoryId(categoryId);

        newCategory.setCategoryName(categoryName);
        newCategory.setCategoryDescription(categoryDescription);

        categoryRepository.save(newCategory);

        return "redirect:/landingsystem";
    }
}

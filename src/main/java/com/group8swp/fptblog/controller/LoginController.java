/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8swp.fptblog.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.group8swp.fptblog.model.UserDTO;
import com.group8swp.fptblog.repositories.UserRepository;

/**
 *
 * @author pc
 */
@Controller
public class LoginController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(value = "/homelogin")
    public String loginhome() {
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login(HttpSession session,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            Model model) {
        
        //-------------------------------

        String failed = "loginfailed"; // duong dan vao duoc khi login khong thanh cong
        String success = "redirect:/viewforum";
        
        //--------------------------------

        String result = null;

        List<UserDTO> founduser = repository.findByUserName(username);
        if (founduser.isEmpty()) {
            result = failed;
            return result;
        }
        String admin = "admin";
        if (admin.equalsIgnoreCase(founduser.get(0).getRoleId())) {
            result = failed;
            return result;
        }
        if (password.equals(founduser.get(0).getPassword())) {

            if (founduser.get(0).getStatus()==1) {
            System.out.println("user : " + founduser.get(0).getPassword() + " login successfully ");
            model.addAttribute("user", founduser.get(0));
            session.setAttribute("user", founduser.get(0));
            result = success;
            }
            
            if (founduser.get(0).getStatus()==0) {
                result = failed;
                session.invalidate();
                model.addAttribute("banned", "NOTE: this account has been deactiaved! please contact admin if you need");
            }
        } else {
            System.out.println("login failed");
            result = failed;
        }

        return result;

    }
//  <----------------------------------------------------------------------------------------------------->    
//    [front-end] template get file to html
//    @GetMapping("/test")
//    public String example(Model model)
//    {
//        String mess="Hello Dummy";
//        model.addAttribute("message","<h1>"+mess+"</h1>");
//        return "loginfailed";
//    }

//<------------------------------------------------------------------------------------------------------>    
    //[front-end] template get data blog (view blog function)into html    
//    @GetMapping("/items")
//    public String items(Model model) {
//        List<String> itemList1 = Arrays.asList("title1", "title2", "title3");
//        List<String> itemList2 = Arrays.asList("Item A", "Item B", "Item C");
//        List<ItemPair> itemPairs = new ArrayList<>();
//        for (int i = 0; i < itemList1.size() && i < itemList2.size(); i++) {
//            ItemPair pair = new ItemPair(itemList1.get(i), itemList2.get(i));
//            itemPairs.add(pair);
//        }
//        for (ItemPair pair : itemPairs) {
//            System.out.println("Item 1: " + pair.getItem1());
//            System.out.println("Item 2: " + pair.getItem2());
//            System.out.println();
//        }
//        model.addAttribute("itemPairs", itemPairs);
//
//        return "index";
//    }
//
//    public static class ItemPair {
//
//        private String item1;
//        private String item2;
//
//        public ItemPair(String item1, String item2) {
//            this.item1 = item1;
//            this.item2 = item2;
//        }
//
//        public String getItem1() {
//            return item1;
//        }
//
//        public String getItem2() {
//            return item2;
//        }
//    }
//    [front-end] tag th:each th:text in html run for template get data blog
//    <div>
//    <h1>Combined Items:</h1>
//    <div th:each="pair : ${itemPairs}">
//        <h2 th:text="${pair.item1}"></h2>
//        <p th:text="${pair.item2}"></p>
//    </div>
//</div>
}

package com.boots.controller;

import com.boots.entity.User;
import com.boots.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping(path="/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private SessionRegistryImpl sessionRegistry;

    @GetMapping()
    public String userList(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "admin";
    }

    @PostMapping(path="/delete")
    public String  deleteUser(@RequestBody List<Long> IDs, Model model) {
        expireSessions(IDs);
        for(Long userid : IDs) {
            userService.deleteUser(userid);
            System.out.println("ID: " + userid);
        }
        model.addAttribute("users", userService.allUsers());
        return "redirect:/admin";
    }

    @PostMapping(path="/block")
    public String block(@RequestBody List<Long> IDs, Model model) {
        expireSessions(IDs);
        for(Long userid : IDs) {
            userService.blockUser(userid);
            System.out.println("ID: " + userid);
        }
        model.addAttribute("users", userService.allUsers());
        return "redirect:/admin";
    }

    @PostMapping(path="/unblock")
    public String unblock(@RequestBody List<Long> IDs, Model model) {
        for(Long userid : IDs) {
            userService.unblockUser(userid);
            System.out.println("ID: " + userid);
        }
        model.addAttribute("users", userService.allUsers());
        return "redirect:/admin";
    }

    private void expireSessions(List<Long> IDs) {
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        for(Object principal : allPrincipals) {
            User user = (User) principal;
            if(hasId(IDs, user)) {
                closeUserSessions(sessionRegistry.getAllSessions(user, true));
            }
        }
    }

    private boolean hasId(List<Long> IDs, User user) {
        for(Long userid : IDs) {
            if(user.getId().equals(userid)) {
                return true;
            }
        }
        return false;
    }

    private void closeUserSessions(List<SessionInformation> sessions) {
        for(SessionInformation session : sessions) {
            session.expireNow();
        }
    }

}

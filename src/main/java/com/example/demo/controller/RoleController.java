package com.example.demo.controller;

import com.example.demo.dto.RoleDto;
import com.example.demo.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/roles")
    public String getRoles(Model model){
        List<RoleDto> roles = roleService.getAllRoles();

        model.addAttribute("title", "Roles");
        model.addAttribute("roles", roles);

        return "roles";
    }

    @GetMapping("/roles/{id}")
    public RoleDto getRoleById(@PathVariable Integer id){
        return roleService.getRoleById(id);
    }
}
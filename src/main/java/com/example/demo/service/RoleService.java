package com.example.demo.service;

import com.example.demo.dto.RoleDto;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    public RoleDto getRoleById(Integer id){
        return roleMapper.roleEntityToDto(roleRepository.findById(id).orElse(null));
    }

    public RoleDto findByRole(String role){
        return roleMapper.roleEntityToDto(roleRepository.findByRole(role));
    }

    public List<RoleDto> getAllRoles(){
        return roleMapper.roleListEntityToDto(roleRepository.findAll());
    }

    public RoleDto createRole(Role role){
        return roleMapper.roleEntityToDto(roleRepository.save(role));
    }

    public RoleDto updateRole(Role role){
        return roleMapper.roleEntityToDto(roleRepository.save(role));
    }

    public void deleteRole(Role role){
        roleRepository.delete(role);
    }

}
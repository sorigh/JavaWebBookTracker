package com.example.demo.mapper;

import com.example.demo.dto.RoleDto;
import com.example.demo.model.Role;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RoleMapper {

    public RoleDto roleEntityToDto(Role role){
        return RoleDto.builder()
                .role(role.getRole())
                .build();
    }

    public List<RoleDto> roleListEntityToDto(List<Role> roles){
        return roles.stream()
                .map(this::roleEntityToDto)
                .toList();
    }

    public Role roleDtoToEntity(RoleDto roleDto){
        return Role.builder()
                .role(roleDto.role())
                .build();
    }

    public List<Role> roleListDtoToEntity(List<RoleDto> roleDtos){
        return roleDtos.stream()
                .map(this::roleDtoToEntity)
                .toList();
    }
}
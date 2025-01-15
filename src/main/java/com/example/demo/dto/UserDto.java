package com.example.demo.dto;

import lombok.Builder;
import java.util.List;

//even if we sent an actual user object and only printed the details we wanted,
//the content can still be viewed. Thats why dto exists. Dto should never contain pass.

@Builder
public record UserDto(
        String username,
        List<RoleDto> roles,
        String firstName,
        String lastName,
        String emailAddress) {}
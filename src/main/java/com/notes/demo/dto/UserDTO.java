package com.notes.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String username;
    private String email;
    private List<Long> noteIds;

}

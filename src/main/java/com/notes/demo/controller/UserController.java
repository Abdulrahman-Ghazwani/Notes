package com.notes.demo.controller;

import com.notes.demo.model.User;
import com.notes.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/get-all")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/add-user")
    public ResponseEntity addUser(@RequestBody @Valid User user){
        userService.addUser(user);
        return ResponseEntity.ok().body("user added successfully");
    }

    @PutMapping("/edit-user/{id}")
    public ResponseEntity updateUser(@RequestBody @Valid User user,@PathVariable @Valid Long id){
        userService.updateUser(user, id);
        return ResponseEntity.ok().body("user updated successfully");
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body("user deleted successfully");
    }

    @GetMapping("/find-user/{id}")
    public ResponseEntity findUserById(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

}

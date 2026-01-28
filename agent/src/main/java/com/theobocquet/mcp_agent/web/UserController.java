package com.theobocquet.mcp_agent.web;

import com.theobocquet.mcp_agent.agent.User;
import com.theobocquet.mcp_agent.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService users;

    public UserController(UserService users) {
        this.users = users;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User create(@RequestParam String name, @RequestParam String email) {
        return users.create(name, email);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getById(@PathVariable String id) {
        return users.getById(id);
    }
}
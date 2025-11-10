package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private JdbcTemplate jdbc;

    @GetMapping("/users")
    public List<Map<String, Object>> getUsers(@RequestParam(value = "name", required = false) String name) {
        String sql = "SELECT id, username, email FROM users";
        if (name != null && !name.isEmpty()) {
            sql += " WHERE username = '" + name + "'"; // intentional SQL injection
        }
        return jdbc.queryForList(sql);
    }

    @GetMapping("/xss")
    public String reflectedXss(@RequestParam(value = "input", defaultValue = "hello") String input) {
        return "You said: " + input; // intentional XSS
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String username, @RequestParam String email) {
        String sql = "INSERT INTO users (username, email) VALUES ('" + username + "', '" + email + "')"; // intentional SQL injection
        jdbc.execute(sql);
        return "added";
    }
}

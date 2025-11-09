package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private JdbcTemplate jdbc;

    // Vulnerable to SQL Injection: uses string concatenation
    @GetMapping("/users")
    public List<Map<String, Object>> getUsers(@RequestParam(value = "name", required = false) String name) {
        String sql = "SELECT id, username, email FROM users";
        if (name != null && !name.isEmpty()) {
            // intentionally vulnerable: concatenating user input into SQL
            sql += " WHERE username = '" + name + "'";
        }
        return jdbc.queryForList(sql);
    }

    // Reflected XSS: reflects input directly
    @GetMapping("/xss")
    public String reflectedXss(@RequestParam(value = "input", defaultValue = "hello") String input) {
        return "You said: " + input;
    }

    // Unsafe POST that stores raw input into DB without validation
    @PostMapping("/addUser")
    public String addUser(@RequestParam String username, @RequestParam String email) {
        // vulnerable: no input validation or prepared statements
        String sql = "INSERT INTO users (username, email) VALUES ('" + username + "', '" + email + "')";
        jdbc.execute(sql);
        return "added";
    }
}

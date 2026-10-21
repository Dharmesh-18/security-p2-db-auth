package com.security.security_p2_db_auth.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderController {


    @GetMapping("/public")
    public String publicAccess() {
        return "Free entry for everyone!";
    }

    @GetMapping("/admin-zone")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Object> adminOnly(Authentication auth) {
        return Map.of("message", "VIP Lounge", "user", auth.getName(), "authorities", auth.getAuthorities());
    }

    @GetMapping("/customers")
    @PreAuthorize("hasAuthority('CUSTOMER_READ')")
    public String readCustomer() {
        return "Customer list displayed! (Anyone with read permission can see this)";
    }

    @DeleteMapping("/customers/{id}")
    @PreAuthorize("hasAuthority('CUSTOMER_DELETE')")
    public String deleteCustomer(@PathVariable Long id) {
        return "Customer with id:" + id + "  deleted! (Only users with delete permission can hit this)";
    }
}

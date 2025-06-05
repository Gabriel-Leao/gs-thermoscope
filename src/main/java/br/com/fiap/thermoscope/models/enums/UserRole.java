package br.com.fiap.thermoscope.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ADMIN("admin"),
    COORDINATOR("coordinator"),
    FIREFIGHTER("firefighter");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}

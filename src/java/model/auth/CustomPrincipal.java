/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.auth;

import java.security.Principal;

/**
 *
 * @author oupma
 */

// Save info to SecurityContext, avoiding extra queries to the DB
public class CustomPrincipal implements Principal {
    private final String username;
    private final Long userId;

    public CustomPrincipal(String username, Long userId) {
        this.username = username;
        this.userId = userId;
    }

    @Override
    public String getName() {
        return username;
    }

    public Long getUserId() {
        return userId;
    }
    
    @Override
    public String toString() {
        return "CustomPrincipal{" +
                "username='" + username + '\'' +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CustomPrincipal that = (CustomPrincipal) obj;

        if (!username.equals(that.username)) return false;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + userId.hashCode();
        return result;
    }
}
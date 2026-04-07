package com.movie.dea.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterForm {
    @NotBlank
    @Size(min = 3, max = 20)
    @Pattern(
            regexp = "^[a-zA-Z0-9_]+$",
            message = "Username can contain only letters, digits and underscore!"
    )
    private String username;
    @NotBlank
    @Size(min = 4, max = 80, message = "Password must be between 4 and 80 characters")
    private String password;

    @NotBlank(message = "Confirm Password!")
    private String confirmPassword;

   public String getUsername() {
       return username;
   }

   public void setUsername(String username) {
       this.username = username;
   }

   public String getPassword() {
       return password;
   }

   public void setPassword(String password) {
       this.password = password;
   }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword( String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

package com.erm.authentication.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class UserDTO {

        //value: to map any string to variable
        @JsonProperty(value = "userName",required = true)
        @NotNull(message = "Username is required")
        private String username;
        @NotNull(message = "password is required")
        private String password;
        @NotNull(message = "email is required")
        private String email;
        @NotNull(message = "firstName is required")
        private String firstName;
        private String lastName;

        // Getters and Setters


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

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }
}


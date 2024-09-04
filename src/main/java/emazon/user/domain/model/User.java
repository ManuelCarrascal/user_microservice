package emazon.user.domain.model;

import java.time.LocalDate;

public class User {
    private Long userId;
    private String userName;
    private String userLastName;
    private String userIdentityDocument;
    private String userPhone;
    private String userEmail;
    private String userPassword;
    private LocalDate userBirthdate;
    private Long roleId;

    public User() {
    }

    private User(Builder builder) {
        this.userId = builder.userId;
        this.userName = builder.userName;
        this.userLastName = builder.userLastName;
        this.userIdentityDocument = builder.userIdentityDocument;
        this.userPhone = builder.userPhone;
        this.userEmail = builder.userEmail;
        this.userPassword = builder.userPassword;
        this.userBirthdate = builder.userBirthdate;
        this.roleId = builder.roleId;
    }

    public static class Builder {
        private Long userId;
        private String userName;
        private String userLastName;
        private String userIdentityDocument;
        private String userPhone;
        private String userEmail;
        private String userPassword;
        private LocalDate userBirthdate;
        private Long roleId;

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder userLastName(String userLastName) {
            this.userLastName = userLastName;
            return this;
        }

        public Builder userIdentityDocument(String userIdentityDocument) {
            this.userIdentityDocument = userIdentityDocument;
            return this;
        }

        public Builder userPhone(String userPhone) {
            this.userPhone = userPhone;
            return this;
        }

        public Builder userEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }

        public Builder userPassword(String userPassword) {
            this.userPassword = userPassword;
            return this;
        }

        public Builder userBirthdate(LocalDate userBirthdate) {
            this.userBirthdate = userBirthdate;
            return this;
        }

        public Builder roleId(Long roleId) {
            this.roleId = roleId;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserIdentityDocument() {
        return userIdentityDocument;
    }

    public void setUserIdentityDocument(String userIdentityDocument) {
        this.userIdentityDocument = userIdentityDocument;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public LocalDate getUserBirthdate() {
        return userBirthdate;
    }

    public void setUserBirthdate(LocalDate userBirthdate) {
        this.userBirthdate = userBirthdate;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
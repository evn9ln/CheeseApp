package org.cheeseapp.domain;

import javax.persistence.*;

@Entity
@Table(name="Admins")

public class Admin {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer adminId;
    @Column(name="Имя")
    private String adminName;
    @Column(name="Фамилия")
    private String  adminSurname;
    @Column(name="Логин")
    private String adminLogin;
    @Column(name="Пароль")
    private String adminPassword;


    public Admin(){
    }


    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminSurname() {
        return adminSurname;
    }

    public void setAdminSurname(String adminSurname) {
        this.adminSurname = adminSurname;
    }

    public String getAdminLogin() {
        return adminLogin;
    }

    public void setAdminLogin(String adminLogin) {
        this.adminLogin = adminLogin;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", adminSurname='" + adminSurname + '\'' +
                ", adminLogin='" + adminLogin + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                '}';
    }
}

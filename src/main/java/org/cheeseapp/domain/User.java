package org.cheeseapp.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name= "surname")
    private String surname;
    @Column(name= "name")
    private String name;
    @Column(name= "phone_number")
    private String phone;
    @Column(name="e_mail")
    private String mail;
    @Column(name= "login")
    private String login;
    @Column(name= "password")
    private String password;
    @Column(name= "address")
    private String address;
    @Column(name = "active")
    private Boolean active;

    @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER) //доп. таблица для enum создается сама,
                                                                        // подгружаются "жадно" (т.е. при запросе подгружаются сразу все)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id")) //данное поле хранится в отдельной таблице с ролью и id
    @Enumerated(EnumType.STRING) //enum храним в виде строки
    private Set<Role> roles; //client,admin

    public User() {
    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void set(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.mail = user.getMail();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.login = user.getLogin();
        this.password = user.getPassword();
    }


    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", active=" + active +
                ", roles=" + roles +
                '}';
    }
}

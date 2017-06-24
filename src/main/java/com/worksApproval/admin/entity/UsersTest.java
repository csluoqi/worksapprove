package com.worksApproval.admin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "users")
public class UsersTest implements Serializable{
    
    private static final long serialVersionUID = 1L;

    public UsersTest(){
        super();
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    
    @Column(name="name",length=32)
    private String name;
    
    @Column(name="age")
    private Integer age;
    
    @Column(name="nice_name",length=32)
    private String nice_name;

    @Override
    public String toString() {
        return "User [id=" + id + ", user_name=" + name + ", age=" + age
                + ", nice_name=" + nice_name + "]";
    }

    public final Long getId() {
        return id;
    }

    public final void setId(Long id) {
        this.id = id;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final Integer getAge() {
        return age;
    }

    public final void setAge(Integer age) {
        this.age = age;
    }

    public final String getNice_name() {
        return nice_name;
    }

    public final void setNice_name(String nice_name) {
        this.nice_name = nice_name;
    }
}

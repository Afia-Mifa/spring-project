package com.example.studentCrud.dto;

import com.example.studentCrud.model.User;

public class UserDto {
    private long id;
    private String name;
    private int age;

    public UserDto(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public UserDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static UserDto from(User user) {
        return new UserDto(user.getId(), user.getName(), user.getAge());
    }
}

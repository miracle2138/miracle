package com.liyao.miracle.user.entity;

/**
 * @Author 栗垚
 * @Date 2022/11/18
 */
public class User {

    private long id;
    private String userName;
    private long departmentId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }
}

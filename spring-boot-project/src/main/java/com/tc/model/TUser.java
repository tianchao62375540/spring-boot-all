package com.tc.model;

import javax.persistence.*;

@Table(name = "t_user")
public class TUser {
    /**
     * 用户id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private String username;

    private String password;

    /**
     * 用户姓名
     */
    private String fullname;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 获取用户id
     *
     * @return id - 用户id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置用户id
     *
     * @param id 用户id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户姓名
     *
     * @return fullname - 用户姓名
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * 设置用户姓名
     *
     * @param fullname 用户姓名
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
package com.liyao.model;

import java.util.Date;

import lombok.Data;

/**
 * @Author 栗垚
 * @Date 2023/3/8
 */
@Data
public class UserDO {
    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码（明文）
     * <p>
     * ps：生产环境下，千万不要明文噢
     */
    private String password;
    /**
     * 创建时间
     */
    private Date createTime;

}

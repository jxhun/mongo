package com.jxhun.mongo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author carabao
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sale_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户编码
     */
    private String code;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号码
     */
    private String cellphone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户的组织类型
     */
    private String userType;

    /**
     * 用户职位
     */
    private String position;

    /**
     * 工号
     */
    private String employeeId;

    /**
     * 所属组织代码
     */
    private String orgCode;

    /**
     * 用户头像
     */
    private String portrait;

    /**
     * 第三方员工类型(经销商员、分销商员工等)
     */
    private Integer thirdPartyType;

    /**
     * 员工所属经销商
     */
    private String dealerCode;

    /**
     * 员工所属分销商
     */
    private String distributorCode;

    /**
     * 绑定的移动终端号
     */
    private String terminalCode;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 备注
     */
    private String description;

    /**
     * 是否删除(1-是;0-否)
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private LocalDateTime created;

    /**
     * 修改时间
     */
    private LocalDateTime modified;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", email='" + email + '\'' +
                ", userType='" + userType + '\'' +
                ", position='" + position + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", portrait='" + portrait + '\'' +
                ", thirdPartyType=" + thirdPartyType +
                ", dealerCode='" + dealerCode + '\'' +
                ", distributorCode='" + distributorCode + '\'' +
                ", terminalCode='" + terminalCode + '\'' +
                ", state=" + state +
                ", description='" + description + '\'' +
                ", deleted=" + deleted +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}

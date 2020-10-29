package com.jxhun.mongo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 门店信息管理
 * </p>
 *
 * @author carabao
 * @since 2019-10-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sale_shop")
@ToString
public class Shop extends Model<Shop> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     *
     * @Id 标注这个id为MongoDB的id
     */
    @Id
    @TableField(exist = false)
    private ObjectId _id;

    /**
     * 主键ID
     *
     * @Id 标注这个id为MongoDB的id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 编号
     */
    private String code;

    /**
     * 店主姓名
     */
    private String shopkeeper;

    /**
     * 联系电话
     */
    private String contactNumber;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 微信号
     */
    private String wechatNumber;

    /**
     * 渠道类型(传统，现代等)
     */
    private String channelType;

    /**
     * 门店类型(便利店，零售超市)
     */
    private String shopType;

    /**
     * 门店所属大区
     */
    private String regionCode;

    /**
     * 省份ID
     */
    private Long provinceId;

    /**
     * 城市ID
     */
    private Long cityId;

    /**
     * 所属行政区
     */
    private Long districtId;

    /**
     * 冗余的区域名称的全称
     */
    private String districtName;

    /**
     * 油站编码
     */
    private String postcode;

    /**
     * 地址
     */
    private String address;

    /**
     * 纬度
     */
    private String lat;

    /**
     * 经度
     */
    private String lng;

    /**
     * 所属组织代码
     */
    private String orgCode;

    /**
     * 所属经销商
     */
    private String dealerCode;

    /**
     * 门店图片
     */
    private String shopPhotos;

    /**
     * 货架照片
     */
    private String shelvesPhotos;

    /**
     * 店主照片
     */
    private String shopkeeperPhotos;

    /**
     * 状态：1-批准开店、2-拒绝开店
     */
    private Integer state;

    /**
     * 备注
     */
    private String remark;

    /**
     * 照片状态
     */
    private Integer photoStatus;

    /**
     * 是否删除(1-是;0-否)
     */
    private Integer deleted;

    /**
     * 创建人编码
     */
    private String creatorCode;

    /**
     * 创建人姓名
     */
    private String creatorName;

    /**
     * 该维护员工的类型
     */
    private String creatorType;

    /**
     * 修改人编码
     */
    private String modifierCode;

    /**
     * 创建时间
     */
    private LocalDateTime created;

    /**
     * 修改时间
     */
    private LocalDateTime modified;

    /**
     * 照片审核意见
     */
    private String photoApprovalDesc;

    /**
     * 照片审核人
     */
    private String photoApprovalCode;

    /**
     * 照片审核人
     */
    private String photoApprovalName;

    /**
     * 照片审核时间
     */
    private LocalDateTime photoApprovalTime;

    @TableField(exist = false)
    private List<User> userShops;

    @TableField(exist = false)
    private List<Dealer> dealers;

    @TableField(exist = false)
    private Integer users;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", shopkeeper='" + shopkeeper + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                ", wechatNumber='" + wechatNumber + '\'' +
                ", channelType='" + channelType + '\'' +
                ", shopType='" + shopType + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", provinceId=" + provinceId +
                ", cityId=" + cityId +
                ", districtId=" + districtId +
                ", districtName='" + districtName + '\'' +
                ", postcode='" + postcode + '\'' +
                ", address='" + address + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", dealerCode='" + dealerCode + '\'' +
                ", shopPhotos='" + shopPhotos + '\'' +
                ", shelvesPhotos='" + shelvesPhotos + '\'' +
                ", shopkeeperPhotos='" + shopkeeperPhotos + '\'' +
                ", state=" + state +
                ", remark='" + remark + '\'' +
                ", photoStatus=" + photoStatus +
                ", deleted=" + deleted +
                ", creatorCode='" + creatorCode + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", creatorType='" + creatorType + '\'' +
                ", modifierCode='" + modifierCode + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", photoApprovalDesc='" + photoApprovalDesc + '\'' +
                ", photoApprovalCode='" + photoApprovalCode + '\'' +
                ", photoApprovalName='" + photoApprovalName + '\'' +
                ", photoApprovalTime=" + photoApprovalTime +
                ", userShops=" + userShops +
                ", dealers=" + dealers +
                ", users=" + users +
                '}';
    }
}

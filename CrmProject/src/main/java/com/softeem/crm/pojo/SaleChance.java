package com.softeem.crm.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName t_sale_chance
 */
@TableName(value = "t_sale_chance")
@Data
public class SaleChance implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 机会来源
     */
    @TableField(value = "chance_source")
    private String chanceSource;

    /**
     *
     */
    @TableField(value = "customer_name")
    private String customerName;

    /**
     *
     */
    @TableField(value = "cgjl")
    private Integer cgjl;

    /**
     *
     */
    @TableField(value = "overview")
    private String overview;

    /**
     *
     */
    @TableField(value = "link_man")
    private String linkMan;

    /**
     *
     */
    @TableField(value = "link_phone")
    private String linkPhone;

    /**
     *
     */
    @TableField(value = "description")
    private String description;

    /**
     *
     */
    @TableField(value = "create_man")
    private String createMan;

    /**
     *
     */
    @TableField(value = "assign_man")
    private String assignMan;

    /**
     *
     */
    @TableField(value = "assign_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//回传给前端这样的Json字符串时间格式
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//将表单提交上来的字符串转换成时间
    private Date assignTime;

    /**
     *
     */
    @TableField(value = "state")
    private Integer state;

    /**
     *
     */
    @TableField(value = "dev_result")
    private Integer devResult;

    /**
     *
     */
    @TableLogic
    @TableField(value = "is_valid")
    private Integer isValid;

    /**
     *
     */
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    /**
     *
     */
    @TableField(value = "update_date", fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
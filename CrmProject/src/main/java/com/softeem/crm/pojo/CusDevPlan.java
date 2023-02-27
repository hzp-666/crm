package com.softeem.crm.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName t_cus_dev_plan
 */
@TableName(value = "t_cus_dev_plan")
@Data
public class CusDevPlan implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField(value = "sale_chance_id")
    private Integer saleChanceId;

    /**
     *
     */
    @TableField(value = "plan_item")
    private String planItem;

    /**
     *
     */
    @TableField(value = "plan_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//回传给前端这样的Json字符串时间格式
    private Date planDate;

    /**
     *
     */
    @TableField(value = "exe_affect")
    private String exeAffect;

    /**
     *
     */
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//回传给前端这样的Json字符串时间格式
    private Date createDate;

    /**
     *
     */
    @TableField(value = "update_date", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//回传给前端这样的Json字符串时间格式
    private Date updateDate;

    /**
     *
     */
    @TableField(value = "is_valid")
    @TableLogic
    private Integer isValid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
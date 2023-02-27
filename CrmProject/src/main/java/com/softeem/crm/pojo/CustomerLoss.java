package com.softeem.crm.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName t_customer_loss
 */
@TableName(value = "t_customer_loss")
@Data
public class CustomerLoss implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField(value = "cus_no")
    private String cusNo;

    /**
     *
     */
    @TableField(value = "cus_name")
    private String cusName;

    /**
     *
     */
    @TableField(value = "cus_manager")
    private String cusManager;

    /**
     *
     */
    @TableField(value = "last_order_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastOrderTime;

    /**
     *
     */
    @TableField(value = "confirm_loss_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")//将表单提交上来的字符串转换成时间
    private Date confirmLossTime;

    /**
     *
     */
    @TableField(value = "state")
    private Integer state;

    /**
     *
     */
    @TableField(value = "loss_reason")
    private String lossReason;

    /**
     *
     */
    @TableField(value = "is_valid")
    @TableLogic
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
package com.softeem.crm.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName t_customer_serve
 */
@TableName(value = "t_customer_serve")
@Data
public class CustomerServe implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField(value = "serve_type")
    private String serveType;

    /**
     *
     */
    @TableField(value = "overview")
    private String overview;

    /**
     *
     */
    @TableField(value = "customer")
    private String customer;

    /**
     *
     */
    @TableField(value = "state")
    private String state;

    /**
     *
     */
    @TableField(value = "service_request")
    private String serviceRequest;

    /**
     *
     */
    @TableField(value = "create_people")
    private String createPeople;

    /**
     *
     */
    @TableField(value = "assigner")
    private String assigner;

    /**
     *
     */
    @TableField(value = "assign_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date assignTime;

    /**
     *
     */
    @TableField(value = "service_proce")
    private String serviceProce;

    /**
     *
     */
    @TableField(value = "service_proce_people")
    private String serviceProcePeople;

    /**
     *
     */
    @TableField(value = "service_proce_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date serviceProceTime;

    /**
     *
     */
    @TableField(value = "service_proce_result")
    private String serviceProceResult;

    /**
     *
     */
    @TableField(value = "myd")
    private String myd;

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
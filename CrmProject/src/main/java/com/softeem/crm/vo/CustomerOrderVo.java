package com.softeem.crm.vo;

import com.softeem.crm.pojo.CustomerOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderVo extends CustomerOrder {
    private Integer total;
}

package com.softeem.crm.vo;

import com.softeem.crm.pojo.CustomerServe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerServeVo extends CustomerServe {
    private String dicValue;
}

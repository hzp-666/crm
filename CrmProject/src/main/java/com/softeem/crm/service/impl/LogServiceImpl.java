package com.softeem.crm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.softeem.crm.pojo.Log;
import com.softeem.crm.service.LogService;
import com.softeem.crm.mapper.LogMapper;
import org.springframework.stereotype.Service;

/**
* @author hzp
* @description 针对表【t_log】的数据库操作Service实现
* @createDate 2022-12-27 14:22:57
*/
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log>
    implements LogService{

}





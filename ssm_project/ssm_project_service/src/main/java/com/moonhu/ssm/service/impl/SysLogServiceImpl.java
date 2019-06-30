package com.moonhu.ssm.service.impl;

import com.moonhu.ssm.dao.SysLogDao;
import com.moonhu.ssm.domain.SysLog;
import com.moonhu.ssm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) throws ExecutionException {
        sysLogDao.save(sysLog);
    }
}

package com.moonhu.ssm.service.impl;

import com.moonhu.ssm.dao.RoleDao;
import com.moonhu.ssm.domain.Role;
import com.moonhu.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl  implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findById(String id) throws Exception {
        return roleDao.findRoleById(id);
    }

    @Override
    public List<Role> finAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save( role);
    }

}

package com.moonhu.ssm.service.impl;

import com.moonhu.ssm.dao.UserInfoDao;
import com.moonhu.ssm.domain.Permission;
import com.moonhu.ssm.domain.Role;
import com.moonhu.ssm.domain.UserInfo;
import com.moonhu.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 实现UserDetailsService的方法
     *
     * @param username 登陆时传过来的用户名
     * @return UserDetails对象, 封装了用户的username, password, 角色信息和权限信息
     * @throws UsernameNotFoundException 找不到该用户
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserInfo userInfo = userInfoDao.findUserByName(username);
            if (userInfo != null) {
                return new User(userInfo.getUsername(), userInfo.getPassword(), getAuthority(userInfo.getRoles()));
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取用户的权限
     * <p>
     * userInfo userInfo
     *
     * @return 封装之后的权限
     */
    private List<GrantedAuthority> getAuthority(List<Role> roles) {
        ArrayList<GrantedAuthority> list = new ArrayList<>();
        if (roles != null) {
            // 将所有的角色封装到集合中
            for (Role role : roles) {
                List<Permission> permissions = role.getPermissions();
                if (permissions != null) {
                    // 将所有的权限封装到集合中
                    for (Permission permission : permissions) {
                        list.add(new SimpleGrantedAuthority(permission.getPermissionName()));
                    }
                }
                list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            }
        }
        return list;
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        return userInfoDao.findById(id);
    }

    @Override
    public List<UserInfo> findAll() throws Exception {
        return userInfoDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userInfoDao.save(userInfo);
    }

    /**
     * @param userInfo session域中获取的数据
     * @param rolesId  前台传输的数据
     */
    @Override
    public void addRoleToUser(UserInfo userInfo, String[] rolesId) {
        String userId = userInfo.getId();
        List<Role> roles = userInfo.getRoles();
        // 之前的id
        ArrayList<String> beforIds = new ArrayList<>();
        // 之后的id
        List<String> afterIds = null;
        // 将之前角色的id封装到列表中
        if (roles != null) {
            for (Role role : roles) {
                beforIds.add(role.getId());
            }
        }

        // 如果前台传过来的数据不为空,
        if (rolesId != null && rolesId.length > 0) {
            afterIds = Arrays.asList(rolesId);
        }

        if (roles == null && rolesId != null && rolesId.length == 0) {
            // 两者都为空时,直接返回即可
            return;
        } else if (roles != null && rolesId != null && rolesId.length == 0) {
            // 之前角色不为空,之后为空说明都删除了,直接删除原有的即可
            for (String beforid : beforIds) {
                userInfoDao.userDeleteRole(beforid);
            }
        } else if (roles == null && rolesId != null && rolesId.length > 0) {
            //  如果之前的为空,之后的不为空直接添加即可
            for (String afterId : afterIds) {
                userInfoDao.userAddRole(userId, afterId);
            }
        } else if (roles != null && rolesId != null && rolesId.length > 0) {
            // 如果之前的不为空,之后的不为空,需要判断


            // 如果之前的id不包括之前的id,说明删掉了
            for (String beforId : beforIds) {
                if (!afterIds.contains(beforId)) {
                    userInfoDao.userDeleteRole(beforId);
                }
            }

            // 如果之后的id集合中不包含之前的,说是时新加的
            for (String afterId : afterIds) {
                if (!beforIds.contains(afterId)) {
                    userInfoDao.userAddRole(userId, afterId);
                }
            }
        }
    }

    /**
     * 查询所有role并且标记已有的角色
     *
     * @param id user id
     * @return roles集合, 包含了标记
     */
    @Override
    public List<Role> findAllRolesAndChecked(String id) {
        return userInfoDao.findAllRolesAndChecked(id);
    }


}

package com.tc.service.user;

import com.tc.common.PageResult;
import com.tc.model.TUser;

/**
 * @Auther: tianchao
 * @Date: 2020/4/10 21:28
 * @Description:
 */
public interface UserService {

    void saveUser(TUser tUser);

    void updateUser(TUser tUser);

    void deleteUser(Long id);

    PageResult<TUser> queryTUserPage(Integer page, Integer rows, String sortBy, Boolean desc, String key);
}

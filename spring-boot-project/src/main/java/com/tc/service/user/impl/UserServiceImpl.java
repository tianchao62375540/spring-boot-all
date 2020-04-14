package com.tc.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.tc.common.ExceptionEnum;
import com.tc.common.PageResult;
import com.tc.common.ServiceException;
import com.tc.mapper.TUserMapper;
import com.tc.model.TUser;
import com.tc.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/4/10 21:29
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TUserMapper tUserMapper;

    @Override
    public void saveUser(TUser tUser) {
        tUserMapper.insertSelective(tUser);
    }

    @Override
    public void updateUser(TUser tUser) {
        tUserMapper.updateByPrimaryKeySelective(tUser);
    }

    @Override
    public void deleteUser(Long id) {
        tUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageResult<TUser> queryTUserPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        PageHelper.startPage(page,rows);
        Example example = new Example(TUser.class);
        if(StringUtils.isNotBlank(key)){
            //過濾條件
            Example.Criteria criteria = example.createCriteria();
            criteria.andLike("username", "%"+key+"%");
        }
        //排序
        if(StringUtils.isNotBlank(sortBy)){
            String orderByCause = sortBy + (desc?" DESC":" ASC");
            example.setOrderByClause(orderByCause);
        }
        List<TUser> list = tUserMapper.selectByExample(example);
        if ("1".equals(key)){
            throw new ServiceException(ExceptionEnum.SYSTEM_ERROR);
        }
        if ("2".equals(key)){
            throw new IllegalArgumentException("错误参数异常");
        }
        if ("3".equals(key)){
            throw new RuntimeException("运行时异常");
        }
        return PageResult.newPageResult(list);
    }


}

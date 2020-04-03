package com.tc.cache.service;

import com.tc.cache.bean.Employee;
import com.tc.cache.mapper.EmployeeMapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * @Auther: tianchao
 * @Date: 2020/3/27 21:54
 * @Description:
 */
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Cacheable(cacheNames="employee",key = "#root.args[0]")
    public Employee getEmp(Integer id){
        System.out.println("查询"+id+"号员工");
        return employeeMapper.getEmployeeById(id);
    }

    @CachePut(cacheNames = "employee",key = "#result.id")
    public Employee updateEmp(Employee employee){
        employeeMapper.updateEmployee(employee);
        return employee;
    }

    @CacheEvict(value = "employee",key="#id",beforeInvocation = true)
    public void deleteEmp(Integer id){
        employeeMapper.deleteEmployeeById(id);
    }

    @Caching(
            cacheable = {
                    @Cacheable(value = "employee",key = "#lastName")
            },
            put = {
                    @CachePut(value = "employee",key = "#result.id"),
                    @CachePut(value = "employee",key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName){
        return employeeMapper.selectEmpByLastName(lastName);
    }
}

package com.tc.cache.mapper;

import com.tc.cache.bean.Employee;
import org.apache.ibatis.annotations.*;

/**
 * @Auther: tianchao
 * @Date: 2020/3/27 21:39
 * @Description:
 */
@Mapper
public interface EmployeeMapper {
    @Select("SELECT * FROM employee WHERE id = #{id}")
    Employee getEmployeeById(Integer id);

    @Update("UPDATE employee set lastName = #{lastName},email = #{email},gender = #{gender},d_id = #{dId} WHERE id = #{id}")
    void updateEmployee(Employee employee);

    @Delete("DELETE FROM employee WHERE id = #{id}")
    void deleteEmployeeById(Integer id);

    @Insert("INSERT INTO employee (lastName,email,gender,d_id) VALUES (#{lastName},#{email},#{gender},#{dId})")
    void insertEmployee(Employee employee);
    @Select("SELECT * FROM employee WHERE lastName = #{lastName}")
    Employee selectEmpByLastName(String lastName);
}

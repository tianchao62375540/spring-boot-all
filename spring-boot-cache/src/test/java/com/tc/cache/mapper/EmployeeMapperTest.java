package com.tc.cache.mapper;

import com.tc.cache.bean.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther: tianchao
 * @Date: 2020/3/27 21:44
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeMapperTest {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    public ApplicationContext context;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate1;
    @Test
    public void testInsert(){
        Employee employee = new Employee();
        employee.setEmail("455433520@qq.com");
        employee.setGender(1);
        employee.setdId(1);
        employee.setLastName("田超2");
        employeeMapper.insertEmployee(employee);
    }
    @Test
    public void testUpdate(){
        Employee employee = new Employee();
        employee.setId(2);
        employee.setEmail("455433520@qq.com");
        employee.setGender(1);
        employee.setdId(1);
        employee.setLastName("田超555");
        employeeMapper.updateEmployee(employee);
    }
    @Test
    public void testDelete(){
        employeeMapper.deleteEmployeeById(2);
    }

    @Test
    public void testSelect(){
        Employee employeeById = employeeMapper.getEmployeeById(1);
        System.out.println(employeeById);
    }
    @Test
    public void testRedis01(){
        stringRedisTemplate.opsForValue().append("message", "hello");
        System.out.println(stringRedisTemplate.opsForValue().get("message"));
    }
    @Test
    public void testRedis02(){
        /*redisTemplate.opsForValue().append("message1", "kkk");*/
        System.out.println(stringRedisTemplate.opsForValue().get("message"));
        System.out.println(stringRedisTemplate.keys("*"));
        System.out.println(redisTemplate.keys("*"));
    }
    @Test
    public void testRedis03(){
        /*redisTemplate.opsForValue().append("message1", "kkk");*/
        Employee employeeById = employeeMapper.getEmployeeById(3);
        redisTemplate.opsForValue().set("emp---"+employeeById.getId(), employeeById);
        System.out.println(redisTemplate.opsForValue().get("emp---" + employeeById.getId()));
    }
    @Test
    public void testRedis04(){

        String[] beanNamesForType = context.getBeanNamesForType(RedisTemplate.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }
    }

    @Test
    public void testRedis05(){
        stringRedisTemplate.opsForValue().set("string", "hello");
        System.out.println("stringRedisTemplate get string:" + stringRedisTemplate.opsForValue().get("string") );
        System.out.println("redisTemplate get string:" + redisTemplate.opsForValue().get("string") );
        Employee employee = new Employee();
        employee.setId(555);
        employee.setLastName("hehe");
        employee.setGender(2);
        redisTemplate.opsForValue().set("string", employee);
        System.out.println("stringRedisTemplate get string:" + stringRedisTemplate.opsForValue().get("string") );
        System.out.println("redisTemplate get string:" + redisTemplate.opsForValue().get("string") );
        //System.out.println(stringRedisTemplate.opsForValue().get("message"));
    }
    @Test
    public void testRedis06(){
    }


}
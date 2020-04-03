package com.tc.cache.controller;

import com.tc.cache.bean.Employee;
import com.tc.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: tianchao
 * @Date: 2020/3/27 21:59
 * @Description:
 */
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        return employeeService.getEmp(id);
    }

    @GetMapping("/emp")
    public Employee upd(Employee employee){
        return employeeService.updateEmp(employee);
    }
    @GetMapping("/del")
    public String deleteEmp(Integer id){
        employeeService.deleteEmp(id);
        return "success";
    }

    @GetMapping("/emp/lastName/{lastName}")
    public Employee getEmpByLastName(@PathVariable("lastName") String lastName){
        Employee empByLastName = employeeService.getEmpByLastName(lastName);
        return empByLastName;
    }
}

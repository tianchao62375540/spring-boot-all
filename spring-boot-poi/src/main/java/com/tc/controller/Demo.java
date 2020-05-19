package com.tc.controller;

import com.tc.util.ExportWordUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: tianchao
 * @Date: 2020/5/19 22:01
 * @Description:
 */
@Controller
public class Demo {
    @RequestMapping("/demo/export")
    public void export(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> params = new HashMap<>();
        params.put("card","210181198903088330");
        params.put("name","李四");
        params.put("password", "62375540");
        //这里是我说的一行代码
        ExportWordUtils.exportWord("word/export.docx","temp","aaa.docx",params,request,response);
    }
}

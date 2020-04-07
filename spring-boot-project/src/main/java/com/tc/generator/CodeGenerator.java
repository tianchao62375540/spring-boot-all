package com.tc.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;
import java.util.List;

//import static org.apache.ibatis.io.Resources.getResourceAsStream;
/**
 * @Auther: tianchao
 * @Date: 2020/4/7 21:57
 * @Description:
 */
public class CodeGenerator {
    /**
     * *************************************************
     *                     代码生成
     *    启动前需修改配置文件    generatorConfig.xml
     * *************************************************
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overWrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config =
                cp.parseConfiguration(ClassLoader.getSystemResourceAsStream("generatorConfig/generatorConfig.xml"));
        DefaultShellCallback callback = new DefaultShellCallback(overWrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }

}

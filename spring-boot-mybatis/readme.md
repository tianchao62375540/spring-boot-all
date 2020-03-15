##1. 引入依赖
    <dependency>
         <groupId>mysql</groupId>
         <artifactId>mysql-connector-java</artifactId>
     </dependency>
     <dependency>
         <groupId>org.mybatis.spring.boot</groupId>
         <artifactId>mybatis-spring-boot-starter</artifactId>
         <version>2.0.0</version>
     </dependency>
     
##2. 编写Mapper接口 
    public interface UserMapper {
    
        @Select("select * from user")
        List<User> list();
    }

##3.     
         

    
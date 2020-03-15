package com.tc.batch;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/3/14 23:48
 * @Description:
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class BatchExecutorHelper implements ApplicationContextAware {

    ApplicationContext context;

    private String statement;

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public <T> Long insertObj(List<T> list){
        Assert.assertTrue("导入数据不能为0--不正确",list!=null&&list.size()>0);
        long begin = System.currentTimeMillis();
        int total = list.size();
        System.out.println("[插入开始.....] 数量"+total);
        SqlSessionFactory sqlSessionFactory = context.getBean(SqlSessionFactory.class);
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH  ,false);
            int a = 2000;
            int loop = (int) Math.ceil(list.size() / (double) a);

            List<T> tempList;
            int start,stop;
            for (int i = 0; i < loop; i++) {
                start = i*a;
                stop = (i+1)*a>=total?total:(i+1)*a;
                tempList = list.subList(start,stop);
                sqlSession.insert(statement, tempList);
                sqlSession.commit();
                sqlSession.clearCache();
                System.out.println("已经插入" +start + "到 "+stop+" 条记录");
            }
        }catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println((end- begin)+"毫秒 处理"+list.size()+"条数据" + Thread.currentThread().getName());
        return Long.valueOf(list.size());
    }



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }


}

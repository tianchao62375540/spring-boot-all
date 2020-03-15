package com.tc.batch;

import com.tc.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


/**
 * @Auther: tianchao
 * @Date: 2020/3/15 00:40
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BatchExecutorHelperTest {

    @Autowired
    private BatchExecutorHelper helper;
    @Test
    public void testBatch(){
        helper.setStatement("tianchao.BatchMapper.insertBatch");
        List<User> users = buildUserList(5000000);
        long start = System.currentTimeMillis();
        helper.insertObj(users);
        System.out.println((System.currentTimeMillis() - start)+"单线程批量 毫秒数");
    }

    private List<User> buildUserList(int num) {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(new User().setAge(15).setPhone("18940295748").setDesc("描述"+i).setUsername("田超"));
        }
        return list;
    }
    @Test
    public void testForkJoinbatch() throws ExecutionException, InterruptedException {
        int NCPU = Runtime.getRuntime().availableProcessors();
        ForkJoinPool fjp = new ForkJoinPool(NCPU);
        List<User> users = buildUserList(5000000);
        long start = System.currentTimeMillis();
        helper.setStatement("tianchao.BatchMapper.insertBatch");
        BatchInsertTask task = new BatchInsertTask(users, helper);
        ForkJoinTask<Long> result = fjp.submit(task);
        System.out.println("forkJoin handler num = "+result.get());
        System.out.println((System.currentTimeMillis() - start)+"多线程批量 毫秒数");
    }
    @Test
    public void testForkJoinbatchNoResult() throws ExecutionException, InterruptedException {
        int NCPU = Runtime.getRuntime().availableProcessors();
        ForkJoinPool fjp = new ForkJoinPool(NCPU);
        List<User> users = buildUserList(5000000);
        long start = System.currentTimeMillis();
        helper.setStatement("tianchao.BatchMapper.insertBatch");
        BatchInsertTaskAction task = new BatchInsertTaskAction(users, helper);
        fjp.submit(task);
        fjp.awaitTermination(50, TimeUnit.SECONDS);
        //System.out.println("forkJoin handler num = "+result.get());
        System.out.println((System.currentTimeMillis() - start)+"多线程批量 毫秒数");
    }
    @Test
    public void testPoolbatch() throws ExecutionException, InterruptedException {
        int NCPU = Runtime.getRuntime().availableProcessors();
        ForkJoinPool fjp = new ForkJoinPool(NCPU);
        List<User> users = buildUserList(5000000);
        long start = System.currentTimeMillis();
        helper.setStatement("tianchao.BatchMapper.insertBatch");
        ExecutorService executorService= Executors.newCachedThreadPool();
        //ForkJoinTask<Long> result = executorService.submit(fjp);
        //System.out.println("forkJoin handler num = "+result.get());
        //System.out.println((System.currentTimeMillis() - start)+"多线程批量 毫秒数");
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

}
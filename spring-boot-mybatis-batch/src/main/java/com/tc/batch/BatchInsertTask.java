package com.tc.batch;

import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @Auther: tianchao
 * @Date: 2020/3/15 13:07
 * @Description:
 */
public class BatchInsertTask extends RecursiveTask<Long> {
    List<?> tasks = null;
    int SEQUENTIAL_THERSHOLD = 20000;
    int start ;
    int end ;
    BatchExecutorHelper batchExecutorHelper ;
    public BatchInsertTask(List<?> tasks,BatchExecutorHelper batchExecutorHelper) {
        this.tasks = tasks;
        this.start = 0;
        this.end = tasks.size();
        this.batchExecutorHelper = batchExecutorHelper;
    }
    private BatchInsertTask(List<?> tasks, int start, int end, BatchExecutorHelper batchExecutorHelper) {
        this.tasks = tasks;
        this.start = start;
        this.end = end;
        this.batchExecutorHelper = batchExecutorHelper;
    }
    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected Long compute() {
        System.out.println();
        if (end - start<=SEQUENTIAL_THERSHOLD){
            tasks.subList(start, end);
            System.out.println("拆分"+start+"到"+end +"  "+Thread.currentThread().getName());
            return batchExecutorHelper.insertObj(tasks.subList(start, end));
        }else{
            int mid = (start + end)/2;
            BatchInsertTask left = new BatchInsertTask(tasks, start, mid,batchExecutorHelper);
            BatchInsertTask right = new BatchInsertTask(tasks, mid, end,batchExecutorHelper);
            left.fork();
            right.fork();
            Long leftAns = left.join();
            Long rightAns = right.join();
            return leftAns + rightAns;
        }
    }
}

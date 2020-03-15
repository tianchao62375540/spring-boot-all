package com.tc.batch;

import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @Auther: tianchao
 * @Date: 2020/3/15 13:07
 * @Description:
 */
public class BatchInsertTaskAction extends RecursiveAction {

    List<?> tasks = null;
    int SEQUENTIAL_THERSHOLD = 20000;
    int start ;
    int end ;
    BatchExecutorHelper batchExecutorHelper ;

    public BatchInsertTaskAction(List<?> tasks, BatchExecutorHelper batchExecutorHelper) {
        this.tasks = tasks;
        this.start = 0;
        this.end = tasks.size();
        this.batchExecutorHelper = batchExecutorHelper;
    }

    private BatchInsertTaskAction(List<?> tasks, int start, int end, BatchExecutorHelper batchExecutorHelper) {
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
    protected void compute() {
        System.out.println();
        if (end - start<=SEQUENTIAL_THERSHOLD){
            tasks.subList(start, end);
            System.out.println("拆分"+start+"到"+end +"  "+Thread.currentThread().getName());

            batchExecutorHelper.insertObj(tasks.subList(start, end));
        }else{
            int mid = (start + end)/2;
            BatchInsertTaskAction left = new BatchInsertTaskAction(tasks, start, mid,batchExecutorHelper);
            BatchInsertTaskAction right = new BatchInsertTaskAction(tasks, mid, end,batchExecutorHelper);
            left.fork();
            right.fork();
        }
    }
}

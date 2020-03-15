package com.tc.batch;

import com.sun.jmx.snmp.tasks.Task;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Auther: tianchao
 * @Date: 2020/3/15 14:31
 * @Description:
 */
public class CallableTask implements Callable<Long> {
    List<?> tasks = null;
    int SEQUENTIAL_THERSHOLD = 8000;
    int start ;
    int end ;
    BatchExecutorHelper batchExecutorHelper ;
    @Override
    public Long call() throws Exception {
        return null;
    }
}

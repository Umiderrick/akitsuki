package im.vinci.test;


import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author pengbo
 * @date 2022/4/21 16:20
 * @name:
 */
public class GGG {

    private final static int ARRAY_LENGTH =10;

    private CountDownLatch commonCountDownLatch =new CountDownLatch(5);
    private CountDownLatch parallelCountDownLatch =new CountDownLatch(5);

    private int[][] arrays ={{1,2,3},{1,2,3},{1,2,3}};
    void testParallelStream() throws InterruptedException {
        ExecutorService threadPool = new ThreadPoolExecutor(50, 200, 20, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1000),
                new ThreadFactoryBuilder().setNamePrefix("test-parallel-thread").build(), new ThreadPoolExecutor.CallerRunsPolicy());

        Long time1 = System.currentTimeMillis();
        // 1. 多线程+foreach执行时长
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            CommonExecutor commonExecutor = new CommonExecutor();
            commonExecutor.array = arrays[i];
            threadPool.submit(commonExecutor);
        }
        commonCountDownLatch.await();
        Long time2 = System.currentTimeMillis();
        System.out.println("for循环耗时: " + (time2 - time1));

        threadPool = new ThreadPoolExecutor(50, 200, 20, TimeUnit.MINUTES, new ArrayBlockingQueue<>(1000),
                new ThreadFactoryBuilder().setNamePrefix("test-parallel-thread").build(), new ThreadPoolExecutor.CallerRunsPolicy());

        // 2. 多线程+并行流执行时长
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            ParallelStreamExecutor parallelStreamExecutor = new ParallelStreamExecutor();
            parallelStreamExecutor.array = arrays[i];
            threadPool.submit(parallelStreamExecutor);
        }
        parallelCountDownLatch.await();
        Long time3 = System.currentTimeMillis();
        System.out.println("并行流耗时: " + (time3 - time2));
    }


}

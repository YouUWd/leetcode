package com.youu.base;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadFactoryTest {
    static void t1() {
        ExecutorService executor = Executors.newFixedThreadPool(2);


        for (int i = 0; i < 10; i++) {
            int fi = i;
            executor.execute(() -> System.out.println(Thread.currentThread() + ": " + fi));
        }
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
            executor.shutdownNow();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static void t2() {

        ExecutorService executor = new ThreadPoolExecutor(2, 4, 2, TimeUnit.SECONDS, new LinkedBlockingQueue<>(96));
        AtomicInteger c = new AtomicInteger();
        for (int i = 0; i < 100; i++) {
            int fi = i;
            executor.execute(() -> {
                try {
                    System.out.println(Thread.currentThread() + ": " + fi);

                    TimeUnit.MILLISECONDS.sleep(20);
                    c.incrementAndGet();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 10; i++) {
            int fi = i;
            executor.execute(() -> {
                try {
                    System.out.println(Thread.currentThread() + ": " + fi);

                    TimeUnit.MILLISECONDS.sleep(20);
                    c.incrementAndGet();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            System.out.println("========================" + c);
            executor.awaitTermination(20, TimeUnit.SECONDS);
            executor.shutdownNow();
            System.out.println(c.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * 判断额外线程（maximumPoolSize）是什么时候启动的，是队列满了才启动的还是队列有待执行任务就启动了
     * 由以下执行结果可判断为队列满了才启动的
     * 当任务执行效率高的时候，队列可能一直不会满，就不会启动额外线程
     * workQueueSize+corePoolSize>=taskCount 不启动额外线程
     * workQueueSize+corePoolSize < taskCount <= workQueueSize+maximumPoolSize 启动额外线程
     * taskCount > workQueueSize+maximumPoolSize 执行RejectedExecutionHandler 拒绝策略，默认AbortPolicy，这个抛出异常是RuntimeException，，会导致后续主线程夯死
     */
    static void t3() {
        ExecutorService executor = new ThreadPoolExecutor(2, 4, 2, TimeUnit.SECONDS, new LinkedBlockingQueue<>(95));
        AtomicInteger c = new AtomicInteger();
        for (int i = 0; i < 100; i++) {
            int fi = i;
            executor.execute(() -> {
                try {
                    System.out.println(Thread.currentThread() + ": " + fi);

                    TimeUnit.MILLISECONDS.sleep(3);
                    c.incrementAndGet();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            System.out.println("========================" + c);
            executor.awaitTermination(3, TimeUnit.SECONDS);
            executor.shutdownNow();
            System.out.println(c.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * 判断额外线程（maximumPoolSize）是什么时候启动的，是队列满了才启动的还是队列有待执行任务就启动了
     * 由以下执行结果可判断为队列满了才启动的
     * 当任务执行效率高的时候，队列可能一直不会满，就不会启动额外线程
     * workQueueSize+corePoolSize>=taskCount 不启动额外线程
     * workQueueSize+corePoolSize < taskCount <= workQueueSize+maximumPoolSize 启动额外线程
     * taskCount > workQueueSize+maximumPoolSize 执行RejectedExecutionHandler 拒绝策略，默认AbortPolicy，这个抛出异常是RuntimeException，，会导致后续主线程夯死
     */
    static void t4() {
        ExecutorService executor = new ThreadPoolExecutor(2, 4, 2, TimeUnit.SECONDS, new LinkedBlockingQueue<>(90));
        AtomicInteger c = new AtomicInteger();
        for (int i = 0; i < 100; i++) {
            int fi = i;
            Runnable r = () -> {
                System.out.println(Thread.currentThread() + ": " + fi);
                c.incrementAndGet();
                try {
                    TimeUnit.MILLISECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };

            executor.submit(r);
        }


        try {
            System.out.println("========================" + c);
            executor.awaitTermination(3, TimeUnit.SECONDS);
            executor.shutdownNow();
            System.out.println(c.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * executor.submit(r)可能会导致线程无法继续正常运行
     */
    static void t5() {
        ExecutorService executor = new ThreadPoolExecutor(2, 4, 2, TimeUnit.SECONDS, new LinkedBlockingQueue<>(90));
        AtomicInteger c = new AtomicInteger();
        for (int i = 0; i < 100; i++) {
            int fi = i;
            Runnable r = () -> {
//                System.out.println(Thread.currentThread() + ": " + fi);
                c.incrementAndGet();
                try {
                    TimeUnit.MILLISECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            System.out.println("before submit " + i);
            try {
                executor.submit(r);
            } catch (Exception e) {
                break;
            }
            System.out.println(" after submit " + i);
        }

        executor.submit(() -> {
            System.out.println("xxxx");
            c.incrementAndGet();
        });
        try {
            System.out.println("========================" + c);
            executor.awaitTermination(3, TimeUnit.SECONDS);
            executor.shutdownNow();
            System.out.println(c.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
//        t1();
//        t2();
//        t3();
//        t4();
        t5();
    }
}

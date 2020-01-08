package com.example.connectionpool.test;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.cache.annotation.Caching;
import org.springframework.core.task.support.ExecutorServiceAdapter;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @author m
 * @date 2019/12/31 17:23
 */
class test1 implements Runnable {
    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
            this.notify();
            try {
                this.wait();
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class test2 implements Runnable {
    @Override
    public synchronized void run() {
        for (char i = 65; i < 75; i++) {
            System.out.print(i + " ");
            this.notify();
            try {
                this.wait();
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
@Caching
public class test {
    Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();

    public void f1() {
        lock.lock();
        try {

            for (int i = 0; i < 5; i++) {

                System.out.print(i + "\t");
                c2.signal();
                c1.await();
            }
            c2.signal();
        } catch (Exception e) {
            //null
        } finally {
            lock.unlock();
        }
    }

    public void f2() {
        lock.lock();
        try {

            for (char i = 65; i < 70; i++) {

                System.out.print(i + "\t");
                c3.signal();
                c2.await();
            }
            c3.signal();
        } catch (Exception e) {
            //null
        } finally {
            lock.unlock();
        }
    }

    public void f3() {
        lock.lock();
        try {
            for (char i = 97; i < 102; i++) {
                System.out.print(i + "\t");
                c1.signal();
                c3.await();
            }
            c3.signal();
        } catch (Exception e) {
            //null
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
       /* test w = new test();
        new Thread(() -> w.f1()).start();
        new Thread(() -> w.f2()).start();
        new Thread(() -> w.f3()).start();
 ThreadFactory th=new ThreadFactoryBuilder().setNameFormat("my-thread-%d").build();*/
    /*   Thread s=new Thread(new Runnable() {
           @Override
           public void run() {
                   for (int i = 0; i < 10; i++) {
                       System.out.print('a');
                   }
               }
       });
       Thread x=new Thread(new Runnable() {
           @Override
           public void run() {
                   for (int i = 0; i < 10; i++) {
                       System.out.print('b');
                   }
           }
       });

        s.start();
        s.join();
        x.start();
        x.join();
        for (int i = 0; i <10 ; i++) {
            System.out.print('c');

        }*/

      /*  ExecutorService service= Executors.newSingleThreadExecutor();
        Future<String> future=service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "实现callable接口";
            }
        });
        try{
            System.out.println("异步");
            String result=future.get();
            System.out.println(result);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        catch (ExecutionException e){
            e.printStackTrace();
        }
        finally {
            service.shutdown();
        }*/

              /*  Thread daemonThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                System.out.println("i am alive");
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                System.out.println("finally block");
                            }
                        }
                    }
                });
                daemonThread.setDaemon(true);
                daemonThread.start();
                //确保main线程结束前能给daemonThread能够分到时间片
                try {
                    Thread.sleep(999);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

            }

}

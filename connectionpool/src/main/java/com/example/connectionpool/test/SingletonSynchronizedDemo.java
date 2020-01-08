package com.example.connectionpool.test;



/**
 * @author m
 * @date 2020/1/7 14:53
 */
public class SingletonSynchronizedDemo {

    private static class inner {
        private static SingletonSynchronizedDemo singletonSynchronizedDemo =new SingletonSynchronizedDemo();
    }
    private SingletonSynchronizedDemo(){
    }
    public SingletonSynchronizedDemo getInstance(){
        return inner.singletonSynchronizedDemo;
    }
    public SingletonSynchronizedDemo Instance(){
        return Singleton.INSTANCE.Instance();
    }
    public enum Singleton {
        /**
         * INSTANCE
         */
        INSTANCE;
        private SingletonSynchronizedDemo ssd;
         Singleton(){
            ssd=new SingletonSynchronizedDemo();
        }
        public SingletonSynchronizedDemo Instance(){
             return ssd;
        }
    }
    public static void main(String[] args) {
        SingletonSynchronizedDemo sd=Singleton.INSTANCE.Instance();
        SingletonSynchronizedDemo sd1=Singleton.INSTANCE.Instance();
        System.out.println(sd==sd1);
    }
}

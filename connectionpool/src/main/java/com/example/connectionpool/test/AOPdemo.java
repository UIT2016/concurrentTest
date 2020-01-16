package com.example.connectionpool.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author myj
 * @description
 * @date 2020/1/15 14:21
 */
public class AOPdemo implements InvocationHandler {
    private inimp1 s;
    public AOPdemo(inimp1 s){
        this.s=s;
    }
    public <T> T getInstance(){
       return (T) Proxy.newProxyInstance(s.getClass().getClassLoader(),s.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法前");
        Object result=method.invoke(s,null);
        System.out.println("方法后");
        return result;
    }

    public static void main(String[] args) {
        in1 obj=new AOPdemo(new inimp1()).getInstance();
        obj.sayHello();

    }
}
interface in1{
    void sayHello();
}
class inimp1 implements in1{

    @Override
    public void sayHello() {
        System.out.println("hello");

    }
}
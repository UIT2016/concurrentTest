package com.example.connectionpool.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author m
 * @date 2020/1/3 10:18
 */
public class oi {
    public static void main(String[] args) throws IOException {

        //同步阻塞：得不到结果不返回，线程进入阻塞态
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket accept = serverSocket.accept();//1
        InputStream inputStream = accept.getInputStream();
        BufferedReader br = new BufferedReader(new
                InputStreamReader(inputStream));
        String s = br.readLine();//2
        System.out.println(s);


                }
            }






package com.kyle.im;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        Socket socket;
        try {
            socket = new Socket("119.3.126.156", 8888);
//            socket = new Socket("localhost", 8888);

//            System.out.println("Android与服务器建立连接：" + socket);
//            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
//            writer.writeUTF("嘿嘿，你好啊，服务器.."); // 写一个UTF-8的信息
//            System.out.println("发送消息");

//            Thread.sleep(1000);
            //读取消息
            while (true){



                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String s = reader.readLine();
                    System.out.println(s);
            }
//            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        finally {
//            socket.close();
        }
    }

}

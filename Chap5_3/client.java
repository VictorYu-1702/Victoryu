package Chap5_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws Exception {


        InetAddress ia = InetAddress.getLocalHost();
        String ip = ia.getHostAddress();
        Socket socket = new Socket(ip, 9999);
        InputStreamReader reader = new InputStreamReader(socket.getInputStream());
        BufferedReader buffer_reader = new BufferedReader(reader);
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        Scanner input= new Scanner(System.in);//创造输入流对象

        int i;//操作序号
        //String order="";//用户输入的命令
        String response;//服务器端回复内容
        boolean f=true;
        while (f){
            System.out.print("1.注册用户   2.登录  3.发消息  4.注销登录  请选择：");
            i=input.nextInt();
            switch (i){
                case 1:    //注册
                    System.out.print("请输入命令(请以<register_name=”用户名”/>的格式输入)：");//<register name=”xu”/>
                    String order=input.next();
                    writer.println(order);
                    writer.flush();      //将输入的命令写入输出流，并发送给服务端
                    response = buffer_reader.readLine();
                    System.out.println("服务器返回消息:" + response);
                    break;
                case 2:   //登录
                    System.out.print("请输入命令(请以<login_name=”用户名”/>的格式输入)：");
                    order=input.next();
                    writer.println(order);
                    writer.flush();      //将输入的命令写入输出流，并发送给服务端
                    response = buffer_reader.readLine();
                    System.out.println("服务器返回消息:" + response);
                    break;
                case 3:   //发消息
                    System.out.print("请输入命令(请以<message_from=”用户名”_to=”用户名”_message=”消息內容”/>的格式输入)：");
                    order=input.next();
                    //System.out.println(order);
                    writer.println(order);
                    writer.flush();      //将输入的命令写入输出流，并发送给服务端
                    response = buffer_reader.readLine();
                    System.out.println("服务器返回消息:" + response);
                    break;
                case 4:  //注销登录
                    System.out.print("请输入命令(请以<logout_name=”用户名”/>的格式输入)：");
                    order=input.next();
                    writer.println(order);
                    writer.flush();      //将输入的命令写入输出流，并发送给服务端
                    response = buffer_reader.readLine();
                    System.out.println("服务器返回消息:" + response);
                    f=false;
            }
        }
        writer.close();
        buffer_reader.close();
        socket.close();

    }
}
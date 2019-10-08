package Chap5_3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(9999);
        while(true){
            System.out.println("服务器端开启成功");
            Socket socket=server.accept();
            sockethandler handler=new sockethandler(socket);
            Thread thread=new Thread(handler);
            thread.start();
        }
    }
}

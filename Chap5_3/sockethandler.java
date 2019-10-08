package Chap5_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class sockethandler implements Runnable {
    private Socket socket;

    public sockethandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStreamReader reader = null;
        BufferedReader buffer_reader = null;
        try {
            reader = new InputStreamReader(socket.getInputStream());
            buffer_reader = new BufferedReader(reader);
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            boolean f = true;
            while (f) {
                String request = buffer_reader.readLine();//读取的客户端发送的命令
                if (request.startsWith("<register")) {   //登录<register name=”xu”/>
                    String name = "";//用户名
                    for (int i = 0; i < request.length(); i++) {
                        if (request.charAt(i) == '”') {  //第一次出现引号，则下一个字母为名字开头
                            for (int j = i + 1; j < request.length(); j++) { //从名字开头第一个字母开始
                                if (request.charAt(j) == '”') {//第二次出现引号，停止增加名字字母，跳出循环
                                    i = j + 1;
                                    break;
                                }
                                name = name + String.valueOf(request.charAt(j));
                            }
                            break;
                        }
                        System.out.println(name);
                    }
                    if (name.equals(""))
                        writer.println("<result command=\"register\"  state=\"error\"  message=\"\"/>");//注册失败
                    else
                        writer.println("<result command=\"register\"  state=\"ok\"/>");//注册成功
                    writer.flush();
                } else if (request.startsWith("<login")) { //注册
                    String name = "";//用户名
                    for (int i = 0; i < request.length(); i++) {
                        if (request.charAt(i) == '"') {  //第一次出现引号，则下一个字母为名字开头
                            for (int j = i + 1; j < request.length(); j++) { //从名字开头第一个字母开始
                                if (request.charAt(j) == '"') {//第二次出现引号，停止增加名字字母，跳出循环
                                    i = j + 1;
                                    break;
                                }
                                name = name + String.valueOf(request.charAt(j));
                            }
                            break;
                        }
                    }
                    if (name.equals(""))
                        writer.println("<result command=\"login\"  state=\"error\"  message=\"\"/>");//注册失败
                    else
                        writer.println("<result command=\"login\"  state=\"ok\"/>");//登录成功
                    writer.flush();
                } else if (request.startsWith("<message")) { //会话
                    writer.println("<result command=\"message\"  state=\"ok\"/>");
                    writer.flush();
                }
                else if (request.startsWith("<logout")) { //注销登录
                    String name = "";//用户名
                    for (int i = 0; i < request.length(); i++) {
                        if (request.charAt(i) == '”') {  //第一次出现引号，则下一个字母为名字开头
                            for (int j = i + 1; j < request.length(); j++) { //从名字开头第一个字母开始
                                if (request.charAt(j) == '”') {//第二次出现引号，停止增加名字字母，跳出循环
                                    i = j + 1;
                                    break;
                                }
                                name = name + String.valueOf(request.charAt(j));
                            }
                            break;
                        }
                        System.out.println(name);
                    }
                    if (name.equals(""))
                        writer.println("<result command=\"logout\"  state=\"error\"  message=\"\"/>");//注册失败
                    else{
                        writer.println("<result command=\"logout\"  state=\"ok\"/>");//注销成功
                        f=false;
                        }
                    writer.flush();
                }
            }
            writer.close();
            buffer_reader.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("发生异常的语句为：" + e.getMessage());
        }

    }
}

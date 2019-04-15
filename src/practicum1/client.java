package practicum1;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.*;
import java.io.*;
import java.util.*;
class client {
    public static void main(String[] arg) throws Exception{
//        Socket s = new Socket("145.89.163.116",4711);
       Socket socket = new Socket("localhost",2222);
       OutputStream output = socket.getOutputStream();
       PrintWriter writer = new PrintWriter(output,true);
       String userName = "";
       while(!userName.equals("stop")){

           Scanner myObj = new Scanner(System.in);
           System.out.println("Enter sdfsdf");
           userName = myObj.nextLine();
           writer.println(userName);
       }
        socket.close();
    }
}



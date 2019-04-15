package practicum1;

import java.io.*;

import java.net.ServerSocket;
import java.net.Socket;
public class main {
    public static void main(String[] args) throws Exception{
       while(true){

           ServerSocket ss = new ServerSocket(2222);
           Socket clientserver = ss.accept();
           InputStream input = clientserver.getInputStream();
           InputStreamReader inputReader = new InputStreamReader(input);
           BufferedReader bufferedReader = new BufferedReader(inputReader);
           OutputStream output = clientserver.getOutputStream();
           PrintWriter writer = new PrintWriter(output,true);
           String clientInput = bufferedReader.readLine();
           writer.println("HTTP/1.1 200 OK\n\n<h1>It works!</h1>");
           writer.println("");

           while(clientInput.equals("")){


               System.out.println(
                       clientInput);
               clientInput = bufferedReader.readLine();


//           if(clientInput.contains("stop")){
////               break;
////           }


           }
           clientserver.close();
           ss.close();
       }
    }
}
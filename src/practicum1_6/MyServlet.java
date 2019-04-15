package practicum1_6;

import java.io.*;
import java.net.Socket;

public class MyServlet extends Thread{
    private Socket socket;
    public MyServlet(Socket sock){
        socket = sock;
    }
    public void run(){
//        System.out.println("sadfsadf");
        try{

            InputStream input = socket.getInputStream();
            InputStreamReader inputReader = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output,true);
            String clientInput = bufferedReader.readLine();
            writer.println("HTTP/1.1 200 OK\n\n<h1>It works!</h1>");
            writer.println("");
            Thread.sleep(10000);

//            while(!clientInput.equals("")){
//                System.out.println(clientInput);
//                clientInput = bufferedReader.readLine();
//            }
            socket.close();


        }catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

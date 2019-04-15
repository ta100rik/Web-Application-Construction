package practicum1_6;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
            while(true){
                ServerSocket ss = new ServerSocket(2222);
                Socket clientserver = ss.accept();
                MyServlet MyServlet = new MyServlet(clientserver);
//                System.out.println(MyServlet);
                MyServlet.start();

//                clientserver.close();
                ss.close();
            }

    }
}

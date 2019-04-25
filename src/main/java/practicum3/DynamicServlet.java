package practicum3;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import static java.lang.Float.parseFloat;

public class DynamicServlet extends HttpServlet {
    private float res;
    private String type;
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String plusus = req.getParameter("plusus");
        String minus = req.getParameter("minus");
        String gedeeldorus = req.getParameter("gedeeldorus");
        String keerus = req.getParameter("keerus");
        Float getal1 = Float.parseFloat(req.getParameter("getal1"));
        Float getal2 = Float.parseFloat(req.getParameter("getal2"));

        if(plusus != null){
            res = getal1 + getal2;
            type = "+";
            //it is pluss
        }else if(minus != null){
            //it is minus
            res = getal1 - getal2;
            type = "-";
        }else if(gedeeldorus != null){
            // geceeld door
            res = getal1 / getal2;
            type = "/";
        }else if(keerus != null){
            //keerus
            res = getal1 * getal2;
            type = "X";
        }
        System.out.println(res);
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("  <title>Dynamic Example</title>");
        out.println("  <body>");
        out.println("    <h2>Rekenmachine</h2>");
        out.println("    <h2>de som \"" + getal1 + " " +type +" "+getal2+"\" komt uit op "+res+"</h2>");
        out.println("  </body>");
        out.println("</html>");
    }
}

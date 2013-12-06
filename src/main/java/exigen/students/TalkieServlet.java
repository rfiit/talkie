package exigen.students;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class TalkieServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest rq, HttpServletResponse rp) throws IOException, ServletException{
        if(rq.getParameter("type") != null && rq.getParameter("type").equals("ajax")) {
            try {
                getServletConfig().getServletContext().getNamedDispatcher("ReplyGeneratorServlet").include(rq,rp);
                return;
            } catch (Exception e) {
//                PrintWriter writer = rp.getWriter();
//                writer.println(e.getStackTrace());
//                writer.close();
                //nothing to do
            }
        }
        PrintWriter writer = rp.getWriter();
        writer.println("<html>\n<head>\n<meta charset=\"UTF-8\">\n<title>Talkie Web App</title>\n<script src=\""+rq.getContextPath()+"/resources/js/ajax.js\"></script></head>\n");
//        writer.println("<body><form>\n request: ");
//        writer.println("<noscript><form></noscript>\n");
        writer.println("<body>\n request: ");
        writer.println("<input type=\"text\" id=\"request\" name=\"request\" value=\"" + ((rq.getParameter("request") == null) ? "" : rq.getParameter("request")) +"\">\n");
        writer.println("<script>document.write(\"<button onclick=\\\"getAjaxResponse(document.getElementById('request').value, 'response')\\\">Submit</button>\");</script><br>\n");
//        writer.println("<noscript><button>Submit</button></noscript>\n");
//        writer.println("<noscript><form></noscript>\n");
        writer.println("<div style=\"float: left;\">response:&nbsp</div>");
        writer.println("<div id=\"response\" style=\"float: left;color:green;\">");

        if(rq.getParameter("request") != null) {
            getServletConfig().getServletContext().getNamedDispatcher("ReplyGeneratorServlet").include(rq,rp);
        }

        writer.println("</div>\n");
        writer.println("</body></html>");
        writer.close();
    }
}

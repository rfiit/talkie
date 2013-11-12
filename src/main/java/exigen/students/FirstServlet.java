package exigen.students;

import javax.servlet.http.HttpServlet;
import java.io.PrintWriter;

public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException {

        PrintWriter writer = resp.getWriter();
        writer.println("<h1>Yeah, " + ((req.getParameter("test") == null) ? "b***** !!1" : req.getParameter("test")) +" <h1>");
        writer.close();

    }
}

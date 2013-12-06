package exigen.students;

import javax.servlet.*;
import java.io.IOException;


public class ReplyGeneratorServlet extends GenericServlet {

    private ReplyGenerator replygenerator;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        this.replygenerator = new DefaultReplyGenerator();
    }

    @Override
    public void service(ServletRequest rq, ServletResponse rp) throws ServletException, IOException {
        rp.getWriter().println(replygenerator.generate() +
                "(request length = " + (rq.getParameter("request") == null ? "0" : rq.getParameter("request").length()) + ")");
        //sp.getWriter().close();
    }


//    @Override   // hope delete replygenerator
//    public void destroy() {
//
//    }
}
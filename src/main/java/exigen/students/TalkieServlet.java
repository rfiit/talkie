package exigen.students;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class TalkieServlet extends HttpServlet {

    ReplyGenerator replygenerator;

    public void init() throws ServletException {
        super.init();
        this.replygenerator = new DefaultReplyGenerator();
        //getServletContext().setAttribute("QCounter", new Integer(0));
    }

    @Override
    public void doGet(HttpServletRequest rq, HttpServletResponse rp) throws IOException, ServletException{
        if(rq.getParameter("request") == null)
            rp.sendRedirect("index.jsp");
        //rp.sendRedirect("error.html");
        String q = replygenerator.generate();
        addQuestion(getQuestionsFromSession(rq.getSession()), rq.getParameter("request"), q);
        rq.getSession().setAttribute("MyQCounter",((Integer)rq.getSession().getAttribute("MyQCounter"))+1);
        rp.getWriter().println(q);
    }

    private Map<String, QuestionAnswer> getQuestionsFromSession(HttpSession session) {
        Map<String, QuestionAnswer> questions = (Map<String, QuestionAnswer>) session.getAttribute("questions");
        if (questions == null) {
            questions = new LinkedHashMap<String, QuestionAnswer>();
            session.setAttribute("questions", questions);
        }
        return questions;
    }

    private boolean addQuestion(Map<String, QuestionAnswer> savedQuestions, String question, String answer) {
        if (StringUtils.isEmpty(question) || StringUtils.isEmpty(answer)) {
            return false;
        }
        String id = generateNewId(savedQuestions);
        savedQuestions.put(id, new QuestionAnswer(question, answer));
        return true;
    }

    private String generateNewId(Map<String, QuestionAnswer> savedQuestions) {
        Integer count = (Integer)getServletContext().getAttribute("QCounter");
        if (count == null) {
            getServletContext().setAttribute("QCounter", new Integer(0));
            count = 0;
        }
        count++;
        while (savedQuestions.containsKey(count+"")) {
            count++;
        }
        getServletContext().setAttribute("QCounter", new Integer(count));
        return String.valueOf(count);
    }
}

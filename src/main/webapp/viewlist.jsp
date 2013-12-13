<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="exigen.students.QuestionAnswer" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>

<%

LinkedHashMap<String, QuestionAnswer> qmap = (LinkedHashMap<String, QuestionAnswer>)request.getSession().getAttribute("questions");
if (qmap != null) {
Set<Map.Entry<String,QuestionAnswer>> qset = qmap.entrySet();
    %>
        <div style="margin:auto; width:100%; text-align:center; font-size:30px;">Последние вопросы:</div>
    <%
    for (Map.Entry<String,QuestionAnswer> q : qset) { QuestionAnswer qa = q.getValue();

%>
        <div class="somequestion">
        <table>
            <tr class="question">
                <td class="field">Вопрос:</td>
                <td><%= qa.question %></td>
            </tr>
            <tr class="answer">
                <td>Ответ:</td>
                <td><%= qa.answer %></td>
            </tr>
        </table>
        </div>
<%}}%>
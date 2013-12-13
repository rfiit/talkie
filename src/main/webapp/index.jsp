<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
if (((Integer)getServletContext().getAttribute("QCounter")) == null) getServletContext().setAttribute("QCounter", new Integer(0));
if (request.getSession().getAttribute("MyQCounter") == null) request.getSession().setAttribute("MyQCounter", new Integer(0));
%>

<html>
<head>
<title>Jsp Web App</title>
<%@ include file="htmlheadtag.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<body>
<div id="maindiv">
<%@ include file="header.html" %>
<div id="content">
    <div id="statistics">
        <div id="stats">
            <div id="allreqcount"><%= ((Integer)getServletContext().getAttribute("QCounter"))  %></div>
            <div id="allstatmess">Всего -&nbsp</div>
        </div>
        <div id="slash">/</div>
        <div id="stats">
            <div id="myreqcount"><%= ((Integer)request.getSession().getAttribute("MyQCounter"))  %></div>
            <div id="mystatmess">&nbsp- Вы спросили</div>
        </div>
    </div>

    <%@ include file="newquestion.html" %>

    <div id="lastquestions">
        <div class="somequestion">
            <table>
                <tr class="question">
                    <td class="field">Вопрос:</td>
                    <td><div id="qmess"></div></td>
                </tr>
                <tr class="answer">
                    <td>Ответ:</td>
                    <td><div id="amess"></div></td>
                </tr>
            </table>
        </div>
    </div>
    <div id="viewlistdiv">
        <%@ include file="viewlist.jsp" %>
    </div>
</div>
<%@ include file="bottom.html" %>
</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %> 
<html>
<body>
<%@ include file="common/include.jsp"%>
<h2>Hello Portal 中文!</h2>
<%
   Date date = new Date();
   out.print( "<h2 align=\"center\">" +date.toString()+"</h2>");
%>
</body>
</html>

<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglibs.jsp"%>
<%
    ArrayList<String> listKey = (ArrayList<String>)request.getAttribute("keys");
    ArrayList<HashMap> data = (ArrayList<HashMap>)request.getAttribute("data");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Crizen Loan Counsel Agency</title>

</head>
<body>
<form action="deqlaction.jsp">
    <table border="1">
        <thead>
        <%
            String key = null;
            for(int i=0;i<listKey.size();i++) {
                key = listKey.get(i);
        %>
        <th>
            <%=key%>
        </th>
        <%
            }
        %>
        </thead>
        <tbody>
        <%
            HashMap oneRowMap = null;
            Object value = null;
            for(int i=0;i<data.size();i++) {
                oneRowMap = data.get(i);
                %>
        <tr>
        <%
                for(int j=0;j<listKey.size();j++) {
                    key = listKey.get(j);
                    value = oneRowMap.get(key);
        %>
            <td><%=value%></td>
        <%
                }
                %>
        </tr>
            <%
            }
        %>
        </tbody>

    </table>
</form>
</body>
</html>
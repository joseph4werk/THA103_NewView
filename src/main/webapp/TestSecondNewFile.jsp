<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tha103.newview.act.*" %>
<%@ page import="com.tha103.newview.act.model.*" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>顯示活動列表</title>
</head>
<body>
    <h1>活動列表</h1>
    
    <%
        // 實例化 DAO 物件
        ActDAO dao = new ActDAOHibernateImpl();

        // 從後端 Java 類別取得活動列表
        List<Act> list = dao.getAll();
    %>
    
    <table border="1">
        <tr>
            <th>ID</th>
            <th>名稱</th>
            <th>價格</th>
            <th>類別ID</th>
            <!-- 其他欄位 -->
        </tr>
        <%
            for (Act act : list) {
        %>
        <tr>
            <td><%= act.getActID() %></td>
            <td><%= act.getActName() %></td>
            <td><%= act.getActPrice() %></td>
            <td><%= act.getActCategoryID() %></td>
            <!-- 其他欄位 -->
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>

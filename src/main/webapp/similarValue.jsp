<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Statistics</title></head>
<body>
<jsp:include page="common/header2.jsp"></jsp:include>
<%
  int[] b = {
    request.getAttribute("band0") == null ? 0 : (Integer) request.getAttribute("band0"),
    request.getAttribute("band1") == null ? 0 : (Integer) request.getAttribute("band1"),
    request.getAttribute("band2") == null ? 0 : (Integer) request.getAttribute("band2"),
    request.getAttribute("band3") == null ? 0 : (Integer) request.getAttribute("band3"),
    request.getAttribute("band4") == null ? 0 : (Integer) request.getAttribute("band4"),
    request.getAttribute("band5") == null ? 0 : (Integer) request.getAttribute("band5") };
  String[] labels = {"0-19%", "20-39%", "40-59% (stored)", "60-79% (rejected)", "80-99% (rejected)", "100% exact (rejected)"};
  int total = 0; for (int v : b) total += v;
  int stored = b[0] + b[1] + b[2];
%>
<div class="card wide">
  <h2>Similarity Statistics</h2>
  <p>Total uploads checked: <b><%= total %></b> &nbsp;|&nbsp; Stored: <b><%= stored %></b>
     &nbsp;|&nbsp; Rejected as redundant: <b><%= total - stored %></b></p>
  <table>
    <tr><th style="width:200px">Similarity</th><th>Uploads</th></tr>
    <% for (int i = 0; i < 6; i++) {
         int pct = total == 0 ? 0 : (int) Math.round(100.0 * b[i] / total); %>
    <tr>
      <td><%= labels[i] %></td>
      <td><div class="bar"><span class="<%= i >= 3 ? "red" : "" %>" style="width:<%= Math.max(pct, b[i] > 0 ? 6 : 0) %>%"><%= b[i] %></span></div></td>
    </tr>
    <% } %>
  </table>
</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<div class="nav">
  <a class="brand" href="${pageContext.request.contextPath}/home.jsp">ICID Dedup</a>
  <div class="links">
    <a href="${pageContext.request.contextPath}/home.jsp">Home</a>
    <a href="${pageContext.request.contextPath}/uploadDataSet.jsp">Upload</a>
    <a href="${pageContext.request.contextPath}/myPicked">View Data</a>
    <a href="${pageContext.request.contextPath}/similarityValue">Statistics</a>
    <a href="${pageContext.request.contextPath}/logout">Logout (<c:out value="${sessionScope.uname}"/>)</a>
  </div>
</div>

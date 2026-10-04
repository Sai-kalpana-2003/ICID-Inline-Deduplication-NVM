<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Home</title></head>
<body>
<jsp:include page="common/header2.jsp"></jsp:include>
<div class="hero">
  <h1>NVM</h1>
  <p>Non-volatile memory plays a crucial role in modern computing, providing a reliable means of
     storing data that persists through power cycles and system reboots.</p>
</div>
<div class="card wide">
  <h2>Welcome, <c:out value="${sessionScope.uname}"/></h2>
  <div class="tiles">
    <a class="tile" href="uploadDataSet.jsp">Upload data</a>
    <a class="tile" href="myPicked">View my stored data</a>
    <a class="tile" href="similarityValue">Similarity statistics</a>
  </div>
</div>
</body>
</html>

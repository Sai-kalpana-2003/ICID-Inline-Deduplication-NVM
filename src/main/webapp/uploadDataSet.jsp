<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Upload</title></head>
<body>
<jsp:include page="common/header2.jsp"></jsp:include>
<div class="card">
  <h2>Upload Data</h2>
  <p style="font-size:14px">Upload a text file (or paste text). If it is 60% or more similar to data that is
     already stored, it is not uploaded.</p>

  <c:if test="${not empty alert}"><div class="alert info"><c:out value="${alert}"/></div></c:if>

  <c:if test="${not empty result}">
    <div class="alert ${result.stored ? 'ok' : 'bad'}">
      <b>String Comparison Result: ${result.similarity}%</b><br>
      <c:out value="${result.message}"/>
    </div>
  </c:if>

  <form action="uploadDataset" method="post" enctype="multipart/form-data">
    <label>File Name</label><input type="text" name="dataSetName" placeholder="e.g. notes1">
    <label>Upload file (.txt)</label><input type="file" name="dataSetFile" accept=".txt,text/plain">
    <label>...or paste text</label><textarea name="dataSet"></textarea>
    <input class="btn" type="submit" value="Upload">
  </form>
</div>
</body>
</html>

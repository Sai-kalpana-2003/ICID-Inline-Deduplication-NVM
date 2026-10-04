<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>My Data</title></head>
<body>
<jsp:include page="common/header2.jsp"></jsp:include>
<div class="card wide">
  <h2>My Stored Data</h2>
  <c:choose>
    <c:when test="${empty list}"><p>Nothing stored yet. <a href="uploadDataSet.jsp">Upload some data</a>.</p></c:when>
    <c:otherwise>
      <table>
        <tr><th>#</th><th>Name</th><th>Preview</th><th>Uploaded on</th></tr>
        <c:forEach var="d" items="${list}">
          <tr>
            <td><c:out value="${d.id}"/></td>
            <td><c:out value="${d.dataset_name}"/></td>
            <td><c:out value="${d.dataset.length() > 120 ? d.dataset.substring(0,120).concat('...') : d.dataset}"/></td>
            <td><c:out value="${d.uploaded_on}"/></td>
          </tr>
        </c:forEach>
      </table>
    </c:otherwise>
  </c:choose>
</div>
</body>
</html>

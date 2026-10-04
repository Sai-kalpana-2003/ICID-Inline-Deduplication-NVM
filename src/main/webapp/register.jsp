<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Register</title></head>
<body>
<jsp:include page="common/header1.jsp"></jsp:include>
<div class="card">
  <h2>Please Register</h2>
  <c:if test="${not empty alert}"><div class="alert bad"><c:out value="${alert}"/></div></c:if>
  <form action="register" method="post">
    <label>Name (used to log in)</label><input type="text" name="name" required>
    <label>Email</label><input type="email" name="email" required>
    <label>Password (min 6 characters)</label><input type="password" name="password" minlength="6" required>
    <label>Mobile</label><input type="text" name="mobile">
    <label>Gender</label>
    <label style="font-weight:normal"><input type="radio" name="gender" value="male"> Male</label>
    <label style="font-weight:normal"><input type="radio" name="gender" value="female"> Female</label>
    <label style="font-weight:normal"><input type="radio" name="gender" value="other" checked> Other</label>
    <label>Place</label><input type="text" name="address">
    <input class="btn" type="submit" value="Register">
  </form>
</div>
</body>
</html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Contact list</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/list.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2>
            <a onclick="document.forms['logoutForm'].submit()"  class="btn btn-info btn-lg" style="margin-left: 100%">
                <span class="glyphicon glyphicon-log-out"></span> Log out
            </a>
        </h2>
    </c:if>
</div>


<div class="container">
    <div id="users">
        <input class="search" placeholder="Search" style="width: 100%"/>
    <table class="table table-hover">
        <thead>
        <tr>
            <th style="text-align: center">Name</th>
            <th style="text-align: center">Mobile phone</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody class="list" >
        <c:forEach var="record" items="${records}">
            <tr>
                <td class="name">${record.name} ${record.surname} ${record.patronymic}</td>
                <td class="phone" style="text-align: center">${record.mobilePhone}</td>
                <spring:url value="/delete/${record.id}" var="deleteUrl" />
                <spring:url value="/workflow/${pageContext.request.userPrincipal.name}/${record.id}" var="updateUrl" />
                <td style="text-align: center">   <button class="btn btn-primary"
                               onclick="location.href='${updateUrl}'">Update</button></td>
                <td style="text-align: center">   <button class="btn btn-danger"
                               onclick="location.href=('${deleteUrl}')">Delete</button></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>
</div>
<script src="//cdnjs.cloudflare.com/ajax/libs/list.js/1.5.0/list.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/list.js"></script>


</body>
</html>
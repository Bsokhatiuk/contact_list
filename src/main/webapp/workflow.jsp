<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="message" value="${message}"/>
<c:set var="record" value="${record}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Add contact</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</head>
<body>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h2>
            <a onclick="document.forms['logoutForm'].submit()" class="btn btn-info btn-lg" style="margin-left: 100%">
                <span class="glyphicon glyphicon-log-out"></span> Log out
            </a>
        </h2>
        <h3>
            <input type="button" class="btn btn-lg btn-primary" value="Show contact list" style="margin-left: 40%"
                   onclick='location.href="/contact_list/${pageContext.request.userPrincipal.name}"'/>
        </h3>
    </c:if>
</div>


<div class="container">
    <form:form method="POST" modelAttribute="newRecordForm" class="form-signin">

        <div style="margin-left: 30%;width: 35%">
            <h3 class="form-signin-heading">Add new record</h3>
            <spring:bind path="name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="name" class="form-control" placeholder="Name*"
                                autofocus="true" value="${record.name}"></form:input>
                    <form:errors path="name"></form:errors>
                </div>
            </spring:bind>


            <spring:bind path="surname">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="surname" class="form-control" placeholder="Surname*"
                                value="${record.surname}"></form:input>
                    <form:errors path="surname"></form:errors>

                </div>
            </spring:bind>

            <spring:bind path="patronymic">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="patronymic" class="form-control" placeholder="Patronymic*"
                                value="${record.patronymic}"></form:input>
                    <form:errors path="patronymic"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="mobilePhone">
                <div class="form-group ${status.error ? 'has-error' : ''}">

                    <form:input path="mobilePhone" type="text" class="input-medium bfh-phone form-control" data-format="+380 (dd) ddd-dd-dd"
                                value="${record.mobilePhone}"></form:input>
                    <form:errors path="mobilePhone"></form:errors>
                    <h6><span>Mobile*</span></h6>


                </div>
            </spring:bind>

            <spring:bind path="homePhone">
                <div class="form-group">
                    <form:input type="text" path="homePhone" class="input-medium bfh-phone form-control" data-format="+380 (dd) ddd-dd-dd"
                                placeholder="Home Phone"
                                value="${record.homePhone}"></form:input>
                    <h6><span>Home</span></h6>
                </div>
            </spring:bind>

            <spring:bind path="address">
                <div class="form-group">
                    <form:input type="text" path="address" class="form-control" placeholder="Address"
                                value="${record.address}"></form:input>
                </div>
            </spring:bind>

            <spring:bind path="email">
                <div class="form-group">
                    <form:input type="email" path="email" class="form-control" placeholder="Email"
                                value="${record.email}"></form:input>
                </div>
            </spring:bind>
            <span>${message}</span>
            <input type="hidden" name="username" id="username" value="${pageContext.request.userPrincipal.name}"/>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </div>
        >
    </form:form>
</div>
<!-- /container -->
<script src="//cdnjs.cloudflare.com/ajax/libs/list.js/1.5.0/list.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap-formhelpers-phone.js"></script>
</body>
</html>

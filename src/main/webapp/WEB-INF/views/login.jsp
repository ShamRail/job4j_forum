<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

</head>
<body class="text-center">
<div class="row">
    <div class="col-4"></div>
    <div class="col-4">
        <form class="form-signin" action="<c:url value='/login'/>" method='POST'>
            <br>
            <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
            <c:if test="${not empty errorMsg}">
                <div style="color:red; font-weight: bold; margin: 30px 0px;">
                        ${errorMsg}
                </div>
            </c:if>
            <label for="inputEmail" class="sr-only">Username:</label>
            <input type="text" id="inputEmail" class="form-control" placeholder="Email address" name="username" required="" autofocus="">
            <br>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" required="">
            <br>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
            <br>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <a href="<c:url value='/reg'/>">Нет учетной записи? Создать</a>
        </form>
    </div>
    <div class="col-4"></div>
</div>
</body>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>


</html>
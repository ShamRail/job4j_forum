<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    <title>Обсуждение</title>
</head>
<body>

<div class="container">
    <br>
    <div class="row">
        <div class="container border border-primary rounded">
            <p><b>Тема:</b> <c:out value="${post.theme.name}"/></p>
            <p><b>Пост:</b> <c:out value="${post.name}"/></p>
            <p><b>Дата создания:</b> <c:out value="${post.formattedDateTime()}"/></p>
            <p><b>Автор:</b> R.Shamsemukhametov</p>
            <p><b>Описание:</b><br> <c:out value="${post.desc}"/></p>
        </div>
    </div>
    <br>
    <c:forEach items="${post.comments}" var="comment">
        <div class="row">
            <div class="container border border-warning rounded">
                <p><b>Автор:</b> R.Shamsemukhametov <b>Время:</b> <c:out value="${comment.formattedDateTime()}"/></p>
                <p><c:out value="${comment.text}"/></p>
            </div>
        </div>
        <br>
    </c:forEach>
    <div class="row">
        <form class="container" action="/theme/${post.theme.id}/post/${post.id}/create" method="post">
            <div class="form-group">
                <textarea name="text" class="form-control" placeholder="Введите текст комментария" cols="100" rows="5"></textarea>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Оставить комментарий</button>
            </div>
        </form>
    </div>
    <br>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

</body>
</html>
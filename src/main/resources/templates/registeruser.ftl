<!doctype html>
<html lang="en">
<head>
    <#include "bootstrapheader.ftl">
    <link rel="stylesheet" href="/assets/css/signin.css">
    <title>Please login</title>
</head>

<body class="text-center">

<form class="form-signin" action="/register" method="POST">

    <h1 class="h1 mb-3 font-weight-normal">Online Shop</h1>
    <h1 class="h3 mb-3 font-weight-normal">Please register</h1>

    <label for="inputUserName" class="sr-only">New user login</label>
    <input type="text" id="inputUserName" name="inputLogin" class="form-control"
           placeholder="User name" required autofocus>

    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" name="inputPassword" class="form-control"
           placeholder="Password" required>

    <label for="inputPassword" class="sr-only">Check password</label>
    <input type="password" id="inputCheckPassword" name="inputCheckPassword" class="form-control"
           placeholder="Password" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
</form>

<#include "bootstrapfooter.ftl">
</body>
</html>

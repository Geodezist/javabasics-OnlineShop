<!doctype html>
<html lang="en">
<head>
    <#include "bootstrapheader.ftl">
    <link rel="stylesheet" href="/assets/css/signin.css">
    <title>Please login</title>
</head>

<body class="text-center">

<form class="form-signin" action="/login" method="POST">

    <h1 class="h1 mb-3 font-weight-normal">Online Shop</h1>
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <label for="inputUserName" class="sr-only">User Login</label>
    <input type="text" id="inputUserName" name="inputUserName" class="form-control"
           placeholder="User name" required autofocus>

    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" name="inputPassword" class="form-control"
           placeholder="Password" required>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
</form>

<#include "bootstrapfooter.ftl">
</body>
</html>

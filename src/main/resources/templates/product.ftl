<!doctype html>
<html lang="en">
<head>
    <#include "bootstrapheader.ftl">
    <link rel="stylesheet" href="/assets/css/style.css">
    <title><#if product??>${product.name} card<#else>Product is absent</#if></title>
</head>
<body>
<#include "header.ftl">
<h1>Product:</h1>

<br>
<#if product??>
<div class="card" style="width: 18rem;">
    <img class="card-img-top" width="200" height="200" src="${product.picturePath}" alt="${product.name}">
    <div class="card-body">
        <h5 class="card-title">${product.name}</h5>
        <p class="card-text">${product.price}</p>
    </div>
</div>

<#else>Product is absent</#if>
<br>
<#include "footer.ftl">
<#include "bootstrapfooter.ftl">
</body>
</html>
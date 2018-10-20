<!doctype html>
<html lang="en">
<head>
    <#include "bootstrapheader.ftl">
    <link rel="stylesheet" href="/assets/css/style.css">
    <title>List of all products</title>
</head>

<body>
<#include "header.ftl">
<h1>Products:</h1>

<table class="table table-striped table-hover table-sm">
    <thead>
    <tr>
        <th scope="col" style="width: 5%">#</th>
        <th scope="col" style="width: 200px"></th>
        <th scope="col">Name</th>
        <th scope="col" style="width: 15%">Price</th>
        <th scope="col" style="width: 15%" class="text-center">Actions</th>
    </tr>
    </thead>

    <tbody>
    <#list products as product>
    <tr>
        <th scope="row" class='clickable-row' data-href="/product/${product.id}">${product.id}</th>
        <td class='clickable-row'
            data-href="/product/${product.id}"><img src="${product.picturePath}"
                                                    class="img-fluid"
                                                    width="200" height="200"
                                                    alt="${product.name}"></td>
        <td class='clickable-row' data-href="/product/${product.id}"> ${product.name}</td>
        <td class='clickable-row' data-href="/product/${product.id}"> ${product.price}</td>
        <td class="text-center">
            <a class="btn btn-outline-primary btn-sm" href="/product/edit/${product.id}">Edit</a>
            <a class= "btn btn-outline-danger btn-sm" href="/product/delete/${product.id}">Delete</a>
        </td>
    </tr>
    </#list>
    </tbody>
</table>

<#include "footer.ftl">
<#include "bootstrapfooter.ftl">
<script>
    jQuery(document).ready(function ($) {
        $(".clickable-row").click(function () {
            window.location = $(this).data("href");
        });
    });
</script>
</body>
</html>
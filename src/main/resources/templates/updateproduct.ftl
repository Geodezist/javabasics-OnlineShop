<!doctype html>
<html lang="en">
<head>
    <#include "bootstrapheader.ftl">
    <link rel="stylesheet" href="/assets/css/style.css">
    <title>Edit product</title>
</head>
<body>
<#include "header.ftl">
<h1>Edit product:</h1>

<#if operationSuccess??>
    <h6 class="text-success">${operationSuccess}</h6>
    <br>
<#else>
</#if>

<form action="/product/edit/${product.id}" method="POST">
    <div class="form-group">
        <label for="productNameLabel">Name of the product</label>
        <input type="text" class="form-control" name="productName" maxlength="250" aria-describedby="productNameHelp"
               value="${product.name}">
        <small name="productNameHelp" class="form-text text-muted">Here you can enter product name. Maximum size of the
            name is 250 chars.
        </small>
    </div>
    <div class="form-group">
        <label for="priceLabel">Price of the product</label>
        <input type="number" step="0.01" class="form-control" name="price"
               value="${product.price?string(",##0.00")}">
    </div>
    <div class="form-group">
        <label for="picturePathLabel">Picture of the product link</label>
        <input type="text" class="form-control" name="picturePath" aria-describedby="picturePathHelp"
               value="${product.picturePath}">
        <small name="picturePathHelp" class="form-text text-muted">Link must be with &quot;http&ratio;&#8725;&#8725;&quot;
            part!
        </small>
    </div>
    <button type="submit" class="btn btn-primary">Save</button>
</form>
<br>

<#include "footer.ftl">
<#include "bootstrapfooter.ftl">
</body>
</html>
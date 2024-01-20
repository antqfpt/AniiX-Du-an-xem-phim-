<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: buidu
  Date: 20/1/2024
  Time: 11:57 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="../decorator/head/AdminHead.jsp"%>>
</head>
<body class="sb-nav-fixed">
<%@include file="../decorator/header/AdminHeader.jsp"%>

<div id="layoutSidenav">
    <%@include file="../decorator/SideBar/SideBar.jsp"%>

    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Add Anime</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item active">Upload an anime</li>
                </ol>

                <form:form action="/admin/upload-flim" method="post" modelAttribute="flim" class="form-control">
                    <div class="mb-3">
                        <label for="name" class="form-label">Name:</label>
                        <form:input path="name" class="form-control"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Categories:</label>
                        <div class="form-check">
                            <form:checkboxes path="categories" items="${categories}" itemLabel="name" itemValue="id" />
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Tags:</label>
                        <div class="form-check d-flex">
                            <form:checkboxes path="tags" items="${tagsList}" itemLabel="tag" itemValue="id" />
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="desc" class="form-label">Desc:</label>
                        <form:textarea class="form-control" id="desc" rows="3" path="description"/>
                    </div>
                    <div class="mb-3">
                        <form:select path="country" cssClass="form-select">
                            <form:options items="${countryList}" itemValue="id" itemLabel="name"/>
                        </form:select>
                    </div>
                    <div class="mb-3">
                        <label for="formFile" class="form-label">Default file input example</label>
                        <input class="form-control" type="file" id="formFile">
                    </div>
                    <div class="d-flex justify-content-lg-end">
                        <button type="submit" class="btn btn-success align-items-end me-2">Submit</button>
                        <button type="button" class="btn btn-danger">Cancel</button>
                    </div>
                </form:form>
            </div>
        </main>

        <%@include file="../decorator/footer/AdminFooter.jsp"%>
    </div>
</div>




<%@include file="../decorator/scrpit/AdminScprit.jsp"%>
</body>
</html>

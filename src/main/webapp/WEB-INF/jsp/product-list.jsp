<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <link>
</head>
<body>

    <form:form modelAttribute="searchForm">
        <form:input path="searchText" />
        <input type="submit" value="Search" />
    </form:form>

    <ul>
    <c:forEach items="${products}" var="prod">
        <li>
            <h3>${prod.title} ( ${prod.balance} )</h3>
            <p>${prod.description}</p>
            <a href="/product/${prod.id}">Edit</a>
        </li>
    </c:forEach>
    </ul>

    <div>
        <a href="/product">New product</a>
    </div>

</body>
</html>

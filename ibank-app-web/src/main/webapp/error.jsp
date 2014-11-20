
<!DOCTYPE html>
<%@ page language="java" contentType="text/html"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="mytag" uri="stylemes" %>
<jsp:include page="/template/header.jsp"/>
<hr/> 
<div id="content-box-right">
    <div id="content-box-right-in">
        <fmt:setLocale value="${requestScope.language}"/>
        <fmt:setBundle basename="messages"/>
        <fmt:message key="servleterror" var="servlexc"/>
        <mytag:messageTag rendered="${requestScope.errorMessage!=null}"  value="${requestScope.errorMessage}" id="error"/> 
        <mytag:messageTag rendered="${param.servletexc!=null}"  value="${servlexc}" id="error"/>         
    </div>
</div>

<jsp:include page="/template/footer.jsp"/>   


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="mytag" uri="stylemes" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en,ru" lang="en,ru">    
    <fmt:setLocale value="${requestScope.language}"/>
    <fmt:setBundle basename="messages"/>
    <head>
        <meta name="Description" content="Ibank system" />
        <meta name="Keywords" content="bank,money,transfer" />
        <meta name="robots" content="NOINDEX" />
        <meta name="author" content="Dmitry Drepin" />        
        <c:set value="${initParam.simplurl}/login.jsp?expired=true" var="linkredirect"/>
        <meta http-equiv="refresh" content="${pageContext.session.maxInactiveInterval};url=${linkredirect}" />
        <meta http-equiv="Content-Script-Type" content="text/javascript" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />        
        <!-- CSS -->
        <link rel="stylesheet" href="./resources/css/style.css" type="text/css" media="screen, projection, tv" />
        <link rel="stylesheet" href="./resources/css/style-print.css" type="text/css" media="print" />       
        <title><fmt:message key="ibankName"/></title>
    </head>
    <body>
        <c:url value="${initParam.urlController}" var="urllogout">
            <c:param name="command" value="logout" />                                        
        </c:url>
        <div id="wrapper">
            <!-- Header -->
            <div id="header-wrapper"> <!--Matrjoska hack for IE quirk box model by Pixy (Petr Stanicek) [http://www.pixy.cz/]  -->
                <div id="header">
                    <!-- Your website name  -->
                    <h1><a href="${initParam.url}"><fmt:message key="ibankName"/></a></h1>
                    <!-- Your website name end -->
                    <!-- Your slogan -->
                    <h2><fmt:message key="slogan"/>&hellip;</h2>
                    <!-- Your slogan end -->  
                    <div class="logout">     
                        <mytag:messageTag rendered="${sessionScope.user!=null}"> 
                            <fmt:message key="usernme"/>${sessionScope.user}  &nbsp;&nbsp; 
                            <a href="${urllogout}">
                                <fmt:message key="logout"/>
                            </a>                                                         
                        </mytag:messageTag> 
                    </div>                   
                </div>                    
            </div>
            <!-- Header end -->
            <div id="content-box">
                <div id="content-box-in">

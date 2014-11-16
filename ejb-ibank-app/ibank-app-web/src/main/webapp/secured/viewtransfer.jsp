
<!DOCTYPE html>
<%@ page language="java" contentType="text/html"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="mytag" uri="stylemes" %>
<jsp:include page="/template/header.jsp"/>  
<div id="content-box-right">
    <div id="content-box-right-in">
<fmt:setLocale value="${requestScope.language}"/>
 <fmt:setBundle basename="messages"/>
 <jsp:useBean id="transfers" class="java.util.ArrayList" scope="request"/>
       <div class="sptable">
        <table>
            <thead>
                <tr>
                    <td>#</td>
                    <td><fmt:message key="transfernum"/></td>
                    <td><fmt:message key="namename"/></td>
                    <td><fmt:message key="Accountnum"/></td>            
                    <td><fmt:message key="sumname"/></td>
                    <td><fmt:message key="datename"/></td>
                    <td><fmt:message key="statusname"/></td>                    
                </tr>
            </thead>

            <tbody>
                <c:if test="${transfers != null}">
                <c:forEach var="trf" items="${transfers}" varStatus="i">
                <c:choose>
                    <c:when test="${(i.count) % 2 == 0}">
                        <tr class="even">
                    </c:when>
                    <c:otherwise>
                        <tr class="odd">
                    </c:otherwise>
                </c:choose>
                <td>${i.count}</td>
                <td>${trf.id}</td>
                <td>${trf.name}</td>
                <td>${trf.toidacct}</td>
                <td><fmt:formatNumber value="${trf.totalAmount}" pattern="#.00"/></td>
                <td><fmt:formatDate value="${trf.dat}"/></td>
                <td>${trf.status}</td>               
                </tr>
            </c:forEach>
       </c:if>            
        </tbody>
    </table>
       </div>
            <mytag:messageTag rendered="${requestScope.infmessage!= null}" value="${requestScope.infmessage}" id="info"/> 
    </div>
</div>
<jsp:include page="/template/menu.jsp"/>
<jsp:include page="/template/footer.jsp"/>          

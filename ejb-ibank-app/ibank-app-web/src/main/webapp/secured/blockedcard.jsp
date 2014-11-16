
<!DOCTYPE html>
<%@ page language="java" contentType="text/html"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/template/header.jsp"/>  
<div id="content-box-right">
    <div id="content-box-right-in">
<fmt:setLocale value="${requestScope.language}"/>
 <fmt:setBundle basename="messages"/>
 <div class="sptable">
        <table>
            <thead>
                <tr>
                    <td>N</td>
                    <td><fmt:message key="sn"/></td>
                    <td><fmt:message key="namename"/></td>
                    <td><fmt:message key="surnamename"/></td>            
                    <td><fmt:message key="validDate"/></td>
                    <td><fmt:message key="creationDate"/></td>
                    <td><fmt:message key="unblock"/></td>
                </tr>
            </thead>

            <tbody>
            <c:forEach var="crd" items="${RequestScope.cards}" varStatus="i">
                <c:choose>
                    <c:when test="${(i.count) % 2 == 0}">
                        <tr class="even">
                    </c:when>
                    <c:otherwise>
                        <tr class="odd">
                    </c:otherwise>
                </c:choose>
                <td>${i.count}</td>
                <td>${crd.serial}</td>
                <td>${crd.acct.client.name}</td>
                <td>${crd.acct.client.surname}</td>
                <td><fmt:formatDate value="${crd.valid}" pattern="mm/yyyy"/></td>
                <td><fmt:formatDate value="${crd.creation}" pattern="mm/yyyy"/></td>
                <td><a href="blockedcard.jsp?id="><fmt:message key="unblock"/></a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </div>
    </div>
</div>
<jsp:include page="/template/menu.jsp"/>
<jsp:include page="/template/footer.jsp"/>          
       
        

 
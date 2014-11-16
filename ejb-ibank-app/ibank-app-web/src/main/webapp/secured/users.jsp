
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
<jsp:useBean id="cards" class="java.util.ArrayList" scope="request"/>
<div class="sptable">        
<table>
            <thead>
                <tr>
                    <td>N</td>
                    <td><fmt:message key="sn"/></td>
                    <td><fmt:message key="login"/></td>
                    <td><fmt:message key="pn"/></td>
                    <td><fmt:message key="namename"/></td>            
                    <td><fmt:message key="surnamename"/></td>
                    <td><fmt:message key="unblockcardname"/></td>                   
                </tr>
            </thead>

            <tbody>
                <c:if test="${cards != null}">
                <c:forEach var="crd" items="${cards}" varStatus="i">
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
                <td>${crd.acct.client.login}</td>
                <td>${crd.acct.client.pnumber}</td>
                <td>${crd.acct.client.name}</td>
                <td>${crd.acct.client.surname}</td>      
                <c:url value="${initParam.urlController}" var="urlcard">
                    <c:param name="command" value="ccunblock"/>
                    <c:param name="idcard" value="${crd.id}"/>                        
                </c:url>                
                <td><a href="${urlcard}"><fmt:message key="unblockcard" /></a></td>            
                </tr>
            </c:forEach>
       </c:if>            
        </tbody>
    </table>
                </div>
                    <mytag:messageTag rendered="${requestScope.infmessage != null}" value="${requestScope.infmessage}" id="info"/>   
                    <mytag:messageTag rendered="${param.warn}" value="${requestScope.warnMessage}" id="warning"/>   
                    
    </div>
</div>
<jsp:include page="/template/menu.jsp"/>
<jsp:include page="/template/footer.jsp"/>          
       
        

 
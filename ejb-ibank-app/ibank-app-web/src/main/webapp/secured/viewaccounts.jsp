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
<jsp:useBean id="accounts" class="java.util.ArrayList" scope="request"/>
<div class="sptable">
        <table>
            <thead>
                <tr>
                    <td>#</td>
                    <td><fmt:message key="Accountnum"/></td>
                    <td><fmt:message key="Accounttype"/></td>
                    <td><fmt:message key="sncard"/></td>            
                    <td><fmt:message key="validDate"/></td>
                    <td><fmt:message key="totalname"/></td>                  
                    <td><fmt:message key="cardinfoname"/></td>
                </tr>
            </thead>

            <tbody>
                <c:if test="${accounts != null}">
                <c:forEach var="acct" items="${accounts}" varStatus="i">
                <c:choose>
                    <c:when test="${(i.count) % 2 == 0}">
                        <tr class="even">
                    </c:when>
                    <c:otherwise>
                        <tr class="odd">
                    </c:otherwise>
                </c:choose>
                <td>${i.count}</td>
                <td>${acct.id}</td>
                <td><fmt:message key="${acct.type}"/></td>
                <td>${acct.crd.serial}</td>
                <td><fmt:formatDate value="${acct.crd.valid}" pattern="MM/yyyy"/></td>
                <td><fmt:formatNumber value="${acct.totsl}" pattern="#.00"/></td>
                 <c:url value="${initParam.urlController}" var="urlcard">
                        <c:param name="command" value="viewCard" />
                        <c:param name="idcard" value="${acct.crd.id}" />                            
                 </c:url>
                <td> <a href="${urlcard}"><fmt:message key="cardname"/></a>                   
                </td>                         
                </tr>
            </c:forEach>
       </c:if>          
        </tbody>
    </table>
</div>
                
           <mytag:messageTag rendered="${requestScope.errorMessageonlyone!=null}"  value="${requestScope.errorMessageonlyone}" id="warning"/>
           <mytag:messageTag rendered="${requestScope.infMessage!=null}"  value="${requestScope.infMessage}" id="info"/>
           <mytag:messageTag rendered="${requestScope.errorMessagenoacct!=null}"  value="${requestScope.errorMessagenoacct}" id="warning"/>
    </div>
</div>
        <jsp:include page="/template/menu.jsp"/>
<jsp:include page="/template/footer.jsp"/>          
       
        

        

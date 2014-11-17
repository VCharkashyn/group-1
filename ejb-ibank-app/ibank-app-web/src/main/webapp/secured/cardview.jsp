
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

       <jsp:useBean id="card" class="by.bsu.ibank.entities.Card" scope="request"/>
       <div class="sptable">
        <table>
            <thead>
                <tr>                   
                    <td><fmt:message key="sn"/></td>
                    <td><fmt:message key="validDate"/></td>
                    <td><fmt:message key="creationDate"/></td>   
                    <td><fmt:message key="activityname"/></td>                  
                    <td><fmt:message key="blockname"/></td>
                </tr>
            </thead>
            <tbody>
                <c:if test="${card != null}">
               <tr class="even">                   
                <td>${card.serial}</td>
                <td><fmt:formatDate value="${card.valid}" pattern="MM/yyyy"/></td>
                <td><fmt:formatDate value="${card.creation}" pattern="MM/yyyy"/></td>                
                <td><c:choose>
                    <c:when test="${card.activity}">
                        ${"+"}
                    </c:when>
                    <c:otherwise>
                        ${"-"} 
                    </c:otherwise>
                </c:choose>
                    </td>                
                <c:url value="${initParam.urlController}" var="urlcard" scope="page">
                        <c:param name="command" value="ccblock" />
                        <c:param name="idcard" value="${card.id}" />                            
                 </c:url>
                    <td><c:if test="${card.activity}"><a href="${urlcard}"><fmt:message key="blocknamecard"/></a> </c:if>                  
                </td>                         
                </tr>           
       </c:if>            
        </tbody>
    </table>
       </div>
          <mytag:messageTag rendered="${card == null}" id="warning" value="${requestScope.errorMessage}"/>   
          <mytag:messageTag rendered="${requestScope.warnMessage != null}" id="warning" value="${requestScope.warnMessage}"/>   
    </div>
</div>
<jsp:include page="/template/menu.jsp"/>
<jsp:include page="/template/footer.jsp"/>          

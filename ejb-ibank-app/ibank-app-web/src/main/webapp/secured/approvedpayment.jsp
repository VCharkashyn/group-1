
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
 <mytag:messageTag rendered="${requestScope.errormes==null}">
 <h3 class="first"><fmt:message key="successPaymentname"/></h3>
        <div class="division">
            <p><fmt:message key="successPaymentdescription"/></p>
        </div>
 </mytag:messageTag>
  <mytag:messageTag rendered="${requestScope.errormes!=null}">      
        <h3 class="first"><font color="red"><fmt:message key="errorPaymentname"/></font></h3>
        <div class="division">
            <p><fmt:message key="errorPaymentdescription"/></p>
        </div>
  </mytag:messageTag>        
      <mytag:messageTag rendered="${requestScope.errormes !=null}" id="${requestScope.classname}" value="${requestScope.errormes}"/>        
    </div>
</div>
<jsp:include page="/template/menu.jsp"/>
<jsp:include page="/template/footer.jsp"/>          

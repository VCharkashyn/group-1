<%@ page language="java" contentType="text/html"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="mytag" uri="stylemes" %>
<!-- Content left -->
<div id="content-box-left">
    <div id="content-box-left-in">
        <fmt:setLocale value="${requestScope.language}"/>
        <fmt:setBundle basename="messages"/>
        <h3><fmt:message key="menu"/></h3>

        <c:url value="${initParam.urlController}" var="urluser">
            <c:param name="command" value="viewUser" />                                        
        </c:url>
        <c:url value="${initParam.urlController}" var="urltransfer">
            <c:param name="command" value="viewTransfer" />                                        
        </c:url>
        <c:url value="${initParam.urlController}" var="urlpayment">
            <c:param name="command" value="viewPayment" />                                        
        </c:url>       
        <c:url value="${initParam.urlController}" var="urlownpayment">
            <c:param name="command" value="viewOwnPayment" />                                        
        </c:url>
        <c:url value="${initParam.urlController}" var="urlaccounts">
            <c:param name="command" value="viewAccount" />                                        
        </c:url>
        <ul>
            <li><a href="${urlaccounts}"><fmt:message key="viewaccountsname"/></a></li>
            <li><a href="${urltransfer}"><fmt:message key="viewtransfersname"/></a></li>
            <li><a href="${urlpayment}"><fmt:message key="dopaymentname"/></a></li>          
            <li class="last"><a href="${urlownpayment}"><fmt:message key="dorecharge"/></a></li>
        </ul>
        <mytag:messageTag rendered="${sessionScope.role!=null}">
            <h3><fmt:message key="adminmenu"/></h3>
            <ul>
                <li><a href="${urluser}"><fmt:message key="usersviewname"/></a></li>
                <li><a href="#"><fmt:message key="createaccountname"/></a></li>
                <li class="last"><a href="#"><fmt:message key="registerclientname"/></a></li>
            </ul>
        </mytag:messageTag>
        <h3><fmt:message key="Contact"/></h3>
        <p class="first">
            <span>E-mail:</span><br />
            service@ibank.com</p>
        <p><span>Skype:</span><br /> 
            ibankservice</p>
        <p><span>Fax:</span><br />
            0 123 456 789</p>        
    </div>
</div>
<!-- Content left end -->
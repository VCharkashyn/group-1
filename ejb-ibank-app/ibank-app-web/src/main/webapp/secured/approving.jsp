
<!DOCTYPE html>
<%@ page language="java" contentType="text/html"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/template/header.jsp"/>  
<div id="content-box-right">
    <div id="content-box-right-in">
        <fmt:setLocale value="${requestScope.language}"/>
        <fmt:setBundle basename="messages"/>       
        <c:if test="${requestScope.typer}">  
            <div class="sptable"> 
            <table>
                <thead>
                    <tr>
                        <td><fmt:message key="Paymentinfo"/></td> 
                        <td></td> 
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><fmt:message key="Accountnum"/></td>  
                        <td>${requestScope.crdview.id}</td>  
                    </tr>
                    <tr>
                        <td><fmt:message key="sn"/></td>  
                        <td>${requestScope.crdview.serial}</td>  
                    </tr>
                    <tr>
                        <td><fmt:message key="datename"/></td>  
                        <td> ${requestScope.crdview.mounth} ${"/"}${requestScope.crdview.year}</td>  
                    </tr>
                    <tr>
                        <td><fmt:message key="CVcode"/></td>  
                        <td>${requestScope.crdview.cv} </td>  
                    </tr>
                    <tr>
                        <td><fmt:message key="namename"/></td>  
                        <td>${requestScope.crdview.name}</td>  
                    </tr>
                    <tr>
                        <td><fmt:message key="surnamename"/></td>  
                        <td>${requestScope.crdview.surname}</td>  
                    </tr>
                    <tr>
                        <td><fmt:message key="sumname"/></td>  
                        <td>${requestScope.crdview.sum}</td>  
                    </tr>
                </tbody>
            </table>  
            </div>
                        <fmt:message key="submitname" var="sbvar"/>

            <form name="viewFormTransfer" 
                  action="controller" method="POST">
                <input type="hidden" name="command" value="approvedPayment" />		
                <input type="hidden" name="fromacctid" value="${requestScope.crdview.id}" />
                <input type="hidden" name="serial" value="${requestScope.crdview.serial}" />
                <input type="hidden" name="mounth" value="${requestScope.crdview.mounth}" />
                <input type="hidden" name="year" value="${requestScope.crdview.year}" />
                <input type="hidden" name="cv" value="${requestScope.crdview.cv}" />
                <input type="hidden" name="name" value="${requestScope.crdview.name}" />
                <input type="hidden" name="surname" value="${requestScope.crdview.surname}" />
                <input type="hidden" name="sum" value="${requestScope.crdview.sum}" />
                <input type="submit" value="${sbvar}">         
            </form>
        </c:if>
        <c:if test="${!requestScope.typer}">
           <div class="sptable">  
            <table>
                <thead>
                    <tr>
                        <td><fmt:message key="Paymentinfo"/></td> 
                        <td></td> 
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><fmt:message key="toAccountn"/></td>  
                        <td>${requestScope.toacctid}</td>  
                    </tr>
                    <tr>
                        <td><fmt:message key="fromAccountn"/></td>  
                        <td>${requestScope.fromacctid}</td>  
                    </tr>
                    <tr>
                        <td><fmt:message key="sumname"/></td>  
                        <td> ${requestScope.sum}</td>  
                    </tr>                
                </tbody>
            </table>   
           </div>
                        <fmt:message key="submitname" var="sbmtvar"/>

            <form name="viewFormTransfer" 
                  action="controller" method="POST">
                <input type="hidden" name="command" value="approvedPayment" />	  
                <input type="hidden" name="own" value="true" />	
                <input type="hidden" name="toacctid" value="${requestScope.toacctid}" />	
                <input type="hidden" name="fromacctid" value="${requestScope.fromacctid}" />
                <input type="hidden" name="sum" value="${requestScope.sum}" />	
                <input type="submit" value="${sbmtvar}">         
            </form>
        </c:if>
    </div>
</div>
<jsp:include page="/template/menu.jsp"/>
<jsp:include page="/template/footer.jsp"/>          


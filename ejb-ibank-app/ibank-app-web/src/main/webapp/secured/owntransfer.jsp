
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
        <script>
            function validateOwnPayment(form) {
                if (form.sum.value=="") {
                    alert("Please fill in your sum");
                    form.sum.focus();
                }        
                else {
                    form.submit();
                }
            }
        </script>

        <form name="ownTranferForm"  method="POST"
              action="controller">
            <input type="hidden" name="command" value="approvingPayment" />
            <input type="hidden" name="typer" value="false" />            
            <table>
                <tr>
                    <td>
                        <strong><fmt:message key="Selectsenderaccount"/>: &nbsp</strong>
                    </td>
                    <td>
                        <select name="receiveraccount">                
                            <c:forEach var="sender" items="${requestScope.accounts}">                    
                                <option value="${sender.id}">${sender.id}</option>                   
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr><td>
                        <strong><fmt:message key="Selectreceiveraccount"/></strong>: &nbsp</td><td>
                        <select name="senderaccount">              
                            <c:forEach var="receiver" items="${requestScope.accounts}">                   
                                <option value="${receiver.id}">${receiver.id}</option>                    
                            </c:forEach>
                        </select> </td>

                </tr>
                <tr>
                    <td>
                        <strong><fmt:message key="sumname"/>:</strong> </td>
                    <td>
                        <input type="text" name="sum" value="${requestScope.sum}" > </td></tr>
            </table>          
            <fmt:message key="submitname" var="sbmt"/>
            <input type="submit" value="${sbmt}" onclick="validateOwnPayment(this.form)">
        </form>
            <mytag:messageTag rendered="${requestScope.validMessage1!=null}"  value="${requestScope.validMessage1}" id="validation"/>         
        <mytag:messageTag rendered="${requestScope.validMessage2!=null}"  value="${requestScope.validMessage2}" id="validation"/>  
    </div>
</div>
<jsp:include page="/template/menu.jsp"/>
<jsp:include page="/template/footer.jsp"/>          

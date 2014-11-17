
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
    function validateCardPayment(form) {
        if (form.serial.value=="") {
            alert("Please fill in your serial number");
            form.serial.focus();
        }
        else if (form.cv.value=="") {
            alert("Please fill in your cv number");
            form.cv.focus();
        }     

 else if (form.id.value=="") {
            alert("Please fill in your id value");
            form.id.focus();
        }  
 else if (form.name.value=="") {
            alert("Please fill in your Card name");
            form.name.focus();
        }  
 else if (form.surname.value=="") {
            alert("Please fill in your Card surname");
            form.surname.focus();
        } 
else if (form.sum.value=="") {
            alert("Please fill in your sum");
            form.sum.focus();
        }  
   
        else {
            form.submit();
        }
    }
</script>
<form name="PaymentForm" method="POST" action="controller">
    <input type="hidden" name="command" value="approvingPayment" />  
    <input type="hidden" name="typer" value="true" />
    <fmt:message key="Cardtype"/></br>        
        <select name="typecard">
        <option value="0">VISA</option>
        <option value="1">MASTERCARD</option>
        <option value="2">AMERICAN EXPRESS</option>
        <option value="3">EN ROUTE</option>
        <option value="4">DINERS CLUB</option>
        </select><br/>
      <fmt:message key="sn"/><br/>
        <input type="text" name="serial" value="${requestScope.crdview.serial}"><br/>
	<fmt:message key="validDate"/><br/>
        <select name="mounth">
        <option value="1">01</option>
        <option value="2">02</option>
        <option value="3">03</option>
        <option value="4">04</option>
        <option value="5">05</option>
        <option value="6">06</option>
        <option value="7">07</option>
        <option value="8">08</option>
        <option value="9">09</option>
        <option value="10">10</option>
        <option value="11">11</option>
        <option value="12">12</option>
        </select>    
        ${"/"}
        <mytag:yearTag name="year"/>             
	 <br/>
        <fmt:message key="CVcode"/></br>
        <input type="text" name="cv" value="${requestScope.crdview.cv}"> <br/>
        <fmt:message key="Accountreceiver"/></br>
         <input type="text" name="id" value="${requestScope.crdview.id}"> <br/>
         <fmt:message key="namename"/></br>
         <input type="text" name="name" value="${requestScope.crdview.name}"> <br/>
          <fmt:message key="surnamename"/></br>
         <input type="text" name="surname" value="${requestScope.crdview.surname}"> <br/>
        <fmt:message key="sumname"/></br>
         <input type="text" name="sum" value="${requestScope.crdview.sum}"> <br/>
         <input type="submit" value="Submit" onclick="validateCardPayment(this.form)">
</form>   
         <mytag:messageTag rendered="${requestScope.validMessage1!=null}"  value="${requestScope.validMessage1}"  id="validation"/>  
         <mytag:messageTag rendered="${requestScope.validMessage2!=null}"  value="${requestScope.validMessage2}" id="validation"/>
         <mytag:messageTag rendered="${requestScope.validMessage3!=null}"  value="${requestScope.validMessage3}" id="validation"/>  
         <mytag:messageTag rendered="${requestScope.validMessage4!=null}"  value="${requestScope.validMessage4}" id="validation"/>  
         <mytag:messageTag rendered="${requestScope.validMessage5!=null}"  value="${requestScope.validMessage5}" id="validation"/>
         <mytag:messageTag rendered="${requestScope.validMessage6!=null}"  value="${requestScope.validMessage6}" id="validation"/>          
         
         <mytag:messageTag rendered="${requestScope.validMessage7!=null}"  value="${requestScope.validMessage7}" id="error"/>  
         <mytag:messageTag rendered="${requestScope.validMessage8!=null}"  value="${requestScope.validMessage8}" id="error"/>  
         <mytag:messageTag rendered="${requestScope.validMessage9!=null}"  value="${requestScope.validMessage9}" id="error"/>  
    </div>
</div>
<jsp:include page="/template/menu.jsp"/>
<jsp:include page="/template/footer.jsp"/>    

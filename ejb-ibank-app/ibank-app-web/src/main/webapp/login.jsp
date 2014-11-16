
<!DOCTYPE html>
<%@ page language="java" contentType="text/html"  pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="mytag" uri="stylemes" %>
<jsp:include page="/template/header.jsp"/>
   <script>
            function validateLogin(form) {
        if (form.login.value=="") {
            alert("Please fill in your login");
            form.login.focus();
        }
        else if (form.password.value=="") {
            alert("Please fill in your password");
           form.password.focus();
        }        
        else {
            form.submit();
        }
    }
 </script> 
 <hr/>
 
<div id="content-box-right">
    <div id="content-box-right-in">
 <fmt:setLocale value="${requestScope.language}"/>
 <fmt:setBundle basename="messages"/>
<form name="loginForm" method="POST" 
              action="controller">
            <input type="hidden" name="command" value="login" />
            <table>
                <tr>
                    <td><fmt:message key="login"/></td>
                    <td><input type="text" name="login" value=""></td>
                </tr>
                <tr>
                    <td><fmt:message key="password"/></td>
                    <td><input type="password" name="password" value=""> </td>
                </tr>
                <tr>
                    <fmt:message key="enter" var="entr"/>
                    <td><input type="submit" value="${entr}" onClick="validateLogin(this.form)"></td>
                    <td></td>                
                </tr>
            </table>                    
                    <fmt:message key="printLogout" var="logt"/>
                    <fmt:message key="validlogout" var="lognvalid"/>
                    <fmt:message key="sessionexpr" var="expr"/>
                    <mytag:messageTag rendered="${param.valid}"  value="${lognvalid}" id="validation"/>  
                    <mytag:messageTag id="${requestScope.classname}" rendered="${param.error}"  value="${requestScope.errorMessage}"/>
                    <mytag:messageTag rendered="${param.logout}"  value="${logt}" id="info"/>  
                    <mytag:messageTag rendered="${sessionScope.mes!=null}" value="${sessionScope.mes}" id="warning"/>
                    <mytag:messageTag rendered="${param.expired!=null}" value="${expr}" id="warning"/>
     </form>
    </div>
</div>
 
<jsp:include page="/template/footer.jsp"/>   
   

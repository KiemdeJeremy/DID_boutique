<%-- 
    Document   : detailAchat
    Created on : 23 janv. 2025, 20:12:01
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <%
            String headerJSP = (String) session.getAttribute("headerJSP");
            if (headerJSP == null) {
                headerJSP = "/vues/mesInclusions/adminHeader.jsp"; // En cas de problème avec l'attribut de session
            }
        %>

        <jsp:include page="<%=headerJSP%>" />
    <body>
       
    </body>
</html>

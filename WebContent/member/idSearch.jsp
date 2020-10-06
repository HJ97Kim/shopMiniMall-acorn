<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String mesg = (String)request.getAttribute("mesg");
	if(mesg != null) {
%>
<script>
	alert('<%=mesg%>');
</script>

<%
	}
%>
<form action="MemberIdSearchServlet" method="get">
이름
<input type="text" value="" name="username"><br>
휴대폰
<select name="phone1">
  		<option value="011">011</option>
  		<option value="010">010</option>
	</select>
-
<input type="text" value="" name="phone2" >
-
<input type="text" value="" name="phone3" ><br>
이메일
<input type="text" value="" name="email1" id="email1">
@
<input type="text" value="" name="email2" id="email2" placeholder="직접입력">
<input type="submit" value="메일 보내기"></input>
</form>

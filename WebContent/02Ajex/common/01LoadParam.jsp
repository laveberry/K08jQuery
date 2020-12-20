<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//단독실행시 500에러. ?param=good 하면 나옴
String params = request.getParameter("params");
if(params.equals("good")){
%>
	<h3 style="color:red;">Good~오늘은 좋은날^*^</h3>
<%
}
else if(params.equals("bad")){
%>	
	<h3 style="color: blue;">Bad! 운수나쁜날ㅠ_ㅠ</h3>
<%
}
%>
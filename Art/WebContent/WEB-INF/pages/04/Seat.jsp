<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.sofa {
	width: 45px;
}
</style>
</head>
<body>
<h1>座位表</h1>
	<table>
		<tr>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
		</tr>
		<tr>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
		</tr>
		<tr>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
		</tr>
		<tr>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/sofaOff.png" alt="" title="" class="sofa"></td>
		</tr>
	</table>
<script
  src="https://code.jquery.com/jquery-3.5.1.js"
  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
  crossorigin="anonymous"></script>
<script>
         $(".sofa").mouseover(function(){
             $(this).attr("src","images/sofa.png");
         }).mouseout(function(){
             $(this).attr("src","images/sofaOff.png")
         })           
    </script> 

</body>
</html>
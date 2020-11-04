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
	width: 60px;
	border-width: 3px;
	border-style: dashed;
	border-color:#FFFFFF;

}
.sofa2 {
	width: 60px;
	

}
</style>
</head>
<body>
<h1>座位表</h1>
1. 請勾選座位
請在灰色空位上點選您要的位置


	<table>
		<tr>
			<td><img src="images/04/sofa.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofa.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofa.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofa.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofa.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofa.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofa.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofa.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofa.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofa.png" alt="" title="" class="sofa"></td>
			
		</tr>
		<tr>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
		</tr>
		<tr>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
		</tr>
		<tr>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa"></td>
		</tr>
	</table>
	<br><br>
	<table>
	<tr>
			<td><img src="images/04/sofaOff.png" alt="" title="" class="sofa2">空位</td>
			<td><img src="images/04/sofa.png" alt="" title="" class="sofa2">已售出</td>
			<td><img src="images/04/sofaTick.png" alt="" title="" class="sofa2">已加入購物車</td>
	</tr>
	</table>
<script
  src="https://code.jquery.com/jquery-3.5.1.js"
  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
  crossorigin="anonymous"></script>
<script>
         $(".sofa").mouseover(function(){
             $(this).css("border-color","#FFAC55"); 
         }).mouseout(function(){
             $(this).css("border-color","#FFFFFF")
         }).click(function(){
           $(this).attr("src","images/04/sofaTick.png");  
         }).dblclick(function(){
             $(this).attr("src","images/04/sofaOff.png")       
//          $(".sofa").mouseover(function(){
//              $(this).attr("src","images/04/sofa.png");
//          }).mouseout(function(){
//              $(this).attr("src","images/04/sofaOff.png")
          })           
    </script> 

</body>
</html>
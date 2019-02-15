<html>
<head>
	<title>student</title>
</head>
<body>
	学生信息:<br>
	学号:${student.id}  <br>
	姓名:${student.name} <br>
	年龄:${student.age}<br>
	地址:  ${student.address}
	
	<p>下面是取列表的形式</p><br>
	<table border="1">
		<tr>
			<th>序号</th>
			<th>学号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>地址</th>
		</tr>
		<#list slist as stu>
		<#if stu_index %2==0>
		<tr bgcolor="red">
		<#else>
		<tr bgcolor="green">
		</#if>
			<td>${stu_index}</td>
			<td>${stu.id}</td>
			<td>${stu.name}</td>
			<td>${stu.age}</td>
			<td>${stu.address}</td>
		</tr>
		</#list>
	</table>
	<br>
	当前日期:${date?date}<br>
	当前时间:${date?time}<br>
	当前日期时间:${date?datetime}<br>
	指定日期格斯${date?string("yyyy/MM/dd HH:mm:ss")}<br>
	空值的处理:${val!} <br>
	若为空添加默认值 ：${val!"syw说null"}<br>
	<br>
	判断是否为空
	<#if val??>
	val有值
	<#else>
	val的值为空
	</#if>
	<br>
	<h3>下面是引用其他模板的测试</h3>
	<#include "hello.ftl">
	
	
	
	
</body>
</html>
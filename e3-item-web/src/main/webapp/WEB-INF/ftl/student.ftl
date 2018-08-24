
<html>
<head>
    <title>student</title>
</head>
<body>
       学生信息: <br>
        学号:${student.id} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        姓名:${student.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        年龄:${student.age} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         家庭住址:${student.address} <br>   
         
     学生列表:
     <table border="1">
      <tr>
       <th>序号</th>
       <th>学号</th>
       <th>姓名</th>
       <th>年龄</th>
       <th>家庭住址</th>
      </tr>
      <#list stuList as student>
      <#if student_index% 2 ==0>
      <tr bgcolor="red">
      <#else>
      <tr bgcolor="green">
       </#if>
        <td>${student_index }</td>
        <td>${student.id}</td>
        <td>${student.name}</td>
        <td>${student.age}</td>
        <td>${student.address}</td>
      </tr>
      </#list>
     </table>  
     <br>
     <!--可以使用?date,?time,?datetime,?string(pattern) -->
     当前日期:${date?string("yyyy/MM/dd HH:mm:ss")}  

     <#include "hello.ftl">
       
       
       
</body>
</html>

<%@ page import="com.koscom.util.StringUtil" %>
<%@ page import="java.io.File" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/include/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Crizen Loan Counsel Agency</title>
    <script type="text/javascript">
    </script>
</head>
<body>
<form action="/fincorp/LCAfilesSelectUpload.crz" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td><input type="submit" name="실행" value="업로드"></td>
            <td><input type="file"   name="file1"></td>
        </tr>
        <tr>
            <td>서버파일명</td>
            <td><input type="text" name="file_name" style="width:500px "></td>
        </tr>
    </table>
</form>
</body>
</html>
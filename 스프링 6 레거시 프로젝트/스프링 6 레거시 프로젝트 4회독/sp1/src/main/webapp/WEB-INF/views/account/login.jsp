<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <style>
        body {
            font-family: sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f4f4f4;
        }

        .login-container {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }

        h2 {
            margin-bottom: 20px;
            color: #333;
        }

        .form-group {
            margin-bottom: 15px;
            text-align: left;
        }

        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-size: 0.9em;
        }

        input[type="text"],
        input[type="password"] {
            width: calc(100% - 12px);
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 1em;
        }

        button[type="submit"] {
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1em;
            width: 100%;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
        }
        
         .message {
            margin-bottom: 15px;
            padding: 10px;
            border-radius: 4px;
            font-size: 0.9em;
        }

        .error {
            background-color: #fdecea;
            color: #d9534f;
            border: 1px solid #e74c3c;
        }

        .logout-success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>로그인</h2>
        
        
        <c:if test="${param.error != null}">
            <div class="message error">
                로그인 실패: 사용자 이름 또는 비밀번호를 확인하세요.
            </div>
        </c:if>

        <c:if test="${param.logout != null}">
            <div class="message logout-success">
                성공적으로 로그아웃되었습니다.
            </div>
        </c:if>
        
        <form method="post">
            <div class="form-group">
                <label for="username">사용자 이름:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">비밀번호:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
	            <label>
	             <input type="checkbox" name="remember-me">
	             로그인 상태 유지
	            </label>
 				    </div>
            
            <button type="submit">로그인</button>
        </form>
    </div>
</body>
</html>

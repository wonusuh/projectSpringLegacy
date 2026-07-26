<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>로그아웃</title>
    <style>
      body {
        font-family: sans-serif;
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        background-color: #f4f4f4;
      }

      .logout-container {
        background-color: #fff;
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 300px;
        text-align: center;
      }

      h2 {
        margin-bottom: 20px;
        color: #d9534f; /* 경고 색상 계열 */
      }

      p {
        color: #555;
        margin-bottom: 20px;
      }

      .button-group {
        display: flex;
        gap: 10px;
      }

      .logout-button,
      .cancel-button {
        flex: 1;
        padding: 10px 15px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 1em;
      }

      .logout-button {
        background-color: #d9534f; /* 경고 색상 */
        color: white;
      }

      .logout-button:hover {
        background-color: #c9302c;
      }

      .cancel-button {
        background-color: #f0f0f0;
        color: #333;
        border: 1px solid #ccc;
      }

      .cancel-button:hover {
        background-color: #e0e0e0;
      }
    </style>
  </head>
  <body>
    <div class="logout-container">
      <h2>로그아웃 확인</h2>
      <p>정말로 로그아웃 하시겠습니까?</p>

      <form action="/logout" method="post">
        <button class="logout-button">로그아웃</button>
      </form>
    </div>
  </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>

<div class="row justify-content-center">
  <div class="col-lg-12">
    <div class="card shadow mb-4">
      <div class="card-header py-3">
        <h6 class="m-0 fw-bold text-primary">Board Modify</h6>
      </div>
      
      <div class="card-body">
        <form id="actionForm" action="/board/modify" method="post">
        
          <div class="mb-3 input-group input-group-lg">
            <span class="input-group-text">Bno</span>
            <input type="text" name="bno" class="form-control" value="<c:out value='${board.bno}'/>" readonly>
          </div>

          <div class="mb-3 input-group input-group-lg">
            <span class="input-group-text">Title</span>
            <input type="text" name="title" class="form-control" value="<c:out value='${board.title}'/>">
          </div>

          <div class="mb-3 input-group input-group-lg">
            <span class="input-group-text">Content</span>
            <textarea class="form-control" name="content" rows="3"><c:out value="${board.content}"/></textarea>
          </div>

          <div class="mb-3 input-group input-group-lg">
            <span class="input-group-text">Writer</span>
            <input type="text" class="form-control" value="<c:out value='${board.writer}'/>" readonly>
          </div>

          <div class="mb-3 input-group input-group-lg">
            <span class="input-group-text">RegDate</span>
            <input type="text" class="form-control" value="<c:out value='${board.createdDate}'/>" readonly>
          </div>

        </form>

        <div class="float-end">
          <button type="button" class="btn btn-info btnList">LIST</button>
          <button type="button" class="btn btn-warning btnModify">MODIFY</button>
          <button type="button" class="btn btn-danger btnRemove">REMOVE</button>
        </div>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
  const formObj = document.querySelector("#actionForm");

  document.querySelector(".btnModify").addEventListener("click", () => {
    formObj.action = '/board/modify';
    formObj.method = 'post';
    formObj.submit();
  });

  document.querySelector(".btnList").addEventListener("click", () => {
    formObj.action = '/board/list';
    formObj.method = 'get';
    formObj.submit();
  });

  document.querySelector(".btnRemove").addEventListener("click", () => {
    formObj.action = '/board/remove';
    formObj.method = 'post';
    formObj.submit();
  });
</script>

<%@ include file="/WEB-INF/views/includes/footer.jsp" %>


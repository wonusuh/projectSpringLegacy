<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@include file="/WEB-INF/views/includes/header.jsp" %>

<div class="row justify-content-center">
  <div class="col-lg-12">
    <div class="card shadow mb-4">
      <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Product Register</h6>
      </div>
      <div class="card-body">
        <!-- 입력 폼 -->
        <form
          class="p-3"
          method="post"
          action="/product/register"
          enctype="multipart/form-data"
        >
          <div class="mb-3">
            <label class="form-label">Product Name</label>
            <input type="text" name="pname" class="form-control" />
          </div>

          <div class="mb-3">
            <label class="form-label">Product Desc</label>
            <textarea class="form-control" name="pdesc" rows="3"></textarea>
          </div>

          <div class="mb-3">
            <label class="form-label">Price</label>
            <input type="number" name="price" class="form-control" />
          </div>

          <div class="mb-3">
            <label class="form-label">Image Files</label>
            <input
              class="form-control"
              type="file"
              name="files"
              multiple="multiple"
            />
          </div>

          <div class="mb-3">
            <label class="form-label">Writer</label>
            <input type="text" name="writer" class="form-control" />
          </div>

          <div class="d-flex justify-content-end">
            <button type="submit" class="btn btn-primary btn-lg">Submit</button>
          </div>
        </form>
        <!-- // 입력 폼 -->
      </div>
    </div>
  </div>
</div>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>

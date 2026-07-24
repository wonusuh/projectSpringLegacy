<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%> <%@ include file="/WEB-INF/views/includes/header.jsp" %>

<div class="row justify-content-center">
  <div class="col-lg-12">
    <div class="card shadow mb-4">
      <div class="card-header py-3">
        <h6 class="m-0 fw-bold text-primary">Product Modify</h6>
      </div>
      <div class="card-body">
        <form
          id="form1"
          action="/product/modify/${product.pno}"
          method="post"
          enctype="multipart/form-data">
          <div class="mb-3 input-group input-group-lg">
            <span class="input-group-text">No</span>
            <input
              type="text"
              name="pno"
              class="form-control"
              value="<c:out value='${product.pno}'/>"
              readonly />
          </div>

          <div class="mb-3 input-group input-group-lg">
            <span class="input-group-text">Product Name</span>
            <input
              type="text"
              name="pname"
              class="form-control"
              value="<c:out value='${product.pname}'/>" />
          </div>

          <div class="mb-3 input-group input-group-lg">
            <span class="input-group-text">Desc</span>
            <textarea
              class="form-control"
              name="pdesc"
              rows="3">
              <c:out value="${product.pdesc}"></c:out>
            </textarea>
          </div>

          <div class="mb-3 input-group input-group-lg">
            <span class="input-group-text">Writer</span>
            <input
              type="text"
              name="writer"
              class="form-control"
              value="<c:out value='${product.writer}'/>"
              readonly />
          </div>

          <div class="mb-3 input-group input-group-lg">
            <span class="input-group-text">Price</span>
            <input
              type="number"
              name="price"
              class="form-control"
              value="<c:out value='${product.price}'/>" />
          </div>

          <div class="mb-3">
            <input
              type="file"
              name="files"
              class="form-control"
              multiple="multiple" />
          </div>

          <div class="float-end">
            <button
              type="button"
              class="btn btn-info btnList">
              LIST
            </button>
            <button
              type="button"
              class="btn btn-warning btnModify">
              MODIFY
            </button>
            <button
              type="button"
              class="btn btn-danger btnRemove">
              REMOVE
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

<div class="mb-3 productImages">
  <label class="form-label fw-bold">Product Images</label>
  <div class="row">
    <c:forEach
      var="image"
      items="${product.imageList}">
      <div class="col-md-3 mb-3">
        <div class="card">
          <a
            href="/images/${image.uuid}_${image.fileName}"
            target="_blank">
            <img
              src="/images/${image.uuid}_${image.fileName}"
              class="card-img-top img-fluid"
              alt="Product Image" />
          </a>
          <button
            type="button"
            class="btn btn-danger btn-sm position-absolute top-0 end-0 m-2 delete-image-btn"
            data-file="${image.uuid}_${image.fileName}">
            Delete
          </button>
        </div>
      </div>
    </c:forEach>
  </div>
</div>

<script>
  const form1 = document.querySelector('#form1')

  // 상품 삭제
  document.querySelector('.btnRemove').addEventListener(
    'click',
    (e) => {
      e.preventDefault()
      e.stopPropagation()

      form1.action = '/product/remove'
      form1.submit()
    },
    false
  )

  // 특정 이미지 삭제
  document.querySelector('.productImages').addEventListener(
    'click',
    (e) => {
      e.preventDefault()
      e.stopPropagation()

      const target = e.target
      const fileName = target.getAttribute('data-file')

      // 방어 로직
      if (!fileName) {
        return
      }

      // 해당하는 <div> 를 찾아가야함
      const divObj = target.closest('.col-md-3')
      divObj.remove()
    },
    false
  )

  // 상품 수정
  document.querySelector('.btnModify').addEventListener(
    'click',
    (e) => {
      e.preventDefault()
      e.stopPropagation()

      form1.method = 'post'
      form1.action = '/product/modify'
      const imageArr = document.querySelectorAll('.productImages button')

      // 방어 로직
      if (!imageArr) {
        return
      }

      let str = ''
      for (const image of imageArr) {
        const imageFile = image.getAttribute('data-file')
        str += `<input type="hidden" name="oldImages" value="\${imageFile}" />`
      } // end of for

      form1.querySelector('div:last-child').insertAdjacentHTML('beforeend', str)

      // 서비스 호출
      form1.submit()
    },
    false
  )
</script>

<%@ include file="/WEB-INF/views/includes/footer.jsp" %>

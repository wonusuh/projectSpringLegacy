<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="row justify-content-center">
	<div class="col-lg-12">
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<h6 class="m-0 fw-bold text-primary">Board Read</h6>
			</div>
			<div class="card-body">
				<div class="mb-3 input-group input-group-lg">
					<span class="input-group-text">Bno</span> <input type="text"
						class="form-control" value="<c:out value='${board.bno}'/>"
						readonly />
				</div>

				<div class="mb-3 input-group input-group-lg">
					<span class="input-group-text">Title</span> <input type="text"
						name="title" class="form-control"
						value="<c:out value='${board.title}'/>" readonly />
				</div>

				<div class="mb-3 input-group input-group-lg">
					<span class="input-group-text">Content</span>
					<textarea class="form-control" name="content" rows="3" readonly>
<c:out value="${board.content}" /></textarea>
				</div>

				<div class="mb-3 input-group input-group-lg">
					<span class="input-group-text">Writer</span> <input type="text"
						name="writer" class="form-control"
						value="<c:out value='${board.writer}'/>" readonly />
				</div>

				<div class="mb-3 input-group input-group-lg">
					<span class="input-group-text">RegDate</span> <input type="text"
						name="regDate" class="form-control"
						value="<c:out value='${board.createdDate}'/>" readonly />
				</div>

				<div class="float-end">
					<a href="/board/list">
						<button type="button" class="btn btn-info btnList">LIST</button>
					</a>

					<sec:authentication property="principal" var="secInfo" />
					<sec:authentication property="authorities" var="roles" />

					<c:if
						test="${!board.delFlag && (secInfo == board.writer || fn:contains(roles, 'ROLE_ADMIN'))}">
						<a href="/board/modify/${board.bno}" class="btn">
							<button type="button" class="btn btn-warning btnModify">
								MODIFY</button>
						</a>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="col-lg-12">
	<div class="card shadow mb-4">
		<div class="m-4">
			<!--댓글 작성 폼 -->
			<form id="replyForm" class="mt-4">
				<!-- 게시글 번호 hidden 처리 -->
				<input type="hidden" name="bno" value="${board.bno}" />

				<div class="mb-3 input-group input-group-lg">
					<span class="input-group-text">Replyer</span> <input type="text"
						name="replyer" class="form-control" required />
				</div>

				<div class="mb-3 input-group">
					<span class="input-group-text">Reply Text</span>
					<textarea name="replyText" class="form-control" rows="3" required></textarea>
				</div>

				<div class="text-end">
					<button type="submit" class="btn btn-primary addReplyBtn">
						Submit Reply</button>
				</div>
			</form>
			<!-- 댓글 작성 폼 끝 -->
		</div>
	</div>
</div>

<div class="col-lg-12">
	<div class="card shadow mb-4">
		<div class="m-4">
			<!-- 댓글 목록 -->
			<ul class="list-group replyList">
				<li class="list-group-item">
					<div class="d-flex justify-content-between">
						<div>
							<strong>번호</strong> - 댓글 내용
						</div>
						<div class="text-muted small">작성일</div>
					</div>
					<div class="mt-1 text-secondary small">작성자</div>
				</li>
			</ul>
			<!-- // 댓글 목록 -->

			<!-- 페이징 -->
			<div aria-label="댓글 페이지 네비게이션" class="mt-4">
				<ul class="pagination justify-content-center">
					<li class="page-item disabled"><a class="page-link" href="#"
						tabindex="-1">이전</a></li>
					<li class="page-item active"><a class="page-link" href="#">1</a>
					</li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#">다음</a></li>
				</ul>
			</div>
			<!-- // 페이징 끝 -->
		</div>
	</div>
</div>

<div class="modal fade" id="replyModal" tabindex="-1"
	aria-labelledby="replyModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="replyModalLabel">댓글 수정 / 삭제</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>

			<div class="modal-body">
				<form id="replyModForm">
					<input type="hidden" name="rno" value="33" />
					<div class="mb-3">
						<label for="replyText" class="form-label">댓글 내용</label> <input
							type="text" name="replyText" id="replyText" class="form-control"
							value="Reply Text" />
					</div>
				</form>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-primary btnReplyMod">수정</button>
				<button type="button" class="btn btn-danger btnReplyDel">삭제</button>
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<script>
  const replyForm = document.querySelector("#replyForm");

  // 댓글 등록 이벤트
  document.querySelector(".addReplyBtn").addEventListener(
    "click",
    (e) => {
      e.preventDefault();
      e.stopPropagation();

      const formData = new FormData(replyForm);
      axios.post("/replies", formData).then((res) => {
        console.log("----------------server response----------------");
        console.log(res);
        replyForm.reset();
        getReplies(1, true);
      });
    },
    false
  );

  // 댓글 목록 불러오기
  let currentPage = 1;
  let currentSize = 10;
  const bno = "${board.bno}";

  // axios 호출
  const getReplies = async (pageNum, goLast) => {
    const res = await axios.get(`/replies/\${bno}/list`, {
      params: {
        page: pageNum || currentPage,
        size: currentSize,
      },
    });

    const data = res.data;
    console.log(data);
    const { totalCount, page, size } = data;

    // 다시 호출할 필요가 있는지를 계산
    if (goLast && totalCount > page * size) {
      // 마지막 페이지를 계산
      const lastPage = Math.ceil(totalCount / size);
      getReplies(lastPage);
    } else {
      currentPage = page;
      currentSize = size;

      printReplies(data); // 출력
    }
  };

  // 댓글 리스트 보여주기
  const replyList = document.querySelector(".replyList");
  const printReplies = (data) => {
    const { replyDTOList, page, size, prev, next, start, end, pageNums } = data;
    let liStr = "";

    for (replyDTO of replyDTOList) {
      liStr += `<li class="list-group-item" data-rno="\${replyDTO.rno}">
			<div class="d-flex justify-content-between">
				<div>
					<strong>\${replyDTO.rno}</strong> - \${replyDTO.replyText}
					</div>
					<div class="text-muted small">
						\${replyDTO.replyDate}
						</div>
						</div>
						<div class="mt-1 text-secondary small">
							\${replyDTO.replyer}
            </div>
          </li>`;
    } //end for

    replyList.innerHTML = liStr;

    // 댓글 페이징 처리
    let pagingStr = "";

    if (prev) {
      pagingStr += `<li class="page-item">
			<a class="page-link" href="\${start -1}" tabindex="-1">이전</a>
			</li>`;
    }

    for (let i of pageNums) {
      pagingStr += `<li class="page-item \${i === page ? 'active': ''}">
				<a class="page-link" href="\${i}">\${i}</a>
  	          </li>`;
    }

    if (next) {
      pagingStr += `<li class="page-item">
					<a class="page-link" href="\${end + 1}">다음</a>
  	          </li>`;
    }

    document.querySelector(".pagination").innerHTML = pagingStr;
  };

  document.querySelector(".pagination").addEventListener(
    "click",
    (e) => {
      e.stopPropagation();
      e.preventDefault();
      const target = e.target;
      const href = target.getAttribute("href");

      //
      if (!href) {
        return;
      }

      console.log(href);
      getReplies(href);
    },
    false
  );

  getReplies(1, true);

  // 댓글 모달
  const replyModal = new bootstrap.Modal(document.querySelector("#replyModal"));
  const replyModForm = document.querySelector("#replyModForm");
  // replyList.addEventListener(
  //   "click",
  //   (e) => {
  //     replyModal.show();
  //   },
  //   false
  // );

  replyList.addEventListener(
    "click",
    (e) => {
      // 가장 가까운 상위 li 요소를 찾는다.
      const targetLi = e.target.closest("li");
      const rno = targetLi.getAttribute("data-rno");

      //
      if (!rno) {
        return;
      }

      axios.get(`/replies/\${rno}`).then((res) => {
        const targetReply = res.data;
        console.log(targetReply);

        //
        if (targetReply.delflag === false) {
          replyModForm.querySelector("input[name = 'rno']").value =
            targetReply.rno;
          replyModForm.querySelector("input[name = 'replyText']").value =
            targetReply.replyText;

          replyModal.show();
        } else {
          alert("삭제된 댓글은 조회할 수 없습니다. ");
        }
      });
    },
    false
  );

  // 댓글 삭제
  document.querySelector(".btnReplyDel").addEventListener(
    "click",
    (e) => {
      e.preventDefault();
      e.stopPropagation();

      const formData = new FormData(replyModForm);
      const rno = formData.get("rno");
      console.log("rno : " + rno);

      axios.delete(`/replies/\${rno}`).then((res) => {
        const data = res.data;
        console.log(data);
        replyModal.hide();
        getReplies(currentPage);
      });
    },
    false
  );

  // 댓글 수정
  document.querySelector(".btnReplyMod").addEventListener(
    "click",
    (e) => {
      e.preventDefault();
      e.stopPropagation();

      const formData = new FormData(replyModForm);
      const rno = formData.get("rno");
      console.log("rno : " + rno);
      axios.put(`/replies/\${rno}`, formData).then((res) => {
        const data = res.data;
        console.log(data);
        replyModal.hide();
        getReplies(currentPage);
      });
    },
    false
  );
</script>

<%@ include file="/WEB-INF/views/includes/footer.jsp"%>

package org.zerock.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class ReplyListPagingDTO {
  private List<ReplyDTO> replyDTOList; // 댓글 dto 리스트
  private int totalCount; // 게시물의 댓글수
  private int page; // 현재 보고있는 페이지
  private int size; // 한 페이지에 보여줄 댓글수
  private int start; // 페이지바의 첫번째 페이지
  private int end; // 페이지바의 마지막 페이지
  private boolean prev; // 이전 페이지바 버튼 활성화 여부
  private boolean next; // 다음 페이지바 버튼 활성화 여부
  private List<Integer> pageNums; // 페이지바에 보여줄 페이지 번호들

  // 생성자에서 페이지 계산
  public ReplyListPagingDTO(List<ReplyDTO> replyDTOList, int totalCount, int page, int size) {
    this.replyDTOList = replyDTOList;
    this.totalCount = totalCount;
    this.page = page;
    this.size = size;

    // 현재 보고있는 페이지가 13 이라면 임시 끝번호는 20
    int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;

    // 시작번호 20 - 9 = 11
    this.start = tempEnd - 9;

    // 시작번호가 1 이면 이전 페이지바 버튼 비활성화
    this.prev = start == 1 ? false : true;

    // 끝번호 재계산
    if ((tempEnd * size) > totalCount) {
      // 20 * 10 > 135 이면 끝번호는 14
      this.end = (int) (Math.ceil(totalCount / (double) size));
    } else {
      this.end = tempEnd;
    }

    // 다음 페이지바로 버튼 활성화여부
    this.next = totalCount > (this.end * size);

    // 페이지바에 보여줄 숫자들
    this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();
  }
}

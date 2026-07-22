package org.zerock.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
  private Long bno;
  private String title;
  private String writer;
  private String content;
  private LocalDateTime regDate;
  private LocalDateTime updateDate;
  private boolean delFlag;

  // JSTL 날짜 처리
  public String getCreatedDate() {
    return this.regDate.format(DateTimeFormatter.ISO_DATE);
  }

  // 댓글 개수
  private int replyCnt;
}

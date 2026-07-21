package org.zerock.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.dto.ReplyDTO;
import org.zerock.dto.ReplyListPagingDTO;
import org.zerock.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
@Log4j2
public class ReplyController {
  @Autowired
  private ReplyService replyService;

  // 댓글 등록
  @PostMapping("")
  public ResponseEntity<Map<String, Long>> add(ReplyDTO replyDTO) {
    log.info(replyDTO);
    replyService.add(replyDTO);
    return ResponseEntity.ok(Map.of("result", replyDTO.getRno()));
  }

  // 댓글목록 조회
  @GetMapping("/{bno}/list")
  public ResponseEntity<ReplyListPagingDTO> listOfBoard(@PathVariable("bno") Long bno,
      @RequestParam(name = "page", defaultValue = "1") int page,
      @RequestParam(name = "size", defaultValue = "10") int size) {
    log.info("bno : " + bno);
    log.info("page : " + page);
    log.info("size : " + size);
    return ResponseEntity.ok(replyService.listOfBoard(bno, page, size));
  }

  // 댓글 조회
  @GetMapping("/{rno}")
  public ResponseEntity<ReplyDTO> read(@PathVariable("rno") Long rno) {
    log.info("rno : " + rno);
    return ResponseEntity.ok(replyService.getOne(rno));
  }
}

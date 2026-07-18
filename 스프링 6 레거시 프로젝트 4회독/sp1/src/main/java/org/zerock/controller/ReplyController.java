package org.zerock.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.dto.ReplyDTO;
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

  @PostMapping("")
  public ResponseEntity<Map<String, Long>> add(ReplyDTO replyDTO) {
    log.info(replyDTO);
    replyService.add(replyDTO);
    return ResponseEntity.ok(Map.of("result", replyDTO.getRno()));
  }
}

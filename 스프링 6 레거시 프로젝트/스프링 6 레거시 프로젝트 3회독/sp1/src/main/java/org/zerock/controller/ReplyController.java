package org.zerock.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private final ReplyService replyService;

    /**
     * 댓글 등록
     * 
     * @param replyDTO
     * @return
     */
    @PostMapping("")
    public ResponseEntity<Map<String, Long>> add(ReplyDTO replyDTO) {
	log.info(replyDTO);
	replyService.add(replyDTO);
	return ResponseEntity.ok(Map.of("result", replyDTO.getRno()));
    }

    /**
     * 댓글 목록 조회
     * 
     * @param bno
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/{bno}/list")
    public ResponseEntity<ReplyListPagingDTO> listOfBoard(@PathVariable("bno") Long bno,
	    @RequestParam(name = "page", defaultValue = "1") int page,
	    @RequestParam(name = "size", defaultValue = "10") int size) {
	log.info("bno : " + bno + "\n");
	log.info("page : " + page + "\n");
	log.info("size : " + size + "\n");
	return ResponseEntity.ok(replyService.listOfBoard(bno, page, size));
    }

    /**
     * 댓글상세조회
     * 
     * @param rno
     * @return
     */
    @GetMapping("/{rno}")
    public ResponseEntity<ReplyDTO> read(@PathVariable("rno") Long rno) {
	log.info("\nrno : " + rno);
	return ResponseEntity.ok(replyService.getOne(rno));
    }

    /**
     * 댓글 삭제
     * 
     * @param rno
     * @param replyDTO
     * @return
     */
    @DeleteMapping("/{rno}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable("rno") Long rno, ReplyDTO replyDTO) {
	log.info("rno : " + rno);
	replyService.remove(rno);
	return ResponseEntity.ok(Map.of("result", "deleted"));
    }

    /**
     * 댓글 수정
     * 
     * @param rno
     * @param replyDTO
     * @return
     */
    @PutMapping("/{rno}")
    public ResponseEntity<Map<String, String>> modify(@PathVariable("rno") Long rno, ReplyDTO replyDTO) {
	log.info("rno : " + rno);
	replyDTO.setRno(rno);
	replyService.modify(replyDTO);
	return ResponseEntity.ok(Map.of("result", "modified"));
    }
}

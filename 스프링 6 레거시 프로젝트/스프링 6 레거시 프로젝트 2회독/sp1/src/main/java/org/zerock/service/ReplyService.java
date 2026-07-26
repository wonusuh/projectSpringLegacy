package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dto.ReplyDTO;
import org.zerock.dto.ReplyListPagingDTO;
import org.zerock.mapper.ReplyMapper;
import org.zerock.service.exception.ReplyException;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ReplyService {

	private final ReplyMapper replyMapper;
	
	public void add(ReplyDTO replyDTO) {
		
		try {
			replyMapper.insert(replyDTO);
		}catch(Exception e) {
			throw new ReplyException(500, "INSERT ERROR");
		}
	}
	
	public ReplyDTO getOne(Long rno) {
		
		try {
			return replyMapper.read(rno);
		}catch(Exception e) {
			throw new ReplyException(404, "NOT FOUND");
		}
		
	}
	
	public void modify(ReplyDTO replyDTO) {
		
		try {
			int count = replyMapper.update(replyDTO);
			
			if(count == 0) {
				throw new ReplyException(404, "NOT FOUND");
			}
			
		}catch(Exception e) {
			throw new ReplyException(500, "UPDATE ERROR");
		}
		
	}
	
	public void remove(Long rno) {
		
		try {
			int count = replyMapper.delete(rno);
			
			if(count == 0) {
				throw new ReplyException(404, "NOT FOUND");
			}
			
		}catch(Exception e) {
			throw new ReplyException(500, "DELETE ERROR");
		}
		
	}
	
	public ReplyListPagingDTO listOfBoard( Long bno, int page, int size ) {
		
		try {
		
			int skip = (page -1) * size;
		
			List<ReplyDTO> replyDTOList = replyMapper.listOfBoard(bno, skip, size);
			
			int count = replyMapper.countOfBoard(bno);
			
			
			return new ReplyListPagingDTO(replyDTOList, count, page, size);
			
			
		}catch(Exception e) {
			throw new ReplyException(500, e.getMessage()); 
		}
				
	}
	
}

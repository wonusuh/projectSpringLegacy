package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.ReplyMapper;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class ReplyService {
	private final ReplyMapper replyMapper;

	@Autowired
	public ReplyService(ReplyMapper replyMapper) {
		this.replyMapper = replyMapper;
	}
}

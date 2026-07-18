package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
  @Autowired
  private ReplyMapper replyMapper;

  //
  public void add(ReplyDTO replyDTO) {
    try {
      replyMapper.insert(replyDTO);
    } catch (Exception e) {
      throw new ReplyException(500, "INSERT ERROR");
    } finally {
    }
  }

  //
  public ReplyDTO getOne(Long rno) {
    try {
      return replyMapper.read(rno);
    } catch (Exception e) {
      throw new ReplyException(404, "NOT FOUND");
    } finally {
    }
  }

  //
  public void modify(ReplyDTO dto) {
    try {
      int count = replyMapper.update(dto);

      if (count == 0) {
        throw new ReplyException(404, "NOT FOUND");
      }
    } catch (Exception e) {
      throw new ReplyException(500, "UPDATE ERROR");
    } finally {
    }
  }

  //
  public void remove(Long rno) {
    try {
      int numberOfRemoved = replyMapper.delete(rno);

      if (numberOfRemoved == 0) {
        throw new ReplyException(404, "NOT FOUND");
      }
    } catch (Exception e) {
      throw new ReplyException(500, "DELETE ERROR");
    } finally {
    }
  }

  // 페이지네이션
  public ReplyListPagingDTO listOfBoard(Long bno, int page, int size) {
    try {
      int skip = (page - 1) * size;
      List<ReplyDTO> replyDtoList = replyMapper.listOfBoard(bno, skip, size);
      int numberOfReplies = replyMapper.getNumberOfRepliesByBno(bno);
      return new ReplyListPagingDTO(replyDtoList, numberOfReplies, page, size);
    } catch (Exception e) {
      throw new ReplyException(500, e.getMessage());
    } finally {
    }
  }
}

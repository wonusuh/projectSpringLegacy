
package org.zerock.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;


@Data
public class BoardListPagingDTO {

	private List<BoardDTO> boardDTOList;
	
	private int totalCount;
	
	private int page, size;

	
	private int start, end;
	
	private boolean prev, next;
	
	private List<Integer> pageNums;

	//검색 조건
	private String types;
	
	private String keyword;



	public BoardListPagingDTO(List<BoardDTO> boardDTOList, int totalCount, int page, int size,	String types, String keyword) {

		this.boardDTOList = boardDTOList;
		this.totalCount = totalCount;
		this.page = page;
		this.size = size;
		this.types = types; //검색 관련 추가 
		this.keyword = keyword;

		
		//start계산을 위한 end 페이지 
		int tempEnd =  (int)(Math.ceil(page/10.0)) * 10;
		
		this.start = tempEnd - 9;
		
		this.prev = start != 1; //start값이 1이 아니라면 이전 페이지로 이동 필요 
		
		//임시 end 값 * size가 totalCount 보다 크다면 totalCount로 다시 계산 필요
		if( (tempEnd * size) > totalCount  ) {
			
			this.end =  (int) ( Math.ceil(totalCount / (double)size)  ); 
		
		}else {
			
			this.end = tempEnd;
		}
		
		//end 값 * size 보다 totalCount가 크다면 next로 이동 가능 
		this.next =  totalCount > (this.end * size);
		
		
		//화면에 출력한 번호들 계산 
		
		this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();

	}
	
}


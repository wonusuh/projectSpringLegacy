package org.zerock.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private Integer pno;
	private String pname;
	private String pdesc;
	private int price;
	private boolean sale;
	private String writer;
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	private List<ProductImageDTO> imageList; // 상품 이미지들

	public void addImage(String uuid, String fileName) {
		if (imageList == null) {
			imageList = new ArrayList<>();
		}

		ProductImageDTO imageDTO = ProductImageDTO.builder().uuid(uuid).fileName(fileName).pno(this.pno)
				.ord(this.imageList.size()).build();

		imageList.add(imageDTO);
	}

	public void clearImages() {
		this.imageList.clear();
	}
}

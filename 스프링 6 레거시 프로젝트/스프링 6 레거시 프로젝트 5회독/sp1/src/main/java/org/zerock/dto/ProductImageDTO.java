package org.zerock.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDTO {
	private Integer ino;
	private Integer pno;
	private String fileName;
	private String uuid;
	private int ord;
	private LocalDateTime regDate;
}

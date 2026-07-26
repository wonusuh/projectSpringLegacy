package org.zerock.dto;

import lombok.Data;

@Data
public class ProductListDTO {
	private Integer pno;
	private String pname;
	private String pdesc;
	private int price;
	private boolean sale;
	private String writer;
	private String uuid;
	private String fileName;
}

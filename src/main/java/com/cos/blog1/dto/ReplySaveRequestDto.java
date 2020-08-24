package com.cos.blog1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReplySaveRequestDto {

	private int userId;
	private int boardId;
	private String content;
}

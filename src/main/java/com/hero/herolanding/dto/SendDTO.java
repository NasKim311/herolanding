package com.hero.herolanding.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SendDTO {
	private Long boardId;
	private String name;
	private String result;
	private String write_time;
}

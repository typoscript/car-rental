package carRental.board.model;

import java.sql.Timestamp;

public class PostResponseDto {
	private String userId;
	private String title;
	private String content;
	private boolean isNotice;
	private Timestamp creationDate;
	private Timestamp modificationDate;
}
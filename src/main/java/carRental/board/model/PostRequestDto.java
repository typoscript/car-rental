package carRental.board.model;

import java.sql.Timestamp;

public class PostRequestDto {
	private String userId;
	private String title;
	private String content;
	private boolean isNotice;
	private Timestamp creationDate;
	private Timestamp modificationDate;
	
	public PostRequestDto() { }
	
	public PostRequestDto(String userId, String title, String content, boolean isNotice, Timestamp creationDate,
			Timestamp modificationDate) {
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.isNotice = isNotice;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isNotice() {
		return isNotice;
	}
	public void setNotice(boolean isNotice) {
		this.isNotice = isNotice;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public Timestamp getModificationDate() {
		return modificationDate;
	}
}

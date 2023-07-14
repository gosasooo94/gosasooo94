package mybatis.guest.model;

import java.util.Date;

//Bean = VO 객체
public class Comment {
	private long commentNo;
	private String userId;
	private String commentContent;
	private Date regDate;
	public long getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(long commenNo) {
		this.commentNo = commenNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}

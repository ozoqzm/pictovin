package model;

import java.util.Date;

public class Reply {
    private int commentId;
    private String commentText;
    private String commentDate;
    private int userId;
    private int postId;

    public Reply() {}

	public Reply(int commentId, String commentText, String commentDate, int userId, int postId) {
		super();
		this.commentId = commentId;
		this.commentText = commentText;
		this.commentDate = commentDate;
		this.userId = userId;
		this.postId = postId;
	}
	
	// 날짜 없는 버전
	public Reply(int commentId, String commentText, int userId, int postId) {
		super();
		this.commentId = commentId;
		this.commentText = commentText;
		this.userId = userId;
		this.postId = postId;
	}

	
	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

}

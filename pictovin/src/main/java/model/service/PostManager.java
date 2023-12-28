package model.service;

import java.sql.SQLException;



import java.util.List;

import model.Post;
import model.Reply;
import model.Tag;
import model.dao.PostDAO;
import model.dao.ReplyDAO;

public class PostManager {
	private static PostManager postMan = new PostManager();
	private PostDAO postDAO;
	private ReplyDAO replyDAO;
	
	 private PostManager() {
	        // PostDAO 객체를 생성하여 할당
	        this.postDAO = new PostDAO();
	        this.replyDAO = new ReplyDAO();
	    }


	public static PostManager getInstance() {
		return postMan;
	}
	
	// 글, 사진 같이 추가
//	public int createPostPhoto(Post post, Photo photo) throws SQLException {
//		return postDAO.createPostWithPhoto(post, photo);		
//	}
//	
	public Post createPost(Post post) throws SQLException {
		return postDAO.create(post);		
	}

	public int updatePost(Post post) throws SQLException {
		return postDAO.update(post);				
	}
	
	// 해당 게시글 삭제 postId로
	public int removePost(int postId) throws SQLException {
		return postDAO.remove(postId);				
	}
	
	// 해당 게시글 postId로 조회
	public Post findPost(int postId) throws SQLException {
		Post post = postDAO.findPost(postId); 
		
		return post; // 해당 post 반환
	}
	
	// 전체 postList 반환
	public List<Post> findPostList() throws SQLException {
		return postDAO.findPostList();
	}
	
	// 해당 albumId 전체 postList 반환
	public List<Post> findPostListByAlbum(int albumId) throws SQLException {
		return postDAO.findPostListByAlbum(albumId);
	}
	
	// postId로 tag 조회
	public Tag findTag(int postId) throws SQLException {
		Tag tag = postDAO.findTag(postId); 
		
		return tag; 
	}
	
	
	//댓글
	public List<Reply> findReplyListByPost(int postId) throws SQLException {
		return replyDAO.findReplyListByPost(postId);
	}
	
	public Reply createReply(Reply reply) throws SQLException {
		return replyDAO.create(reply);		
	}
	
	
}

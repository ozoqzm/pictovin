package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.Post;
import model.Reply;

public class ReplyDAO {
    private JDBCUtil jdbcUtil = null;

    public ReplyDAO() {
        jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
    }

    public Reply create(Reply reply) throws SQLException {
        String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String sql = "INSERT INTO Reply (commentID, commentTEXT, commentDATE, USERID, POSTID) "
        		+ "VALUES (SEQUENCE_POSTID.nextval, ?, ?, ?, ?)";
        Object[] param = new Object[] { 
        		reply.getCommentText(), 
        		formattedDateTime, 
        		reply.getUserId(), 
        		reply.getPostId() 
        		};
        jdbcUtil.setSqlAndParameters(sql, param);
		
		String key[] = {"commentId"};	// PK 컬럼의 이름     
		try {    
			jdbcUtil.executeUpdate(key);  
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);  
		   		reply.setCommentId(generatedKey);   
		   	}
		   	return reply;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return null;	
	}
    

 // 해당 postId의 전체 댓글 조회..
 	public List<Reply> findReplyListByPost(int postId) throws SQLException {
 		String sql = "SELECT * FROM Reply WHERE postId=?";

 		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});

 		try {
 			ResultSet rs = jdbcUtil.executeQuery();
 			List<Reply> replyList = new ArrayList<>();
 			while (rs.next()) {
 				Reply reply = new Reply(
 						rs.getInt("commentId"),
 						rs.getString("commentText"),
 						rs.getString("commentDate"),
 						rs.getInt("userId"),
 						rs.getInt("postId")
 				
 						);
 				replyList.add(reply);
 			}
 			return replyList;
 		} catch (Exception ex) {
 			ex.printStackTrace();
 		} finally {
 			jdbcUtil.close();
 		}
 		return null;
 	}

   
}

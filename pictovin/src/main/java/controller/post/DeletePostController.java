package controller.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Photo;
import model.Post;
import model.Reply;
import model.Tag;
import model.service.PhotoManager;
import model.service.PostManager;

public class DeletePostController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(DeletePostController.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		PostManager pmanager = PostManager.getInstance();

		int albumId =  Integer.parseInt(request.getParameter("albumId"));
		int postId = Integer.parseInt(request.getParameter("postId"));

		// userId 받아오기
		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		System.out.println("사용자 아이디: " + user_id);
		int userId = Integer.parseInt(user_id);

		Post post = null;
		post = pmanager.findPost(postId);		// 일기 정보 검색
		int owner = post.getUserId();

		if (owner == userId) { 
			pmanager.removePost(postId);			
			return "redirect:/post/list?albumId=" + albumId;
		}

		/* 삭제가 불가능한 경우 */
		Photo photo = null;
		Tag tag = null;
		PostManager manager = PostManager.getInstance();
		PhotoManager phomanager = PhotoManager.getInstance();
		List<Reply> replyList = manager.findReplyListByPost(postId);
		photo = phomanager.getPhoto(postId);
		tag = manager.findTag(postId);
		
		request.setAttribute("post", post);	
		request.setAttribute("photo", photo);	
		request.setAttribute("tag", tag);	
		request.setAttribute("replyList", replyList);	
		int replyListSize = replyList.size();
		request.setAttribute("replyListSize", replyListSize);			
		
		request.setAttribute("deleteFailed", true);
		String msg = "타인이 작성한 일기는 삭제할 수 없습니다.";													
		request.setAttribute("exception", new IllegalStateException(msg));            
		return "/post/postView.jsp";		//(forwarding)	


	}
}


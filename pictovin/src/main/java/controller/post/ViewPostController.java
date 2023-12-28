package controller.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;



import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.PostManager;
import model.service.PhotoManager;
import model.Post;
import model.Photo;
import model.Tag;
import model.Reply;

//postId 로 post 상세 조회하기
public class ViewPostController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			

		Photo photo = null;
		Post post = null;
		Tag tag = null;

		PostManager manager = PostManager.getInstance();
		PhotoManager phomanager = PhotoManager.getInstance();

		int postId = Integer.parseInt(request.getParameter("postId"));
		List<Reply> replyList = manager.findReplyListByPost(postId);

		post = manager.findPost(postId);		// 일기 정보 검색
		photo = phomanager.getPhoto(postId);
		tag = manager.findTag(postId);

		request.setAttribute("post", post);	// 일기 정보 저장	
		request.setAttribute("photo", photo);	
		request.setAttribute("tag", tag);	
		request.setAttribute("replyList", replyList);	

		// 댓글 배열 크기 저장
		int replyListSize = replyList.size();
		request.setAttribute("replyListSize", replyListSize);

		return "/post/postView.jsp";				// 일기 보기 화면으로 이동
	}
}

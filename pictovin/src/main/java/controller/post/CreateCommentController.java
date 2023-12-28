package controller.post;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Reply;
import model.service.PostManager;


public class CreateCommentController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateCommentController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// userId
		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		System.out.println("사용자 아이디: " + user_id);
		int userId = Integer.parseInt(user_id);
		
		int postId = Integer.parseInt(request.getParameter("postId"));
		String commentText = request.getParameter("commentText");

		// 추가
		Reply reply = new Reply(
				0, 
				commentText,
				userId,
				postId
				);
		try {
			PostManager manager = PostManager.getInstance();
			manager.createReply(reply); // 게시글 생성

			log.debug("Create Reply : {}", reply);

			return "redirect:/post/view?postId=" + postId;


		} catch (Exception e) {		// 예외 발생 시 입력 form으로 forwarding
			request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("reply", reply); 

			return "/post/postView.jsp";
		}


	}
}


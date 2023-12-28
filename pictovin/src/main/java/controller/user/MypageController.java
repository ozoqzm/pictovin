package controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.User;
import model.service.UserManager;


public class MypageController implements Controller {
   @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 여부 확인
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/user/login/form";		// login form 요청으로 redirect
		}
		
		String userId = UserSessionUtils.getLoginUserId(request.getSession());
        
		UserManager manager = UserManager.getInstance();
		User user = manager.findUser(userId);
	
		// user 객체와 현재 로그인한 사용자 ID를 request에 저장하여 전달
		request.setAttribute("user", user);				
		request.setAttribute("curUserId",
		UserSessionUtils.getLoginUserId(request.getSession()));		
				
		// 사용자 리스트 화면으로 이동(forwarding)
		return "/user/mypage.jsp";
    }
}
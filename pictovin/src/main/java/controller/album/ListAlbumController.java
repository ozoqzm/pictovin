package controller.album;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Album;
import model.User;
import model.service.AlbumManager;
import model.service.UserManager;

/*
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
*/

public class ListAlbumController implements Controller {
   @SuppressWarnings("unchecked")
   @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 여부 확인
		if (!UserSessionUtils.hasLogined(request.getSession())) {
			return "redirect:/user/login/form";		// login form 요청으로 redirect
		}
		
		String login_id = UserSessionUtils.getLoginUserId(request.getSession());
        int loginId = Integer.parseInt(login_id);
        
		AlbumManager manager = AlbumManager.getInstance();
		List<Album> albumList = manager.findAlbumList();
//		System.out.println("Controller: " + albumList);
		
		// userList 객체와 현재 로그인한 사용자 ID를 request에 저장하여 전달
		request.setAttribute("albumList", albumList);				
		request.setAttribute("curUserId",
				UserSessionUtils.getLoginUserId(request.getSession()));		
		
		// 사용자 리스트 화면으로 이동(forwarding)
		return "/album/albumList.jsp";    
    }
}
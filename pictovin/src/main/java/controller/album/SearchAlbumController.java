package controller.album;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

public class SearchAlbumController implements Controller {
   @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	   String search = request.getParameter("search");
	   try {
		   AlbumManager manager = AlbumManager.getInstance();
		   List<Album> albumList = manager.resultAlbumList(search);
		   
		   request.setAttribute("albumList", albumList);				
		   request.setAttribute("curUserId",
					UserSessionUtils.getLoginUserId(request.getSession()));
           
           return "/album/searchAlbum.jsp";
		} catch (Exception e) {
			request.setAttribute("searchFailed", true);
			request.setAttribute("exception", e);
			return "/album/searchAlbum.jsp";		
		}
    }
}
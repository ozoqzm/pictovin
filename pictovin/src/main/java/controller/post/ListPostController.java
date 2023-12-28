package controller.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.service.PhotoManager;
import model.Photo;

// 일기 전체 목록 컨트롤러- 앨범으로 찾기
public class ListPostController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
    	PhotoManager manager = PhotoManager.getInstance();
    	
    	String albumIdParam = request.getParameter("albumId");
    	int albumId = Integer.parseInt(albumIdParam);
    	
		List<Photo> photoList = manager.findPhotoListByAlbum(albumId);
		
		// postList 객체를 request에 저장하여 포스트 리스트 화면으로 이동(forwarding)
		request.setAttribute("photoList", photoList);	
		
		
		return "/post/postList.jsp";        
    }
}

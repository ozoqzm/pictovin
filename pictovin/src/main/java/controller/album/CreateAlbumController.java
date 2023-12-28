package controller.album;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;
import model.Album;
import model.service.AlbumManager;

// 앨범 생성
public class CreateAlbumController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CreateAlbumController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       
       String login_id =  UserSessionUtils.getLoginUserId(request.getSession());
      int loginId = Integer.parseInt(login_id);
      
      Album album = new Album (
             request.getParameter("albumName"),
             "hyejin",
            "hi",
            request.getParameter("isShared"),
            loginId
             );
      try {
         AlbumManager manager = AlbumManager.getInstance();
         
         manager.createAlbum(album, loginId);
         
           return "redirect:/album/list";   // 성공 시 앨범 리스트 화면으로 redirect
           
      } catch (Exception e) {      
         // 예외 발생 시 입력 form으로 forwarding
            request.setAttribute("creationFailed", true);
         request.setAttribute("exception", e);
         request.setAttribute("album", album);
         return "/album/create.jsp";
      }
    }
}
package controller.recommend;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.Controller;
import model.service.RecommendManager;
import model.Photo;
import model.Tag;


// 일기 전체 목록 컨트롤러- 앨범으로 찾기
public class ListRecommController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)    throws Exception {

        RecommendManager manager = RecommendManager.getInstance();

        String studio = request.getParameter("studio");
        String concept = request.getParameter("concept");
        String people = request.getParameter("people");
        String ageGroup = request.getParameter("ageGroup");

        Tag tag = new Tag(
                studio,
                concept,
                people,
                ageGroup
                );

        List<Photo> photoList = manager.findPhotoListByTag(tag);
        int listSize = photoList.size();
        if (listSize > 0)
            request.setAttribute("havePhoto", "True");
        else 
            request.setAttribute("havePhoto", "False");

        // postList 객체를 request에 저장하여 포스트 리스트 화면으로 이동(forwarding)
        request.setAttribute("photoList", photoList);


        return "/recommend/conceptSearch.jsp";
    }
}
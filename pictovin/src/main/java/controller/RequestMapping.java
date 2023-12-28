package controller;

import java.util.HashMap;


import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.user.*;
import controller.album.*;
import controller.post.*;
import controller.recommend.*;

public class RequestMapping {
	private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

	// 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
	private Map<String, Controller> mappings = new HashMap<String, Controller>();

	public void initMapping() {
		// 각 uri에 대응되는 controller 객체를 생성 및 저장

		mappings.put("/", new ForwardController("index.jsp"));
		/* 메인페이지: 로그인, 회원가입, 사진관 정보 */
		mappings.put("/user/main", new ForwardController("/user/main.jsp"));

		mappings.put("/post/createForm", new ForwardController("/post/createPost.jsp"));
		mappings.put("/post/create", new CreatePostController());
		mappings.put("/post/view", new ViewPostController());
		mappings.put("/post/edit", new ForwardController("/post/editPost.jsp"));
		mappings.put("/post/list", new ListPostController());
	    mappings.put("/post/delete", new DeletePostController());
	    mappings.put("/reply/create", new CreateCommentController());
	    
	    mappings.put("/post/deco", new ForwardController("/post/decoPost.jsp"));
		mappings.put("/post/tag", new ForwardController("/post/addTag.jsp"));
		mappings.put("/post/addtag", new CreateTagController());

		mappings.put("/recommend/concept", new ForwardController("/recommend/conceptSearch.jsp"));
		mappings.put("/recommend/search",  new ListRecommController());
		mappings.put("/recommend/studio", new ForwardController("/recommend/Studio.jsp"));

		// 앨범
//		mappings.put("/album/list", new ForwardController("/album/albumList.jsp"));
        mappings.put("/album/list", new ListAlbumController());
		mappings.put("/album/search", new ForwardController("/album/searchAlbum.jsp"));
		mappings.put("/album/create", new ForwardController("/album/createAlbum.jsp"));
		mappings.put("/album/createForm",  new CreateAlbumController());
		mappings.put("/album/search/algo",  new SearchAlbumController());

		// 아래 내용 픽토빈에 맞게 수정해야 함
		mappings.put("/user/login/form", new ForwardController("/user/login.jsp"));
		mappings.put("/user/login", new LoginController());
//        mappings.put("/user/logout", new LogoutController());
//        mappings.put("/user/list", new ListUserController());
//        mappings.put("/user/view", new ViewUserController());
//        
//        // 회원 가입 폼 요청과 가입 요청 처리 병합 (폼에 커뮤니티 선택 메뉴 추가를 위함)
		mappings.put("/user/join/form", new ForwardController("/user/join.jsp"));
		mappings.put("/user/register", new RegisterUserController());
		
		//알람
		mappings.put("/user/alarm", new ForwardController("/user/userAlarm.jsp"));
		
		//마이페이지
		mappings.put("/user/mypage", new MypageController());
//
//        // 사용자 정보 수정 폼 요청과 수정 요청 처리 병합
////      mappings.put("/user/update/form", new UpdateUserFormController());
		mappings.put("/user/update", new UpdateUserController());        
//        mappings.put("/user/update", new UpdateUserController());
//        
//        mappings.put("/user/delete", new DeleteUserController());
//        
//        // 커뮤니티 관련 request URI 추가
//        mappings.put("/community/list", new ListCommunityController());
//        mappings.put("/community/view", new ViewCommunityController());
//        mappings.put("/community/create/form", new ForwardController("/community/creationForm.jsp"));
//        mappings.put("/community/create", new CreateCommunityController());
//        mappings.put("/community/update", new UpdateCommunityController());

		logger.info("Initialized Request Mapping!");
	}

	public Controller findController(String uri) {
		// 주어진 uri에 대응되는 controller 객체를 찾아 반환
		return mappings.get(uri);
	}
}
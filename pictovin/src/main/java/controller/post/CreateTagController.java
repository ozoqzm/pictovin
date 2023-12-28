package controller.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import model.Tag;
import model.service.TagManager;

public class CreateTagController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String studio = request.getParameter("studio");
		String concept = request.getParameter("concept");
		String people = request.getParameter("people");
		String ageGroup = request.getParameter("ageGroup");
		int postId = Integer.parseInt(request.getParameter("postId"));
		int albumId = Integer.parseInt(request.getParameter("albumId"));
	
		// 태그 생성
		Tag tag = new Tag(postId, studio, concept, people, ageGroup);

		try {
			TagManager tagManager = new TagManager();
			tagManager.createTag(tag);

			// 선택한 태그 정보를 request에 저장
			request.setAttribute("selectedTag", tag);

			// post/view로 리다이렉트
			return "redirect:/post/list?albumId=" + albumId;
		} catch (Exception e) {
			request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("tag", tag);
			return "/tag/createTag.jsp";
		}
	}
}

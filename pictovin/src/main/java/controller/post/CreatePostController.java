package controller.post;

import java.io.File;

import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.user.UserSessionUtils;

import model.Post;
import model.Photo;
import model.service.PostManager;
import model.service.PhotoManager;

// 파일 업로드 APIs 
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
// 파일 용량 초과에 대한 Exception 클래스를 FileUploadBase 클래스의 Inner 클래스로 처리
import org.apache.commons.fileupload.servlet.*;

public class CreatePostController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreatePostController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String postDate = null;
		String content = null;
		String privacyStatus = null;
		String imagePath = null;
		int albumId = 0;
		String albumIdParam;

		// userId
		String user_id = UserSessionUtils.getLoginUserId(request.getSession());
		System.out.println("사용자 아이디: " + user_id);
		int userId = Integer.parseInt(user_id);


		boolean check = ServletFileUpload.isMultipartContent(request);
		if (check) {
			ServletContext context = request.getServletContext();
			String path = context.getRealPath("/upload");
			File dir = new File(path);

			if (!dir.exists())
				dir.mkdir(); // 전송된 파일을 저장할 폴더 생성

			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(10 * 1024);
				factory.setRepository(dir);

				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(10 * 1024 * 1024);
				upload.setHeaderEncoding("utf-8");

				List<FileItem> items = (List<FileItem>) upload.parseRequest(request);
				log.debug("items: {}", items);

				for (int i = 0; i < items.size(); ++i) {
					FileItem item = (FileItem) items.get(i);

					String value = item.getString("utf-8");

					if (item.isFormField()) {
						if (item.getFieldName().equals("postDate"))
							postDate = value;
						else if (item.getFieldName().equals("content"))
							content = value;
						else if (item.getFieldName().equals("privacyStatus"))
							privacyStatus = value; // 공개범위
						else if (item.getFieldName().equals("albumId")) {
							albumIdParam = value;
							albumId = Integer.parseInt(albumIdParam);
						}
					} else { // item이 파일인 경우
						if (item.getFieldName().equals("imagePath")) {
							String oriFilename = item.getName(); // 파일 이름 획득 (자동 한글 처리 됨)
							if (oriFilename == null || oriFilename.trim().length() == 0)
								continue;

							imagePath = oriFilename.substring(oriFilename.lastIndexOf("\\") + 1);

							// 랜덤이름
							imagePath = UUID.randomUUID().toString()
									+ oriFilename.substring(oriFilename.lastIndexOf("."));

							File file = new File(dir, imagePath);
							item.write(file);
						}
					}
				}
			} catch (SizeLimitExceededException e) {
				e.printStackTrace();
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("dir", dir);
			request.setAttribute("photo", imagePath);
			log.debug("dir: {}", dir);
			log.debug("filepath: {}", imagePath);
		}

		// 추가
		Post post = new Post(0, // 일단 0으로.. 시퀀스
				content, privacyStatus, postDate, userId, albumId);

		try {
			PostManager manager = PostManager.getInstance();
			Post postNew = manager.createPost(post); // 게시글 생성

			Photo photo = new Photo(imagePath, postNew.getPostId() // postId--> 이거 어쩜?
					);

			PhotoManager pmanager = PhotoManager.getInstance();
			pmanager.createPhoto(photo); // 게시글 생성

			log.debug("Create Post : {}", post);
			log.debug("Create Photo : {}", photo);
			log.debug("uploaded file: {}", imagePath);
			int postId = postNew.getPostId();
			return "redirect:/post/tag?postId=" + postId + "&albumId=" + albumId;
		} catch (Exception e) { // 예외 발생 시 입력 form으로 forwarding
			request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("post", post);
			request.setAttribute("photo", imagePath);

			return "/post/createPost.jsp";
		}

	}
}

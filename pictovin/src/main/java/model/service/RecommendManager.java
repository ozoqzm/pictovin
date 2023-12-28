package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Tag;
import model.Photo;
import model.dao.PhotoDAO;

public class RecommendManager {
	private static RecommendManager recMan = new RecommendManager();
	private PhotoDAO photoDAO;
	
	 private RecommendManager() {
	        // PostDAO 객체를 생성하여 할당
	        this.photoDAO = new PhotoDAO();
	    }

	public static RecommendManager getInstance() {
		return recMan;
	}
	
	// 해당 tag와 겹치는 전체 postList 반환.. 추후 작성
	// photoDAO에 질의 작성
	public List<Photo> findPhotoListByTag(Tag tag) throws SQLException {
		return photoDAO.findPhotoListByTag(tag);
	}
	
	
	

	
	
}

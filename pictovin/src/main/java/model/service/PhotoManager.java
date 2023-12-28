package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Photo;
import model.dao.PhotoDAO;

public class PhotoManager {
	private static PhotoManager photoMan = new PhotoManager();
	private PhotoDAO photoDAO;
	
	 private PhotoManager() {
	        // PostDAO 객체를 생성하여 할당
	        this.photoDAO = new PhotoDAO();
	    }

	public static PhotoManager getInstance() {
		return photoMan;
	}
		
	// 사진 업로드
	public int createPhoto(Photo photo) throws SQLException {
		return photoDAO.createPhoto(photo);		
	}

	// 해당 사진의 postId로 조회
	public Photo getPhoto(int postId) throws SQLException {
		Photo photo = photoDAO.getPhoto(postId); 
		
		return photo; // 해당 post 반환
	}
	
	// 사진 리스트 전체 조회
	public List<Photo> findPhotoListByAlbum(int albumId) throws SQLException {
		List<Photo> photoList = photoDAO.findPhotoListByAlbum(albumId); 
		
		return photoList; // 해당 post 반환
	}
	
}

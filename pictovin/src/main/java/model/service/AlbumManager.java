package model.service;

import java.sql.SQLException;



import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import model.Membership;
import model.User;
import model.Album;
import model.dao.AlbumDAO;
import model.dao.UserDAO;
/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * UserDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
public class AlbumManager {

    private static AlbumManager albumManager = new AlbumManager();
    private UserDAO userDAO;
    private AlbumDAO albumDAO;
    
    private UserAnalysis userAanlysis;

    private AlbumManager() {
        try {
            userDAO = new UserDAO();
            albumDAO = new AlbumDAO();
            userAanlysis = new UserAnalysis(userDAO);
        } catch (Exception e) {
            e.printStackTrace();
        }           
    }
    
    public static AlbumManager getInstance() {
        return albumManager;
    }
    
    // 앨범 생성
    public int createAlbum(Album album, int userId) throws SQLException {
        return albumDAO.create(album, userId);        
    }
  
    // 사용자 앨범 리스트
    public List<Album> findMyAlbumList(int userId) throws SQLException {
        return albumDAO.findMyAlbum(userId);
    }
    
    // 전체 앨범 리스트
    public List<Album> findAlbumList() throws SQLException {
        return albumDAO.findAlbumList();
    }
    
    public List<Album> resultAlbumList(String input) throws SQLException, ResultNotFoundException {
    	List<Album> albumList = albumDAO.resultAlbumList(input);
        if (albumList == null || input == "") {
			throw new ResultNotFoundException("검색결과가 없습니다.");
		}
        return albumList;
    }
}
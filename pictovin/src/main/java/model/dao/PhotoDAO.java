package model.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Photo;
import model.Tag;
import model.Post;

public class PhotoDAO {
	private JDBCUtil jdbcUtil = null;

	public PhotoDAO() {
		jdbcUtil = new JDBCUtil(); 
	}

	// 사진 추가
	public int createPhoto(Photo photo) throws SQLException {
		String sql = "INSERT INTO Photo VALUES (?, ?)";

		Object[] param = new Object[] {photo.getImagePath(), photo.getPostId()};

		try {
			jdbcUtil.setSqlAndParameters(sql, param);
			return jdbcUtil.executeUpdate();
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}

	// 해당 postId 사진 조회
	public Photo getPhoto(int postId) throws SQLException {
		String sql = "SELECT * FROM Photo WHERE postId=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				Photo photo = new Photo(
						rs.getString("imagePath"),
						rs.getInt("postId")
						);
				return photo;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}


	public List<Photo> findPhotoListByAlbum(int albumId) throws SQLException {
		String sql = "SELECT ph.imagePath, p.postId, p.postDate "
				+ "FROM POST p JOIN PHOTO ph ON p.postId = ph.postId "
				+ "WHERE p.albumId=?";

		jdbcUtil.setSqlAndParameters(sql, new Object[]{albumId});

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Photo> photoList = new ArrayList<>();
			while (rs.next()) {
				Photo photo = new Photo(
						rs.getString("imagePath"),
						rs.getInt("postId"),
						rs.getString("postDate") // 추가
						);
				photoList.add(photo);
			}
			return photoList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	// 일치하는 태그 포토 리스트
	public List<Photo> findPhotoListByTag(Tag tag) throws SQLException {
		  String sql = "SELECT P.IMAGEPATH, P.POSTID "
		            + "FROM PHOTO P JOIN TAG T ON P.POSTID = T.POSTID "
		            + "JOIN POST PS ON P.POSTID = PS.POSTID "  // 추가
		            + "WHERE (T.STUDIO = ? OR ? IS NULL) "
		            + "AND (T.CONCEPT = ? OR ? IS NULL) "
		            + "AND (T.PEOPLE = ? OR ? IS NULL) "
		            + "AND (T.AGEGROUP = ? OR ? IS NULL) "
		            + "AND PS.PRIVACYSTATUS = 'Public'";  // 추가. public일 때만 보여지도록

		jdbcUtil.setSqlAndParameters(sql, new Object[]{
				tag.getStudio(), tag.getStudio(),
				tag.getConcept(), tag.getConcept(),
				tag.getPeople(), tag.getPeople(),
				tag.getAgeGroup(), tag.getAgeGroup()
		});

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Photo> photoList = new ArrayList<>();
			while (rs.next()) {
				Photo photo = new Photo(
						rs.getString("imagePath"),
						rs.getInt("postId")
						);
				photoList.add(photo);
			}
			return photoList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	/*
	 * public static void main(String[] args) { PhotoDAO photoDAO = new PhotoDAO();
	 * 
	 * try { // 특정 albumId에 해당하는 photoList 조회 int albumId = 1; // 특정 albumId 값으로
	 * 변경해주세요 List<Photo> photoList = photoDAO.findPhotoListByAlbum(albumId);
	 * 
	 * // 조회 결과 출력 if (photoList != null) { for (Photo photo : photoList) {
	 * System.out.println("Image Path: " + photo.getImagePath());
	 * System.out.println("Post ID: " + photo.getPostId());
	 * System.out.println("Post Date: " + photo.getPostDate());
	 * System.out.println("-----------------------"); } } else {
	 * System.out.println("해당 albumId의 photoList가 존재하지 않습니다."); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); }
	 * 
	 * 
	 * try { // Tag 객체 생성 (원하는 값으로 설정) Tag tag = new Tag(); tag.setStudio("인생네컷");
	 * tag.setConcept(null); tag.setPeople(null); tag.setAgeGroup(null);
	 * 
	 * // 함수 호출 List<Photo> photoList = photoDAO.findPhotoListByTag(tag);
	 * 
	 * // 결과 출력 if (photoList != null) { System.out.println("--------------"); for
	 * (Photo photo : photoList) { System.out.println("Image Path: " +
	 * photo.getImagePath()); System.out.println("Post ID: " + photo.getPostId());
	 * System.out.println("--------------"); } } else {
	 * System.out.println("해당 tag의 photoList가 존재하지 않습니다."); } } catch (SQLException
	 * e) { e.printStackTrace(); } }
	 */


}

package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import model.Tag;

public class TagDAO {
	private JDBCUtil jdbcUtil = null;

	public TagDAO() {
		jdbcUtil = new JDBCUtil();
	}

	public int createTag(Tag tag) throws SQLException {
		String sql = "INSERT INTO Tag (studio, concept, people, agegroup, postid) VALUES (?, ?, ?, ?, ?)";
		Object[] param = new Object[] { tag.getStudio(), tag.getConcept(), tag.getPeople(), tag.getAgeGroup(),
				tag.getPostId() };

		jdbcUtil.setSqlAndParameters(sql, param);

		String key[] = { "postId" };
		//int generatedKey = 0; // 변수 선언 및 초기화

		try {
			int generatedKey = jdbcUtil.executeUpdate(key); // insert 문 실행
			ResultSet rs = jdbcUtil.getGeneratedKeys();
			if (rs.next()) {
				generatedKey = rs.getInt(1); // 생성된 PK 값
				tag.setPostId(generatedKey); // id필드에 저장
			}
			return generatedKey;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}

	public Tag getTag(int postId) throws SQLException {
		String sql = "SELECT * FROM Tag WHERE postid=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { postId });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				Tag tag = new Tag(rs.getInt("postId"), rs.getString("studio"), rs.getString("concept"),
						rs.getString("people"), rs.getString("ageGroup"));
				return tag;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	public List<Tag> getTags(int postId) throws SQLException {
		String sql = "SELECT * FROM Tag WHERE postid=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { postId });

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<Tag> tagList = new ArrayList<>();
			while (rs.next()) {
				Tag tag = new Tag(rs.getInt("postId"), rs.getString("studio"), rs.getString("concept"),
						rs.getString("people"), rs.getString("ageGroup"));
				tagList.add(tag);
			}
			return tagList;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

}

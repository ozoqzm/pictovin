package model.dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import model.Photo;
import model.Post;
import model.Tag;

public class PostDAO {
   private JDBCUtil jdbcUtil = null;

   public PostDAO() {
      jdbcUtil = new JDBCUtil();
   }

   // 사진 추가
   public int createPhoto(Photo photo) throws SQLException {
      String sql = "INSERT INTO Photo VALUES (?, ?)";

      Object[] param = new Object[] { photo.getImagePath(), photo.getPostId() };

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

   // SEQUENCE_POSTID.nextval
   // 새 게시글 추가
   public Post create(Post post) throws SQLException {
      String sql = "INSERT INTO Post (postId, postcontent, privacyStatus, postDate, userId, albumId) "
            + "VALUES (SEQUENCE_POSTID.nextval, ?, ?, ?, ?, ?)";
      Object[] param = new Object[] { post.getContent(), post.getPrivacyStatus(), post.getPostDate(),
            post.getUserId(), post.getAlbumId() };
      jdbcUtil.setSqlAndParameters(sql, param);

      String key[] = { "postId" }; // PK 컬럼의 이름
      try {
         jdbcUtil.executeUpdate(key); // insert 문 실행
         ResultSet rs = jdbcUtil.getGeneratedKeys();
         if (rs.next()) {
            int generatedKey = rs.getInt(1); // 생성된 PK 값
            post.setPostId(generatedKey); // id필드에 저장
         }
         return post;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      } finally {
         jdbcUtil.commit();
         jdbcUtil.close(); // resource 반환
      }
      return null;
   }

   // 게시글 수정
   public int update(Post post) throws SQLException {
      String sql = "UPDATE Post " + "SET postcontent=?, privacyStatus=?, postDate=SYSDATE, userId=?, albumId=? "
            + "WHERE postId=?";
      Object[] param = new Object[] { post.getContent(), post.getPrivacyStatus(), post.getUserId(), post.getAlbumId(),
            post.getPostId() };
      jdbcUtil.setSqlAndParameters(sql, param);

      try {
         return jdbcUtil.executeUpdate();
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();
      }
      return 0;
   }

   // 게시글 삭제
   public int remove(int postId) throws SQLException {
      String sql = "DELETE FROM Post WHERE postId=?";
      jdbcUtil.setSqlAndParameters(sql, new Object[] { postId });

      try {
         int result = jdbcUtil.executeUpdate(); // delete 문 실행
         return result;
      } catch (Exception ex) {
         jdbcUtil.rollback();
         ex.printStackTrace();
      } finally {
         jdbcUtil.commit();
         jdbcUtil.close(); // resource 반환
      }
      return 0;
   }

   // 해당 postId 게시글 조회
   public Post findPost(int postId) throws SQLException {
      String sql = "SELECT * FROM Post WHERE postId=?";
      jdbcUtil.setSqlAndParameters(sql, new Object[] { postId });

      try {
         ResultSet rs = jdbcUtil.executeQuery();
         if (rs.next()) {
            Post post = new Post(rs.getInt("postId"), rs.getString("postcontent"), rs.getString("privacyStatus"),
                  rs.getString("postDate"), rs.getInt("userId"), rs.getInt("albumId"));
            return post;
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();
      }
      return null;
   }

   // 모든 post 테이블 게시글 조회, postList반환
   public List<Post> findPostList() throws SQLException {
       String sql = "SELECT * FROM Post";
       jdbcUtil.setSqlAndParameters(sql, null);

       try {
           ResultSet rs = jdbcUtil.executeQuery();
           List<Post> postList = new ArrayList<>();
           while (rs.next()) {
               Post post = new Post(
                       rs.getInt("postId"),
                       rs.getString("postcontent"),
                       rs.getString("privacyStatus"),
                       rs.getString("postDate"),
                       rs.getInt("userId"),
                       rs.getInt("albumId")
               );
               postList.add(post);
           }

           // postId를 기준으로 오름차순으로 정렬
           postList.sort(Comparator.comparing(Post::getPostId));

           return postList;
       } catch (Exception ex) {
           ex.printStackTrace();
       } finally {
           jdbcUtil.close();
       }
       return null;
   }


   // 해당 albumId의 전체 postList 조회..
   public List<Post> findPostListByAlbum(int albumId) throws SQLException {
      /*
       * String sql = "SELECT * FROM Post WHERE albumId=?";
       */

      String sql = "SELECT * FROM Post ORDER BY postDate DESC";
      jdbcUtil.setSqlAndParameters(sql, new Object[] { albumId });

      try {
         ResultSet rs = jdbcUtil.executeQuery();
         List<Post> postList = new ArrayList<>();
         while (rs.next()) {
            Post post = new Post(rs.getInt("postId"), rs.getString("postcontent"), rs.getString("privacyStatus"),
                  rs.getString("postDate"), rs.getInt("userId"), rs.getInt("albumId"));
            postList.add(post);
         }
         return postList;
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         jdbcUtil.close();
      }
      return null;
   }

   // 해당 게시물의 태그 출력 (행 하나)
   public Tag findTag(int postId) throws SQLException {
      String sql = "SELECT * FROM Tag WHERE postId=?";
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

   public static void main(String[] args) {
      PostDAO postDAO = new PostDAO();

      try {
         // 원하는 postId를 지정하여 findPost 메서드 호출
         int postIdToFind = 1; // 예시로 postId를 1로 가정
         Post foundPost = postDAO.findPost(postIdToFind);

         // 찾은 게시글이 있으면 출력
         if (foundPost != null) {
            System.out.println("게시글을 찾았습니다.");
            System.out.println("게시글 ID: " + foundPost.getPostId());
            System.out.println("게시글 내용: " + foundPost.getContent());
            System.out.println("개인정보 공개 여부: " + foundPost.getPrivacyStatus());
            System.out.println("게시일: " + foundPost.getPostDate());
            System.out.println("사용자 ID: " + foundPost.getUserId());
            System.out.println("앨범 ID: " + foundPost.getAlbumId());
         } else {
            System.out.println("해당 ID에 해당하는 게시글을 찾지 못했습니다.");
         }

         // 게시글 삭제 테스트
         int postIdToRemove = 48; // 예시로 삭제할 게시글 ID를 설정
         int removedRows = postDAO.remove(postIdToRemove);

         // 삭제된 행의 개수 출력
         System.out.println("삭제된 행의 개수: " + removedRows);

         // 삭제 후 해당 ID의 게시글을 찾아보기
         Post removedPost = postDAO.findPost(postIdToRemove);

         // 찾은 게시글이 있으면 출력
         if (removedPost != null) {
            System.out.println("삭제 후 해당 ID의 게시글을 찾았습니다.");
            System.out.println("게시글 ID: " + removedPost.getPostId());
            System.out.println("게시글 내용: " + removedPost.getContent());
            System.out.println("개인정보 공개 여부: " + removedPost.getPrivacyStatus());
            System.out.println("게시일: " + removedPost.getPostDate());
            System.out.println("사용자 ID: " + removedPost.getUserId());
            System.out.println("앨범 ID: " + removedPost.getAlbumId());
         } else {
            System.out.println("해당 ID의 게시글은 삭제되었습니다.");
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

}
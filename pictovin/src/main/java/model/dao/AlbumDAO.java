package model.dao;

import java.sql.ResultSet;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import model.Album;
import model.Membership;
import model.Post;

public class AlbumDAO {
    private JDBCUtil jdbcUtil = null;

    public AlbumDAO() {
        jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
    }
    
    public int create(Album album, int userId) throws SQLException {
     
        String sql = "INSERT INTO Album " +
                "VALUES (sequence_albumId.nextval, ?, ?, ?, ?, ?, ?)";
        Object[] param = new Object[]{
                album.getAlbumName(),
                album.getCreateDate(),
                album.getCreatorName(),
                album.getExplanation(),
                album.getIsShared(),
                userId
        };
        
        try {
            jdbcUtil.setSqlAndParameters(sql, param);
            int result = jdbcUtil.executeUpdate(); // 앨범생성 실행
            return result;
            
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        return 0;
    }

    // 모든 post 테이블 게시글 조회, postList반환
    public List<Album> findAlbumList() throws SQLException {
        String sql = "SELECT * FROM Album";
        jdbcUtil.setSqlAndParameters(sql, null);
        ResultSet rs = null; 

        try {
            rs = jdbcUtil.executeQuery();
            List<Album> albumList = new ArrayList<>();
            while (rs.next()) {
                int albumId = rs.getInt("albumId");
                Album album = new Album(
                        albumId,
                        rs.getString("albumName"),
                        rs.getString("createDate"),
                        rs.getString("creatorName"),
                        rs.getString("explanation"),
                        rs.getString("isShared"),
                        rs.getInt("userId")
                );
                System.out.println("albumId: " + albumId);
                
                if (album != null) {
                    albumList.add(album);
                }
            }
            System.out.println("DAO: " + albumList);

            // postId를 기준으로 오름차순으로 정렬
            albumList.sort(Comparator.comparing(Album::getAlbumId));

            return albumList;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }

    
 // user가 가입한 앨범 찾기
    public List<Album> findMyAlbum(int userId) {
        String sql = "SELECT * FROM ALBUM WHERE userId = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[]{userId});

        ResultSet rs = null; //Declare ResultSet outside try block

        try {
            rs = jdbcUtil.executeQuery();
            List<Album> albumList = new ArrayList<>();

            while (rs.next()) {
                int albumId = rs.getInt("albumId");
                Album album = new Album(
                        albumId,
                        rs.getString("albumName"),
                        rs.getString("createDate"),
                        rs.getString("creatorName"),
                        rs.getString("explanation"),
                        rs.getString("isShared"),
                        rs.getInt("userId")
                );
                System.out.println("albumId: " + albumId);
                
                if (album != null) {
                    albumList.add(album);
                }
            }
            System.out.println("DAO: " + albumList);
            return albumList;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return null;
    }
/*    public List<Album> findMyAlbum(int userId) {
        String sql = "SELECT * FROM ALBUM WHERE userId = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[]{userId});

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<Album> albumList = new ArrayList<>();

            while (rs.next()) {
                int albumId = rs.getInt("albumId");
                
                // membership의 albumId로 앨범을 찾아옴
                Album album = findAlbumById(albumId);
                
                if (album != null) {
                    albumList.add(album);
                }
            }
            return albumList;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return null;
    }
*/
    
    // 앨범 id로 조회 (위 함수에서 사용됨)
    public Album findAlbumById(int albumId) throws SQLException {
        String sql = "SELECT albumName, createDate, creatorName, explanation, isShared, userId " +
                "FROM Album " +
                "WHERE albumId=?";

        jdbcUtil.setSqlAndParameters(sql, new Object[]{albumId});
        Album album = null;
        try {
            ResultSet rs = jdbcUtil.executeQuery();

            if (rs.next()) {
                album = new Album(
                        albumId,
                        rs.getString("albumName"),
                        rs.getString("createDate"),
                        rs.getString("creatorName"),
                        rs.getString("explanation"),
                        rs.getString("isShared"),
                        rs.getInt("userId")
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return album;
    }


    // 앨범 존재여부 확인
    public boolean existingAlbum(String albumId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Album WHERE albumId=?";

        jdbcUtil.setSqlAndParameters(sql, new Object[]{albumId});

        try {
            ResultSet rs = jdbcUtil.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return (count == 1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return false;
    }
    
 // 앨범이름으로 멤버 수 확인
    public int countAlbumMember(String albumName) throws SQLException {
        int count = 0;  // 멤버 수 저장
        String sql = "SELECT COUNT(*) FROM MEMBERSHIP WHERE albumName=?";

        jdbcUtil.setSqlAndParameters(sql, new Object[]{albumName});

        try {
            ResultSet rs = jdbcUtil.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);  // ResultSet에서 멤버 수 얻기
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return count;
    }
    public static void main(String[] args) {
        AlbumDAO albumDAO = new AlbumDAO();

        // 테스트용 앨범 객체 생성
        Album testAlbum = new Album(
              0,
                "Test Album",
                "Test Date",
                "Test Creator",               
                "Test Explanation",
                "Shared",
                1 // Assuming user ID is 1 for testing
        );

        try {
            // 앨범 생성 메서드 호출
            int result = albumDAO.create(testAlbum, 1);
            System.out.print(result);
            if (result > 0) {
                System.out.println("앨범 생성 성공!");
            } else {
                System.out.println("앨범 생성 실패!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Album> resultAlbumList(String input) {
        String sql = "SELECT * FROM ALBUM WHERE albumName LIKE ? AND isShared LIKE 'True'";
        jdbcUtil.setSqlAndParameters(sql, new Object[]{("%"+input+"%")});

        ResultSet rs = null; //Declare ResultSet outside try block

        try {
            rs = jdbcUtil.executeQuery();
            List<Album> albumList = new ArrayList<>();

            while (rs.next()) {
                Album album = new Album(
                        rs.getInt("albumId"),
                        rs.getString("albumName"),
                        rs.getString("createDate"),
                        rs.getString("creatorName"),
                        rs.getString("explanation"),
                        rs.getString("isShared"),
                        rs.getInt("userId")
                );
                if (album != null) {
                    albumList.add(album);
                }
            }
            return albumList;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }
}
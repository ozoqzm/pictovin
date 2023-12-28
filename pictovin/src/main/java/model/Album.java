package model;

import java.util.List;

public class Album {
    private int albumId;
    private String albumName;
    private String createDate;
    private String creatorName;
    private String explanation;
    private String isShared; // 문자형 타입 (조심)
    private int userId;
    
    public Album() {}
    
    public Album(int albumId, String albumName, String createDate, String creatorName,
            String explanation, String isShared, int userId) {
        super();
        this.albumId = albumId;
        this.albumName = albumName;
        this.createDate = createDate;
        this.creatorName = creatorName;
        this.explanation = explanation;
        this.isShared = isShared;
        this.userId = userId;
    }
    
    public Album(String albumName,  String creatorName,
            String explanation, String isShared, int userId) {
        super();
        this.albumName = albumName;
        this.creatorName = creatorName;
        this.explanation = explanation;
        this.isShared = isShared;
        this.userId = userId;
    }
    
    
    public int getAlbumId() {
        return albumId;
    }
    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
    public String getAlbumName() {
        return albumName;
    }
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    public String getCreatorName() {
        return creatorName;
    }
    public void setCreateName(String createName) {
        this.creatorName = createName;
    }
    public String getExplanation() {
        return explanation;
    }
    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
    public String getIsShared() {
        return isShared;
    }
    public void setIsShared(String isShared) {
        this.isShared = isShared;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
}

package model;


public class Post {
    private int postId;
    private String content;
    private String privacyStatus;
    private String postDate;
    private int userId;
    private int albumId;
    
    public Post() {}
    
    public Post(int postId, String content, String privacyStatus, String postDate,
            int userId, int albumId) {
        super();
        this.postId = postId;
        this.content = content;
        this.privacyStatus = privacyStatus;
        this.postDate = postDate;
        this.userId = userId;
        this.albumId = albumId;
    }
    
    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
        this.postId = postId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getPrivacyStatus() {
        return privacyStatus;
    }
    public void setPrivacyStatus(String privacyStatus) {
        this.privacyStatus = privacyStatus;
    }
    public String getPostDate() {
        return postDate;
    }
    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getAlbumId() {
        return albumId;
    }
    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
    
    
    
    
}

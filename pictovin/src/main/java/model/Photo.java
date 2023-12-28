package model;

public class Photo {

    private String imagePath;
    private int postId; // 기본키
    private String postDate;
    
    public Photo() {}
    
    public Photo(String imagePath, int postId) {
        super();
        this.imagePath = imagePath;
        this.postId = postId;
    }
    
    // 추가
    public Photo(String imagePath, int postId, String postDate) {
        super();
        this.imagePath = imagePath;
        this.postId = postId;
        this.setPostDate(postDate);
    }
    
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
        this.postId = postId;
    }

    // 추가
	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
    
    
}
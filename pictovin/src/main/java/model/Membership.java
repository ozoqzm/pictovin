package model;

import java.util.Date;
import java.util.List;

public class Membership {
    private int membershipId;
    private int userId;
    private int albumId;
    
    public Membership() {}
    
    public Membership(int membershipId, int userId, int albumId) {
        super();
        this.membershipId = membershipId;
        this.userId = userId;
        this.albumId = albumId;
    }
    
    public int getMembershipId() {
        return membershipId;
    }
    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
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
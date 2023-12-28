package model.service;

import java.sql.SQLException;
import java.util.List;

import model.Tag;
import model.dao.TagDAO;

public class TagManager {
    private TagDAO tagDAO;

    public TagManager() {
        this.tagDAO = new TagDAO();
    }

    public static TagManager getInstance() {
        return new TagManager();
    }

    public int createTag(Tag tag) throws SQLException {
        return tagDAO.createTag(tag);
    }

    public Tag getTag(int postId) throws SQLException {
        return tagDAO.getTag(postId);
    }

    public List<Tag> getTags(int postId) throws SQLException {
        return tagDAO.getTags(postId);
    }
}

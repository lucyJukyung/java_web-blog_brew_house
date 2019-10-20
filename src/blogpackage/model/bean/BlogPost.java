package blogpackage.model.bean;

import java.util.Date;

public class BlogPost {

    protected int postID;
    protected String postTitle;
    protected Date postDate;
    protected String postAuthor;
    protected String postContent;
    protected boolean isPostVisible;
    protected int category;

    // add more constructors if you need
    public BlogPost(int postID, String postTitle, Date postDate, String postAuthor, String postContent, boolean isPostVisible, int category) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postDate = postDate;
        this.postAuthor = postAuthor;
        this.postContent = postContent;
        this.isPostVisible = isPostVisible;
        this.category = category;
    } // end of BlogPost constructor

    public BlogPost() {
    }

    // getters and setters
    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public boolean isPostVisible() {
        return isPostVisible;
    }

    public void setPostVisible(boolean postVisible) {
        isPostVisible = postVisible;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}

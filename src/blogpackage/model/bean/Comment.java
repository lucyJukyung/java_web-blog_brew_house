package blogpackage.model.bean;

public class Comment {

    protected int commentID;
    protected String commentOwner;
    protected String commentContent;
    protected int postId;

    // constructor
    public Comment() {
    }

    public Comment(String commentOwner, String commentContent, int postId) {
        this.commentOwner = commentOwner;
        this.commentContent = commentContent;
        this.postId = postId;
    }

    public Comment(int commentID, String commentOwner, String commentContent, int postId) {
        this.commentID = commentID;
        this.commentOwner = commentOwner;
        this.commentContent = commentContent;
        this.postId = postId;
    }

    // getters and setters
    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getCommentOwner() {
        return commentOwner;
    }

    public void setCommentOwner(String commentOwner) {
        this.commentOwner = commentOwner;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
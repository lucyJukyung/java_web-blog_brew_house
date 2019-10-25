package blogpackage.model.bean;

public class BlogPost {
    private int postID;
    private String postTitle;
    private String postDate;
    private String postAuthor;
    private String postContent;
    private boolean isPostVisable;
    private String postSummary;

    //added categoryId and category Title by Lucy
    private int categoryId;
    private String categoryTitle;

    public BlogPost(){

    }

    //constructor for selectAllPosts in PostDAO WITH SUMMARY
    public BlogPost(int postID, String postTitle, String postDate, String postAuthor, String postContent, boolean isPostVisable, int categoryId, String categoryTitle,
                    String postSummary) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postDate = postDate;
        this.postAuthor = postAuthor;
        this.postContent = postContent;
        this.isPostVisable = isPostVisable;
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.postSummary = postSummary;
    }

    public BlogPost(int postID, String postTitle, String postDate, String postAuthor, String postContent, boolean isPostVisable, int categoryId, String categoryTitle) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postDate = postDate;
        this.postAuthor = postAuthor;
        this.postContent = postContent;
        this.isPostVisable = isPostVisable;
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
    }

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
    /*public int getPostCategory() {
        return postCategory;
    }
    public void setPostCategory(int postCategory) {
        this.postCategory = postCategory;
    }*/
    public String getPostDate() {
        return postDate;
    }
    public void setPostDate(String postDate) {
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
    public boolean isPostVisable() {
        return isPostVisable;
    }
    public void setPostVisable(boolean isPostVisable) {
        this.isPostVisable = isPostVisable;
    }

    //Added categoryId getter and setter to display in showPosts.jsp
    public  int getCategoryId(){
        return categoryId;
    }
    public void setCategoryId(int categoryId){
        this.categoryId = categoryId;
    }
    //Added categoryTitle getter and setter
    public String getCategoryTitle(){
        return  categoryTitle;
    }
    public void setCategoryTitle(String categoryTitle){
        this.categoryTitle = categoryTitle;
    }

    public String getPostSummary() {
        return postSummary;
    }

    public void setPostSummary(String postSummary) {
        this.postSummary = postSummary;
    }

}

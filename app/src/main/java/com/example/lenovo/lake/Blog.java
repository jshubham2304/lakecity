package com.example.lenovo.lake;

public class Blog {
    private String postTitle,postDesc,postImage,profileImage,userName;

    public Blog() {
    }

    public Blog(String postTitle, String postDesc, String postImage,String profileImage,String userName) {
        this.postTitle = postTitle;
        this.postDesc = postDesc;
        this.postImage = postImage;
        this.profileImage = profileImage;
        this.userName = userName;


    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDesc() {
        return postDesc;
    }

    public void setPostDesc(String postDesc) {
        this.postDesc = postDesc;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }
}

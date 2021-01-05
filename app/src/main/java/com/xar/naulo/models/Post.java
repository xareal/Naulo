package com.xar.naulo;


public class Post {

    private int mTitleId;
    private String mUrlId;


    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;


    // Create a new Post object with image being not available
    public Post(int titleId, String urlId) {
        mTitleId = titleId;
        mUrlId = urlId;
    }


    //  Create a new Post object with image being available
    public Post(int titleId, String urlId, int imageResourceId) {
        mTitleId = titleId;
        mUrlId = urlId;
        mImageResourceId = imageResourceId;
    }

    // Return resource ids' of post
    public int getTitleId() {
        return mTitleId;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    // Return and set url
    public String getUrlId() {
        return mUrlId;
    }
    public void setUrl(String url) { this.mUrlId = url; }

    // Return whether or not there is image for this post
    public boolean hasImage() { return mImageResourceId != NO_IMAGE_PROVIDED; }

}

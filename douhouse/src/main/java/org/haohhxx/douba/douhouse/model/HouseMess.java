package org.haohhxx.douba.douhouse.model;

/**
 * @author zhenyuan_hao@163.com
 */
public class HouseMess {

    private String title;
    private String replyNub;
    private String lastReply;
    private String author;

    private String placeStr;
    private String longitude;
    private String latitude;

    private String id;

    public String getPlaceStr() {
        return placeStr;
    }

    public void setPlaceStr(String placeStr) {
        this.placeStr = placeStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReplyNub() {
        return replyNub;
    }

    public void setReplyNub(String replyNub) {
        this.replyNub = replyNub;
    }

    public String getLastReply() {
        return lastReply;
    }

    public void setLastReply(String lastReply) {
        this.lastReply = lastReply;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

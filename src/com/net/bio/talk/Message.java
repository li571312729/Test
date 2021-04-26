package com.net.bio.talk;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = -790609627181027194L;
    private String userName;
    private String targetName;
    private String content;
    private int type; //消息类型
    private Date createTime;
    
    public Message(){};

    public Message(String userName, String content, Date createTime, String targetName, int type){
        this.targetName = targetName;
        this.userName = userName;
        this.content = content;
        this.createTime = createTime;
        this.type = type;
    };
    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }
    
}

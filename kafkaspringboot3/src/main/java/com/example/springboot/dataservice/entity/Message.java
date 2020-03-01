package com.example.springboot.dataservice.entity;

public class Message {

    private String senderName;
    private String senderEMail;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Message(String senderName, String senderEMail, String receiverName, String receiverEMail, String messageContent, long timeinMilliSeconds) {
        this.senderName = senderName;
        this.senderEMail = senderEMail;
        this.receiverName = receiverName;
        this.receiverEMail = receiverEMail;
        this.messageContent = messageContent;
        this.timeinMilliSeconds = timeinMilliSeconds;
    }

    @Override
    public String toString() {
        return "Message{" +
                "senderName='" + senderName + '\'' +
                ", senderEMail='" + senderEMail + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverEMail='" + receiverEMail + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", timeinMilliSeconds=" + timeinMilliSeconds +
                '}';
    }

    public String getSenderEMail() {
        return senderEMail;
    }

    public void setSenderEMail(String senderEMail) {
        this.senderEMail = senderEMail;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverEMail() {
        return receiverEMail;
    }

    public void setReceiverEMail(String receiverEMail) {
        this.receiverEMail = receiverEMail;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    private String receiverName;
    private String receiverEMail;
    private String messageContent;

    public long getTimeinMilliSeconds() {
        return timeinMilliSeconds;
    }

    public void setTimeinMilliSeconds(long timeinMilliSeconds) {
        this.timeinMilliSeconds = timeinMilliSeconds;
    }

    private long timeinMilliSeconds;
}

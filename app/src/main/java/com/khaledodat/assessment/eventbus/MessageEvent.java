package com.khaledodat.assessment.eventbus;

import com.khaledodat.assessment.view.base.BaseFragment;

public class MessageEvent {

    public MessageEvent(Object data, Class sentTo, MessageType messageType) {
        this.data = data;
        this.sentTo = sentTo;
        this.messageType = messageType;
    }

    public MessageEvent() {
    }

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Class sentTo;

    public Class getSentTo() {
        return sentTo;
    }

    public void setSentTo(Class sentTo) {
        this.sentTo = sentTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public BaseFragment getToFragment() {
        return toFragment;
    }

    public void setToFragment(BaseFragment toFragment) {
        this.toFragment = toFragment;
    }

    private BaseFragment toFragment;

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    private MessageType messageType;

    public enum MessageType {
        NAVIGATION,
        SEND_DATA,
        CLIP_ITEM,
        UNCLIP_ITEM,
        ACTIVE_DIRECTORY_RESULTS,
        ACTION_BAR_HIDE_SHOW,
        RONOCK_ASSISTANT_TOGGLE,
        REFRESH_TOKEN,
        SHOW_HIDE_PROGRESS,
        SHOW_HIDE_NO_INTERNET,
        UPDATE_AD_TYPE_TAB,
        SEND_FILTER,
        CATEGORY_SELECTED
    }
}
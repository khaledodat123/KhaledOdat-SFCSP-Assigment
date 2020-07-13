package com.khaledodat.assessment.view.callbacks;

public interface ResponseListener<T> {

    void onSuccess(T data);
    void onFailure(Throwable t);
    void onAction(String message);
}

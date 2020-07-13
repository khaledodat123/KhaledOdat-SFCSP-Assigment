package com.khaledodat.assessment.data.remote.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListDataResponse<T> {
    @SerializedName("continuationToken")
    private String mContinuationToken;
    @SerializedName("items")
    private List<T> mItems;

    public String getContinuationToken() {
        return mContinuationToken;
    }

    public void setContinuationToken(String continuationToken) {
        mContinuationToken = continuationToken;
    }

    public List<T> getItems() {
        return mItems;
    }

    public void setItems(List<T> items) {
        mItems = items;
    }
}

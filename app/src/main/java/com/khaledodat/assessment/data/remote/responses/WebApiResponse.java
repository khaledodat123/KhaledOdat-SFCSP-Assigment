
package com.khaledodat.assessment.data.remote.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class WebApiResponse<T> {

    @SerializedName("code")
    private long mCode;
    @SerializedName("data")
    private T mData;
    @SerializedName("errors")
    private List<Error> mErrors;
    @SerializedName("message")
    private String mMessage;

    public long getCode() {
        return mCode;
    }

    public void setCode(long code) {
        mCode = code;
    }

    public T getData() {
        return mData;
    }

    public void setData(T data) {
        mData = data;
    }

    public List<Error> getErrors() {
        return mErrors;
    }

    public void setErrors(List<Error> errors) {
        mErrors = errors;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}

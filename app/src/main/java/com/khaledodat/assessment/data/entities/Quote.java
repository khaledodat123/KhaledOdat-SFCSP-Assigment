
package com.khaledodat.assessment.data.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Quote extends BaseEntity{

    @PrimaryKey
    private int localID = 1;
    @SerializedName("_id")
    private String quotID;
    @SerializedName("author")
    private String mAuthor;
    @SerializedName("en")
    private String mEn;
    @SerializedName("id")
    private String mId;

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getEn() {
        return mEn;
    }

    public void setEn(String en) {
        mEn = en;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getQuotID() {
        return quotID;
    }

    public void setQuotID(String quotID) {
        this.quotID = quotID;
    }

    public int getLocalID() {
        return localID;
    }

    public void setLocalID(int localID) {
        this.localID = localID;
    }
}

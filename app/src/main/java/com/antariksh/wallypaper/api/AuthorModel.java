package com.antariksh.wallypaper.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ahmed Talaat on 09-Apr-16.
 */
public class AuthorModel implements Parcelable {
    private String userName;
    private String url;

    /**
     * @return The userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName The userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.url);
    }

    public AuthorModel() {
    }

    protected AuthorModel(Parcel in) {
        this.userName = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<AuthorModel> CREATOR = new Parcelable.Creator<AuthorModel>() {
        @Override
        public AuthorModel createFromParcel(Parcel source) {
            return new AuthorModel(source);
        }

        @Override
        public AuthorModel[] newArray(int size) {
            return new AuthorModel[size];
        }
    };
}

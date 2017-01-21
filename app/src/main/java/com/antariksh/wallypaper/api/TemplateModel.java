package com.antariksh.wallypaper.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ahmed Talaat on 09-Apr-16.
 */
public class TemplateModel implements Parcelable {
    private String title;
    private String url;
    private AuthorModel author;

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
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

    /**
     * @return The author
     */
    public AuthorModel getAuthor() {
        return author;
    }

    /**
     * @param author The author
     */
    public void setAuthor(AuthorModel author) {
        this.author = author;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeParcelable(this.author, flags);
    }

    public TemplateModel() {
    }

    protected TemplateModel(Parcel in) {
        this.title = in.readString();
        this.url = in.readString();
        this.author = in.readParcelable(AuthorModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<TemplateModel> CREATOR = new Parcelable.Creator<TemplateModel>() {
        @Override
        public TemplateModel createFromParcel(Parcel source) {
            return new TemplateModel(source);
        }

        @Override
        public TemplateModel[] newArray(int size) {
            return new TemplateModel[size];
        }
    };
}

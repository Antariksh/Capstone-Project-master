package com.antariksh.wallypaper.api;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.antariksh.wallypaper.data.WallypaperProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed Talaat on 09-Apr-16.
 */
public class PatternModel implements Parcelable {

    private Integer id;
    private String title;
    private String userName;
    private Integer numViews;
    private Integer numVotes;
    private Integer numComments;
    private Double numHearts;
    private Integer rank;
    private String dateCreated;
    private List<String> colors = new ArrayList<String>();
    private String description;
    private String url;
    private String imageUrl;
    private String badgeUrl;
    private String apiUrl;
    private TemplateModel template;

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

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
     * @return The numViews
     */
    public Integer getNumViews() {
        return numViews;
    }

    /**
     * @param numViews The numViews
     */
    public void setNumViews(Integer numViews) {
        this.numViews = numViews;
    }

    /**
     * @return The numVotes
     */
    public Integer getNumVotes() {
        return numVotes;
    }

    /**
     * @param numVotes The numVotes
     */
    public void setNumVotes(Integer numVotes) {
        this.numVotes = numVotes;
    }

    /**
     * @return The numComments
     */
    public Integer getNumComments() {
        return numComments;
    }

    /**
     * @param numComments The numComments
     */
    public void setNumComments(Integer numComments) {
        this.numComments = numComments;
    }

    /**
     * @return The numHearts
     */
    public Double getNumHearts() {
        return numHearts;
    }

    /**
     * @param numHearts The numHearts
     */
    public void setNumHearts(Double numHearts) {
        this.numHearts = numHearts;
    }

    /**
     * @return The rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     * @param rank The rank
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    /**
     * @return The dateCreated
     */
    public String getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated The dateCreated
     */
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return The colors
     */
    public List<String> getColors() {
        return colors;
    }

    /**
     * @param colors The colors
     */
    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return The imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl The imageUrl
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @return The badgeUrl
     */
    public String getBadgeUrl() {
        return badgeUrl;
    }

    /**
     * @param badgeUrl The badgeUrl
     */
    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    /**
     * @return The apiUrl
     */
    public String getApiUrl() {
        return apiUrl;
    }

    /**
     * @param apiUrl The apiUrl
     */
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    /**
     * @return The template
     */
    public TemplateModel getTemplate() {
        return template;
    }

    /**
     * @param template The template
     */
    public void setTemplate(TemplateModel template) {
        this.template = template;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeString(this.userName);
        dest.writeValue(this.numViews);
        dest.writeValue(this.numVotes);
        dest.writeValue(this.numComments);
        dest.writeValue(this.numHearts);
        dest.writeValue(this.rank);
        dest.writeString(this.dateCreated);
        dest.writeStringList(this.colors);
        dest.writeString(this.description);
        dest.writeString(this.url);
        dest.writeString(this.imageUrl);
        dest.writeString(this.badgeUrl);
        dest.writeString(this.apiUrl);
        dest.writeParcelable(this.template, flags);
    }

    public PatternModel() {
    }

    protected PatternModel(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.userName = in.readString();
        this.numViews = (Integer) in.readValue(Integer.class.getClassLoader());
        this.numVotes = (Integer) in.readValue(Integer.class.getClassLoader());
        this.numComments = (Integer) in.readValue(Integer.class.getClassLoader());
        this.numHearts = (Double) in.readValue(Double.class.getClassLoader());
        this.rank = (Integer) in.readValue(Integer.class.getClassLoader());
        this.dateCreated = in.readString();
        this.colors = in.createStringArrayList();
        this.description = in.readString();
        this.url = in.readString();
        this.imageUrl = in.readString();
        this.badgeUrl = in.readString();
        this.apiUrl = in.readString();
        this.template = in.readParcelable(TemplateModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<PatternModel> CREATOR = new Parcelable.Creator<PatternModel>() {
        @Override
        public PatternModel createFromParcel(Parcel source) {
            return new PatternModel(source);
        }

        @Override
        public PatternModel[] newArray(int size) {
            return new PatternModel[size];
        }
    };

    public static PatternModel fromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(WallypaperProvider.PatternColumns.PATTERN_ID));
        String title = cursor.getString(cursor.getColumnIndex(WallypaperProvider.PatternColumns.TITLE));
        String imageUrl = cursor.getString(cursor.getColumnIndex(WallypaperProvider.PatternColumns.IMAGE_URL));
        String userName = cursor.getString(cursor.getColumnIndex(WallypaperProvider.PatternColumns.USER_NAME));
        int likes = cursor.getInt(cursor.getColumnIndex(WallypaperProvider.PatternColumns.LIKES));
        int views = cursor.getInt(cursor.getColumnIndex(WallypaperProvider.PatternColumns.VIEWS));
        String apiUrl = cursor.getString(cursor.getColumnIndex(WallypaperProvider.PatternColumns.API_URL));

        return new Builder()
                .id(id)
                .imageUrl(imageUrl)
                .title(title)
                .userName(userName)
                .likes(likes)
                .views(views)
                .apiUrl(apiUrl)
                .build();
    }

    public static class Builder {
        private PatternModel mPatternModel;

        public Builder() {
            mPatternModel = new PatternModel();
        }

        public Builder id(int id) {
            mPatternModel.setId(id);
            return this;
        }

        public Builder title(String title) {
            mPatternModel.setTitle(title);
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            mPatternModel.setImageUrl(imageUrl);
            return this;
        }

        public Builder userName(String userName) {
            mPatternModel.setUserName(userName);
            return this;
        }

        public Builder likes(int likes) {
            mPatternModel.setNumVotes(likes);
            return this;
        }

        public Builder views(int views) {
            mPatternModel.setNumViews(views);
            return this;
        }

        public Builder apiUrl(String apiUrl) {
            mPatternModel.setApiUrl(apiUrl);
            return this;
        }

        public PatternModel build() {
            return mPatternModel;
        }
    }

}

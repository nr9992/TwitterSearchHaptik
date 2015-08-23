package com.neelraj.twittersearchhaptik.Model;

import com.google.gson.annotations.SerializedName;
import com.neelraj.twittersearchhaptik.List.Searches;

/**
 * Created by Neel Raj on 23-08-2015.
 */
public class SearchResults {

    @SerializedName("statuses")
    private Searches statuses;

    @SerializedName("search_metadata")
    private SearchMetadata metadata;


    public Searches getStatuses() {
        return statuses;
    }

    public void setStatuses(Searches statuses) {
        this.statuses = statuses;
    }

    public SearchMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(SearchMetadata metadata) {
        this.metadata = metadata;
    }
}


package com.example.week3day4.GithubRepository;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repository implements Parcelable
{

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    public final static Creator<Repository> CREATOR = new Creator<Repository>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        public Repository[] newArray(int size) {
            return (new Repository[size]);
        }

    }
    ;

    protected Repository(Parcel in) {
        in.readList(this.results, (com.example.week3day4.GithubRepository.Result.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Repository() {
    }

    /**
     * 
     * @param results
     */
    public Repository(List<Result> results) {
        super();
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(results);
    }

    public int describeContents() {
        return  0;
    }

}

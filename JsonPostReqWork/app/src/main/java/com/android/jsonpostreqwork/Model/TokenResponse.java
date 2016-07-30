
package com.android.jsonpostreqwork.Model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class TokenResponse {

    @SerializedName("places")
    @Expose
    private List<Place> places = new ArrayList<Place>();

    /**
     * 
     * @return
     *     The places
     */
    public List<Place> getPlaces() {
        return places;
    }

    /**
     * 
     * @param places
     *     The places
     */
    public void setPlaces(List<Place> places) {
        this.places = places;
    }

}

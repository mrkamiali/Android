package com.android.jsonpostreqwork.Model;

/**
 * Created by Kamran ALi on 7/31/2016.
 */
public class TokenRequest {
    private int id;
    private int city_id;
    private String tourist_guide_key_mob;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getTourist_guide_key_mob() {
        return tourist_guide_key_mob;
    }

    public void setTourist_guide_key_mob(String tourist_guide_key_mob) {
        this.tourist_guide_key_mob = tourist_guide_key_mob;
    }
}

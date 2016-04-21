package com.android.extendablelist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kamran ALi on 3/3/2016.
 */
public class VersionList {
    public static HashMap<String, List<Double>> getInfo() {
        HashMap<String, List<Double>> android_details = new HashMap<String, List<Double>>();

        List<Double> jellybean;
        List<Double> kitKat;
        List<Double> lolipop;
        List<Double> marshMallow;

        jellybean = new ArrayList<Double>();
        jellybean.add(4.1);
        jellybean.add(4.2);
        jellybean.add(4.3);
        kitKat = new ArrayList<Double>();
        kitKat.add(4.4);
        kitKat.add(4.6);
        kitKat.add(4.7);
        kitKat.add(4.8);
        kitKat.add(4.9);
        lolipop = new ArrayList<Double>();
        lolipop.add(5.0);
        lolipop.add(5.1);
        lolipop.add(5.2);
        lolipop.add(5.3);
        lolipop.add(5.4);
        lolipop.add(5.5);
        lolipop.add(5.6);
        lolipop.add(5.7);
        lolipop.add(5.8);
        lolipop.add(5.9);
        marshMallow = new ArrayList<Double>();
        marshMallow.add(6.0);

        android_details.put("JellyBean", jellybean);
        android_details.put("KitKat", kitKat);
        android_details.put("Lolipop", lolipop);
        android_details.put("Marshmallow", marshMallow);


        return android_details;
    }

    ;
}

package com.bfs.onboard.constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Gender {

    public final static String MALE = "Male";
    public final static String FEMALE = "Female";
    public final static String NA = "I don't want to answer";

    private final static Set<String> acceptGender = new HashSet<>(Arrays.asList(MALE, FEMALE, NA));

    public static boolean accept(String gender) {
        return acceptGender.contains(gender);
    }


}

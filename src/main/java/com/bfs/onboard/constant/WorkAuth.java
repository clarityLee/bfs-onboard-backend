package com.bfs.onboard.constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WorkAuth {
    public final static String H1B = "H1-B";
    public final static String L2 = "L2";
    public final static String F1 = "F1(CPT/OPT)";
    public final static String H4 = "H4";
    public final static String OTHER = "Other";

    private final static Set<String> types = new HashSet<>(Arrays.asList(
            H1B, L2, F1, H4, OTHER
    ));

    public static boolean contains(String term) {
        return types.contains(term);
    }
}

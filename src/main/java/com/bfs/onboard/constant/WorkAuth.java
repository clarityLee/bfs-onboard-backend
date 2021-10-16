package com.bfs.onboard.constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WorkAuth {
    public final static String H1B = "H1-B";
    public final static String L2 = "L2";
    public final static String F1 = "F1(CTP/OPT)";
    public final static String H4 = "H4";
    public final static String OTHER = "Other";

    private final static Set<String> types = new HashSet<>(Arrays.asList(
            "H1-B",
            "L2",
            "F1(CTP/OPT)",
            "H4",
            "Other"
    ));

    public static boolean contains(String term) {
        return types.contains(term);
    }
}

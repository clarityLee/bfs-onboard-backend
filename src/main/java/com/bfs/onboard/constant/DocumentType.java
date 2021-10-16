package com.bfs.onboard.constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class DocumentType {
    public final static String WORK_AUTHORIZATION = "Work Authorization";
    public final static String DRIVING_LICENSE = "Driving License";
//    public final static String OPT_RECEIPT = "OPT Receipt";
//    public final static String OPT_EAD = "OPT EAD";
//    public final static String I_983 = "I-983";
//    public final static String I_20 = "I-20";
//    public final static String OPT_STEM_RECEIPT = "OPT STEM Receipt";
//    public final static String OPT_STEM_EAD = "OPT STEM EAD";

    private final static Set<String> types = new HashSet<>(Arrays.asList(
            "Work Authorization",
            "Driving License",
            "OPT Receipt",
            "OPT EAD",
            "I-983",
            "I-20",
            "OPT STEM Receipt",
            "OPT STEM EAD"
    ));

    public static boolean contains(String term) {
        return types.contains(term);
    }
}

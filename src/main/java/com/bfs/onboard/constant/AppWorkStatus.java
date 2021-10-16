package com.bfs.onboard.constant;

import java.util.HashMap;
import java.util.Map;

public class AppWorkStatus {
    public final static Integer STATUS_OPEN = 0;
    public final static Integer STATUS_PENDING = 1;
    public final static Integer STATUS_REJECTED = 2;
    public final static Integer STATUS_COMPLETED = 3;
    public final static String TYPE_ONBOARDING = "Onboarding";
    public final static String TYPE_OPT = "OPT";

    private static Map<Integer, String> mapping;
    static {
        mapping = new HashMap<>();
        mapping.put(0, "OPEN");
        mapping.put(1, "PENDING");
        mapping.put(2, "REJECTED");
        mapping.put(3, "COMPLETED");
    }

    public static String statusToString(Integer status) {
        return mapping.get(status);
    }
}

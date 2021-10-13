package com.bfs.onboard.util;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TokenGenerator {

    private final static String REGISTRATION_FORMAT = "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx";
    private final Random random = new Random();

    public String registration() {
        StringBuilder sb = new StringBuilder();
        for (char c : REGISTRATION_FORMAT.toCharArray()) {
            if (c == 'x') {
                int rnd = random.nextInt(36);
                if (rnd < 10)
                    sb.append(rnd);
                else
                    sb.append((char) ('a' + rnd - 10));
            } else {
                sb.append('-');
            }
        }
        return sb.toString();
    }

}

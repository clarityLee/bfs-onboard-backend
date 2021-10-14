package com.bfs.onboard.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UploadResponse {

    private Boolean success = false;
    private String errorMessage;
    private String path;
}

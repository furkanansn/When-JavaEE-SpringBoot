package com.Hobedtech.when.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
public class PushNotificationRequest {
    private String subject;
    private String content;
    private Map<String, String> data;
    private String image;
}

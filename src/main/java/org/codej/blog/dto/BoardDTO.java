package org.codej.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {

    private Long id;
    private String title;
    private String content;
    private int viewCount;
    private String username;
    private String userEmail;
    private int replyCount;
    private Timestamp creteDate;

}

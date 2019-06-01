package com.kpi.project.second.service.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Document(collection = "ITEM_COMMENT")
public class ItemComment {
    @Id
    private String commentId;
    @NotBlank
    @Size(max = 2000)
    private String bodyText;
    private String postTime;
    private String login;
    private String imageFilepath;
    private String authorLogin;
    private String itemId;

}

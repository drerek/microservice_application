package com.kpi.project.second.service.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Document(collection = "ITEM")
@NoArgsConstructor
public class Item {

    @Id
    private String itemId;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @Size(max = 1023)
    private String description;

    private String imageFilepath;

    private String link;

    private String dueDate;

    List<String> tags;

    @NotNull
    private String ownerLogin;

    private ItemPriority priority;

}

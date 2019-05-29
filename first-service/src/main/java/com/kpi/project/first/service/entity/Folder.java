package com.kpi.project.first.service.entity;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Folder {

    private int folderId;

    @NotNull
    @Size(min = 1, max = 100)
    private String name;
    private int userId;

    private List<Event> events;
}

package com.kpi.project.first.service.service.vm;

import com.kpi.project.first.service.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatCheckEntity {
    private Role role;
    private int chatTypeId;
}

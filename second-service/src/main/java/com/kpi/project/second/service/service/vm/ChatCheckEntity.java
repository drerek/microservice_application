package com.kpi.project.second.service.service.vm;

import com.kpi.project.second.service.entity.Role;
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

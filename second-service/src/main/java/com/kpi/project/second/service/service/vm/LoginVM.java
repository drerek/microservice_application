package com.kpi.project.second.service.service.vm;

import com.kpi.project.second.service.entity.User;
import com.kpi.project.second.service.rest.controller.AccountController;
import lombok.Data;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginVM {
    @NotBlank
    @Size(min = 4, max = 50)
    private String login;

    @Size(min = 6, max = 50)
    private String password;
}

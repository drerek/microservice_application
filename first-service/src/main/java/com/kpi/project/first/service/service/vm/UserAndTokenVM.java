package com.kpi.project.first.service.service.vm;

import com.kpi.project.first.service.entity.User;
import com.kpi.project.first.service.rest.controller.AccountController;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletResponse;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserAndTokenVM extends User {
    private String token;

    public UserAndTokenVM(User user) {
        this.setId(user.getId());
        this.setLogin(user.getLogin());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
        this.setName(user.getName());
        this.setLastname(user.getLastname());
        this.setPhone(user.getPhone());
        this.setBirthDay(user.getBirthDay());
        this.setTimeZone(user.getTimeZone());
        this.setImgPath(user.getImgPath());
    }
}

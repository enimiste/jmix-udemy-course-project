package com.company.projectmanagement.security;

import com.company.projectmanagement.entity.Task;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name = "Restricted Task Role", code = RestrictedTaskRole.CODE)
public interface RestrictedTaskRole {
    String CODE = "restricted-task-role";

    @JpqlRowLevelPolicy(entityClass = Task.class,
            where = "{E}.assignee.username=:current_user_username or {E}.project.manager.username=:current_user_username")
    void task();
}
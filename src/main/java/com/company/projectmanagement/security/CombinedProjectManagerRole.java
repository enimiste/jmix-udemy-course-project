package com.company.projectmanagement.security;

import io.jmix.security.role.annotation.ResourceRole;

@ResourceRole(name = "Combined Project Manager", code = CombinedProjectManagerRole.CODE, scope = "UI")
public interface CombinedProjectManagerRole extends ProjectManagerRole, UiMinimalRole {
    String CODE = "combined-project-manager";
}
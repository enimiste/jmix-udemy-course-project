package com.company.projectmanagement.security;

import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name = "Combined Restricted Project Role", code = CombinedRestrictedProjectRole.CODE)
public interface CombinedRestrictedProjectRole extends RestrictedProjectsRole, RestrictedTimeEntryRole {
    String CODE = "combined-restricted-project-role";
}
package com.company.projectmanagement.security;

import com.company.projectmanagement.entity.Project;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.security.model.RowLevelBiPredicate;
import io.jmix.security.role.annotation.PredicateRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;

import static io.jmix.security.model.RowLevelPolicyAction.*;

@RowLevelRole(name = "Restricted Projects Role", code = RestrictedProjectsRole.CODE)
public interface RestrictedProjectsRole {
    String CODE = "restricted-projects-role";

    /**
     * This policy checks that the connected user :
     * <ul>
     *     <li>Can only update/delete projects which are managed by him.</li>
     *     <li>Can only create a new project where the he is the manager.</li>
     *     <li>Can't change the manager of a project.</li>
     * </ul>
     * @return
     */
    @PredicateRowLevelPolicy(entityClass = Project.class, actions = {CREATE, UPDATE, DELETE})
    default RowLevelBiPredicate<Project, ApplicationContext> projectPredicate() {
        return (project, applicationContext) -> {
            UserDetails user = applicationContext.getBean(CurrentAuthentication.class).getUser();
            return user.equals(project.getManager());
        };
    }
}
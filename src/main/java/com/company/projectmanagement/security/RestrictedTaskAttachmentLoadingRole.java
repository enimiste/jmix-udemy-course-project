package com.company.projectmanagement.security;

import com.company.projectmanagement.entity.Task;
import com.company.projectmanagement.entity.User;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.security.model.RowLevelBiPredicate;
import io.jmix.security.model.RowLevelPolicyAction;
import io.jmix.security.role.annotation.PredicateRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;
import org.springframework.context.ApplicationContext;

@RowLevelRole(name = "Restricted Task Attachment Loading Role", code = RestrictedTaskAttachmentLoadingRole.CODE)
public interface RestrictedTaskAttachmentLoadingRole {
    String CODE = "restricted-task-attachment-loading-role";

    @PredicateRowLevelPolicy(entityClass = Task.class, actions = {RowLevelPolicyAction.CREATE, RowLevelPolicyAction.UPDATE})
    default RowLevelBiPredicate<Task, ApplicationContext> taskPredicate() {
        return (task, applicationContext) -> {
            User user = (User) applicationContext.getBean(CurrentAuthentication.class).getUser();
            return task.getAttachment()==null || user.equals(task.getAssignee());
        };
    }
}
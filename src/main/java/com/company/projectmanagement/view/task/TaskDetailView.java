package com.company.projectmanagement.view.task;

import com.company.projectmanagement.entity.Task;
import com.company.projectmanagement.entity.TaskPriority;
import com.company.projectmanagement.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "tasks/:id", layout = MainView.class)
@ViewController("Task_.detail")
@ViewDescriptor("task-detail-view.xml")
@EditedEntityContainer("taskDc")
public class TaskDetailView extends StandardDetailView<Task> {
    @Subscribe
    public void onInitEntity(final InitEntityEvent<Task> event) {
        Task entity = event.getEntity();
        entity.setPriority(TaskPriority.MEDUIM);
    }
}
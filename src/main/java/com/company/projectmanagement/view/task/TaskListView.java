package com.company.projectmanagement.view.task;

import com.company.projectmanagement.entity.Task;
import com.company.projectmanagement.view.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.component.grid.DataGrid;
import io.jmix.flowui.download.Downloader;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "tasks", layout = MainView.class)
@ViewController("Task_.list")
@ViewDescriptor("task-list-view.xml")
@LookupComponent("tasksDataGrid")
@DialogMode(width = "64em")
public class TaskListView extends StandardListView<Task> {
    @ViewComponent
    private DataGrid<Task> tasksDataGrid;
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private Downloader downloader;

    @Subscribe
    public void onInit(final InitEvent event) {
        tasksDataGrid.addComponentColumn(task -> {
            Button button = uiComponents.create(Button.class);
            button.setIcon(VaadinIcon.DOWNLOAD.create());
            button.setThemeName("tertiary-inline");
            button.addClickListener(e -> downloader.download(task.getAttachment()));
            button.setEnabled(task.getAttachment() != null);
            return button;
        });
    }

}
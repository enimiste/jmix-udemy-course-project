package com.company.projectmanagement.app;

import com.company.projectmanagement.entity.Project;
import com.company.projectmanagement.entity.ProjectStats;
import com.company.projectmanagement.entity.Task;
import io.jmix.core.DataManager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProjectStatsService {

    private final DataManager dataManager;

    public ProjectStatsService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<ProjectStats> fetchProjectsStatistics() {
        List<Project> projects = dataManager.load(Project.class)
                .all()
                .list();
        return projects.stream()
                .map(project -> {
                    ProjectStats stat = dataManager.create(ProjectStats.class);
                    stat.setProjectName(project.getName());
                    stat.setId(project.getId());
                    stat.setTasksCount(project.getTasks().size());
                    stat.setPlannedEfforts(project.getTasks().stream()
                            .filter(task -> task.getEstimation() != null)
                            .mapToInt(Task::getEstimation)
                            .sum());
                    stat.setActualEfforts(getActualEffortsByProject(project.getId()));
                    return stat;
                }).toList();
    }

    public Integer getActualEffortsByProject(UUID projectID) {
        return dataManager.loadValue("select SUM(te.timeSpent) from TimeEntry te " +
                        "where te.task.project.id = :projectId", Integer.class)
                .parameter("projectId", projectID)
                .optional()
                .orElse(0);
    }
}
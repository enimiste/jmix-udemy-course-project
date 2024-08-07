package com.company.projectmanagement.entity;

import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JmixEntity
public class ProjectStats {
    @JmixId
    private UUID id;

    @JmixProperty(mandatory = true)
    @NotNull
    private String projectName;

    private Integer tasksCount;

    private Integer plannedEfforts;

    private Integer actualEfforts;

    public void setTasksCount(Integer tasksCount) {
        this.tasksCount = tasksCount;
    }

    public Integer getTasksCount() {
        return tasksCount;
    }

    public Integer getActualEfforts() {
        return actualEfforts;
    }

    public void setActualEfforts(Integer actualEfforts) {
        this.actualEfforts = actualEfforts;
    }

    public Integer getPlannedEfforts() {
        return plannedEfforts;
    }

    public void setPlannedEfforts(Integer plannedEfforts) {
        this.plannedEfforts = plannedEfforts;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
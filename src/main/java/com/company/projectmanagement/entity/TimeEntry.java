package com.company.projectmanagement.entity;

import io.jmix.core.MetadataTools;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;
import java.util.UUID;

@JmixEntity
@Table(name = "TIME_ENTRY", indexes = {
        @Index(name = "IDX_TIME_ENTRY_TASK", columnList = "TASK_ID"),
        @Index(name = "IDX_TIME_ENTRY_USER", columnList = "USER_ID")
})
@Entity
public class TimeEntry {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "TASK_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Task task;

    @PositiveOrZero(message = "{msg://com.company.projectmanagement.entity/TimeEntry.timeSpent.validation.PositiveOrZero}")
    @Column(name = "TIME_SPENT", nullable = false)
    @NotNull
    private Integer timeSpent;

    @NotNull
    @Column(name = "ENTRY_DATE", nullable = false)
    private LocalDateTime entryDate;

    @JoinColumn(name = "USER_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @Lob
    @Column(name = "DESCRIPTION")
    private String description;

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"description"})
    public String getInstanceName(MetadataTools metadataTools) {
        if (description == null) return null;
        return description;
    }
}
package com.company.jmixpm.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import java.util.UUID;

@JmixEntity
public class ProjectStats {

    private UUID projectId;

    private String projectName;

    private Integer taskCount;

    private Integer plnnedEfforts;

    private Integer actualEfforts;

    public Integer getActualEfforts() {
        return actualEfforts;
    }

    public void setActualEfforts(Integer actualEfforts) {
        this.actualEfforts = actualEfforts;
    }

    public Integer getPlnnedEfforts() {
        return plnnedEfforts;
    }

    public void setPlnnedEfforts(Integer plnnedEfforts) {
        this.plnnedEfforts = plnnedEfforts;
    }

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public void setProjectId(UUID projectId) {
        this.projectId = projectId;
    }
}
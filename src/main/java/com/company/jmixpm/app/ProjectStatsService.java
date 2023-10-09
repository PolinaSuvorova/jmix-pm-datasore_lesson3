package com.company.jmixpm.app;

import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.ProjectStats;
import com.company.jmixpm.entity.Task;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectStatsService {
    @Autowired
    private DataManager dataManager;

    public List<ProjectStats> fetchProjectStats() {
        List<Project> projects = dataManager.load(Project.class)
                .all()
                .fetchPlan("project-with-tasks")
                .list();
        List<ProjectStats> projectStats = projects.stream().map(project -> {
            ProjectStats prs = dataManager.create(ProjectStats.class);
            prs.setProjectId(project.getId());
            prs.setProjectName(project.getName());
            prs.setTaskCount(project.getTasks().size());
            Integer estimatedEforts = project.getTasks().stream().map(Task::getEstimatedEfforts).reduce(0, Integer::sum);
            prs.setPlnnedEfforts(estimatedEforts);
            prs.setActualEfforts(getActualEfforts(project.getId()));
            return prs;
        }).collect(Collectors.toList());

        return projectStats;
    }

    public Integer getActualEfforts(UUID uuid) {
        return dataManager.loadValue(
                        "select sum(t.timeSpent) " +
                                "from TimeEntry t " +
                                "where t.task.project.id = :projectId1 ", Integer.class)
                .parameter("projectId1", uuid)
                .one();
    }
}
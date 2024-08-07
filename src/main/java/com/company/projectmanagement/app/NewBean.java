package com.company.projectmanagement.app;

import com.company.projectmanagement.entity.Project;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.stereotype.Component;

@Component
public class NewBean {
    private final DataManager dataManager;

    public NewBean(DataManager dataManager) {
        this.dataManager = dataManager;

        System.out.println(
                dataManager.load(Project.class)
                        .condition(PropertyCondition.contains(
                                "name", "USG"
                        ))
                        .maxResults(2)
                        .fetchPlan(FetchPlan.BASE)
                        .list()
        );
    }
}
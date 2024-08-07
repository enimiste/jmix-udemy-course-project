package com.company.projectmanagement.app;

import com.company.projectmanagement.entity.Project;
import com.company.projectmanagement.entity.Task;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class NewBean {
    private final DataManager dataManager;

    public NewBean(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<Task> loadTasksByQuery(){
        return dataManager.load(Task.class)
                .query("select t from Task_ t " +
                        "where extract(month from t.dueDate) = :month " +
                        "order by t.name asc")
                .parameter("month", LocalDate.now().getMonthValue())
                .list();
    }
}
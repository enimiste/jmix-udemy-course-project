package com.company.projectmanagement.view.timeentry;

import com.company.projectmanagement.entity.TimeEntry;
import com.company.projectmanagement.entity.User;
import com.company.projectmanagement.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.TimeSource;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;

@Route(value = "time-entries/:id", layout = MainView.class)
@ViewController("TimeEntry.detail")
@ViewDescriptor("time-entry-detail-view.xml")
@EditedEntityContainer("timeEntryDc")
public class TimeEntryDetailView extends StandardDetailView<TimeEntry> {
    @Autowired
    private TimeSource timeSource;
    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Subscribe
    public void onInitEntity(final InitEntityEvent<TimeEntry> event) {
        final ZonedDateTime zonedDateTime = timeSource.now();
        TimeEntry entity = event.getEntity();
        entity.setEntryDate(zonedDateTime.toLocalDateTime());
        final User user = (User) currentAuthentication.getUser();
        entity.setUser(user);
    }

}
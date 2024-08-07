package com.company.projectmanagement.view.user;

import com.company.projectmanagement.entity.User;
import com.company.projectmanagement.view.main.MainView;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;

@Route(value = "users", layout = MainView.class)
@ViewController("User.list")
@ViewDescriptor("user-list-view.xml")
@LookupComponent("usersDataGrid")
@DialogMode(width = "64em")
public class UserListView extends StandardListView<User> {
    @Autowired
    private UiComponents uiComponents;

    @Supply(to = "usersDataGrid.avatar", subject = "renderer")
    private Renderer<User> usersDataGridAvatarRenderer() {
        return new ComponentRenderer<>(user -> {
            if (user.getAvatar() != null && user.getAvatar().length > 0) {
                Image image = uiComponents.create(Image.class);
                image.setWidth("25px");
                image.setHeight("25px");
                image.setSrc(new StreamResource("avatar", () -> new ByteArrayInputStream(user.getAvatar())));
                return image;
            }
            return new Span();
        });
    }
}
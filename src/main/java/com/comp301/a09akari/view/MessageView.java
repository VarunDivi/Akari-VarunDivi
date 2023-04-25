package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.scene.Parent;

public class MessageView implements FXComponent{

    AlternateMvcController controller;

    public MessageView(AlternateMvcController controller) {
        this.controller = controller;
    }

    @Override
    public Parent render() {
        return null;
    }
}

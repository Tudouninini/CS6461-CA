package gui;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class AlertBox {
    public static void display(String title, String message) {
        Stage window = new Stage();
        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label(message);
        Button closeButton = new Button("close me");
        closeButton.setOnAction(event -> window.close());
        StackPane layout = new StackPane();
        layout.getChildren().addAll(label, closeButton);
        Scene scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.showAndWait();

    }
}

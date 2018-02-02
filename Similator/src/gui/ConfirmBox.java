package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConfirmBox {
    private static Boolean answer;

    public static boolean display(String title, String message) {

        Stage window = new Stage();
        window.setTitle(title);
        Label label = new Label(message);
        Button yesButton = new Button("Yes");
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });
        Button noButton = new Button("No");
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, noButton, yesButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 300, 300);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }
}


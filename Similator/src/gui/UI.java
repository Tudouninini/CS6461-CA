package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class UI extends Application {
    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = new Stage();
        window.setTitle("This is the window title");
//        window.setOnCloseRequest(e -> {
//            e.consume();
//            closeProgram();
//        });
//        Button button = new Button("button1");
//        button.setOnAction(e -> {
////            AlertBox.display("Title", "ERROR");
//            boolean result = ComfirmBox.display("Confirm Box", "Are you human?");
//            System.out.println(result);
//        });
        Button closeButton = new Button("Close and save");
        closeButton.setOnAction(e -> closeProgram());
        StackPane layout = new StackPane();
        layout.getChildren().addAll(closeButton);
        Scene scene = new Scene(layout, 400, 500);
        window.setScene(scene);
        window.show();
    }

    private void closeProgram() {
        //The process before close program
        Boolean result = ConfirmBox.display("Title", "Are you sure to exit");
        System.out.println(result);
        if (result) window.close();
    }
}

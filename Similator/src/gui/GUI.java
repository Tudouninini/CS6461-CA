package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.*;
import javafx.stage.Stage;

import util.Transform;


public class GUI extends Application {
    private GridPane centerGrid;
    private Button resetBt;
    private RegisterEntity pcEntity;
    private RegisterEntity ccEntity;
    private RegisterEntity marEntity;
    private RegisterEntity mbrEntity;
    private RegisterEntity msrEntity;
    private RegisterEntity mfrEntity;
    private RegisterEntity x1Entity;
    private RegisterEntity x2Entity;
    private RegisterEntity x3Entity;
    private RegisterEntity r0Entity;
    private RegisterEntity r1Entity;
    private RegisterEntity r2Entity;
    private RegisterEntity r3Entity;
//    private RegisterEntity cc;

    public static void main(String[] args) {
        launch(args);
    }


    private void depositToDisplay(RegisterEntity register) {
        register.getDepositButton().setOnAction(e -> {
            String binaryStr = getBinaryStrByDeposit(register);
            setRatioButtonsByBinaryStr(register, binaryStr);
        });
    }

    public void resetRegister(RegisterEntity entity) {

        setRatioButtonsByBinaryStr(entity, "0000000000000000");
        entity.getTextField().clear();

    }

    public void resetAllRegister() {
        setRatioButtonsByBinaryStr(pcEntity, "0000000000000000");
        setRatioButtonsByBinaryStr(ccEntity, "0000000000000000");
        setRatioButtonsByBinaryStr(marEntity, "0000000000000000");
        setRatioButtonsByBinaryStr(mbrEntity, "0000000000000000");
        setRatioButtonsByBinaryStr(msrEntity, "0000000000000000");
        setRatioButtonsByBinaryStr(mfrEntity, "0000000000000000");
        setRatioButtonsByBinaryStr(x1Entity, "0000000000000000");
        setRatioButtonsByBinaryStr(x2Entity, "0000000000000000");
        setRatioButtonsByBinaryStr(x3Entity, "0000000000000000");
        setRatioButtonsByBinaryStr(r0Entity, "0000000000000000");
        setRatioButtonsByBinaryStr(r1Entity, "0000000000000000");
        setRatioButtonsByBinaryStr(r2Entity, "0000000000000000");
        setRatioButtonsByBinaryStr(r3Entity, "0000000000000000");
        pcEntity.getTextField().clear();
        ccEntity.getTextField().clear();
        marEntity.getTextField().clear();
        mbrEntity.getTextField().clear();
        msrEntity.getTextField().clear();
        mfrEntity.getTextField().clear();
        x1Entity.getTextField().clear();
        x2Entity.getTextField().clear();
        x3Entity.getTextField().clear();
        r0Entity.getTextField().clear();
        r1Entity.getTextField().clear();
        r2Entity.getTextField().clear();
        r3Entity.getTextField().clear();
    }


    private String getBinaryStrByDeposit(RegisterEntity register) {
        try {
            long temp = Integer.parseInt(register.getTextField().getText());
            if (temp > 0xffff) resetRegister(register);
            else {
                int value = (int) temp;
                return Transform.intToBinaryString(value);
            }

        } catch (NumberFormatException e) {
            System.out.println("Must deposit a integer");
        }
        return "0000000000000000";
    }

    private void setRatioButtonsByBinaryStr(RegisterEntity register, String binaryStr) {
        int i = binaryStr.length();
        for (int j = 0; j < i; j++) {
            char temp = binaryStr.charAt(j);
            if (temp == '1') {
                register.getRblist().get(j).setSelected(true);
            } else {
                if (register.getRblist().get(j).isSelected()) {
                    register.getRblist().get(j).setSelected(false);
                }
            }
        }

    }


    public void positionEntityInLayout(RegisterEntity entity, int rowIndex) {
        GridPane.setConstraints(entity.getLabel(), 0, rowIndex);
        for (int i = 0; i < 16; i++) {
            GridPane.setConstraints(entity.getRblist().get(i), i + 1, rowIndex);
        }
        GridPane.setConstraints(entity.getTextField(), 17, rowIndex);
        GridPane.setConstraints(entity.getDepositButton(), 18, rowIndex);
    }

    public void addEntityIntoLayout(RegisterEntity entity) {
        centerGrid.getChildren().add(entity.getLabel());
        for (int i = 0; i < 16; i++) {
            centerGrid.getChildren().add(entity.getRblist().get(i));
        }
        centerGrid.getChildren().add(entity.getTextField());
        centerGrid.getChildren().add(entity.getDepositButton());
    }


    @Override

    public void start(Stage window) throws Exception {
        window.setTitle("CPU SIMULATOR FOR CS6461");
        String[] rigisterLabels = {"PC", "CC", "MAR", "MBR", "MSR", "MFR", "X1", "X2", "X3", "R0", "R1", "R2", "R3"};

        pcEntity = new RegisterEntity("PC");
        ccEntity = new RegisterEntity("CC");
        marEntity = new RegisterEntity("MAR");
        mbrEntity = new RegisterEntity("MBR");
        msrEntity = new RegisterEntity("MSR");
        mfrEntity = new RegisterEntity("MFR");
        x1Entity = new RegisterEntity("X1");
        x2Entity = new RegisterEntity("X2");
        x3Entity = new RegisterEntity("X3");
        r0Entity = new RegisterEntity("R0");
        r1Entity = new RegisterEntity("R1");
        r2Entity = new RegisterEntity("R2");
        r3Entity = new RegisterEntity("R3");


        depositToDisplay(pcEntity);
        depositToDisplay(ccEntity);
        depositToDisplay(marEntity);
        depositToDisplay(mbrEntity);
        depositToDisplay(msrEntity);
        depositToDisplay(mfrEntity);
        depositToDisplay(x1Entity);
        depositToDisplay(x2Entity);
        depositToDisplay(x3Entity);
        depositToDisplay(r0Entity);
        depositToDisplay(r1Entity);
        depositToDisplay(r2Entity);
        depositToDisplay(r3Entity);


        BorderPane borderPane = new BorderPane();
        Label bottom = new Label("bottom");
        Label top = new Label("top");
        resetBt = new Button("Reset");
        resetBt.setOnAction(e -> resetAllRegister());
        Label left = new Label("left");
        Label right = new Label("right");
        HBox topMenu = new HBox();
        HBox bottomMenu = new HBox();
        VBox leftMenu = new VBox();
        VBox rightMenu = new VBox();
        centerGrid = new GridPane();
        centerGrid.setPadding(new Insets(50, 80, 40, 70));
        centerGrid.setVgap(8);
        centerGrid.setHgap(9);
        //put the label first

        positionEntityInLayout(pcEntity, 0);
        positionEntityInLayout(ccEntity, 1);
        positionEntityInLayout(marEntity, 2);
        positionEntityInLayout(mbrEntity, 3);
        positionEntityInLayout(msrEntity, 4);
        positionEntityInLayout(mfrEntity, 5);
        positionEntityInLayout(x1Entity, 6);
        positionEntityInLayout(x2Entity, 7);
        positionEntityInLayout(x3Entity, 8);
        positionEntityInLayout(r0Entity, 9);
        positionEntityInLayout(r1Entity, 10);
        positionEntityInLayout(r2Entity, 11);
        positionEntityInLayout(r3Entity, 12);


        addEntityIntoLayout(pcEntity);
        addEntityIntoLayout(ccEntity);
        addEntityIntoLayout(marEntity);
        addEntityIntoLayout(mbrEntity);
        addEntityIntoLayout(msrEntity);
        addEntityIntoLayout(mfrEntity);
        addEntityIntoLayout(x1Entity);
        addEntityIntoLayout(x2Entity);
        addEntityIntoLayout(x3Entity);
        addEntityIntoLayout(r0Entity);
        addEntityIntoLayout(r1Entity);
        addEntityIntoLayout(r2Entity);
        addEntityIntoLayout(r3Entity);


//        centerGrid.getChildren().addAll(ccEntity, ir, mar, mbr, msr, mfr, x1, x2, x3, r0, r1, r2, r3);
        topMenu.getChildren().addAll(top, resetBt);
        bottomMenu.getChildren().add(bottom);
        leftMenu.getChildren().add(left);
        rightMenu.getChildren().add(right);
        borderPane.setTop(topMenu);
        borderPane.setCenter(centerGrid);
        borderPane.setLeft(leftMenu);
        borderPane.setRight(rightMenu);
        borderPane.setBottom(bottomMenu);
        Scene scene = new Scene(borderPane, 1217, 726);
        window.setScene(scene);
        window.show();
    }
    //    public void start(Stage primaryStage) throws Exception {
//        window = primaryStage;
//        window.setTitle("CPU SIMULATOR By Group 9");
//        //Scene 1 :
//        Label label1 = new Label("This is a label");
//        button1 = new Button("RUN");
//        button1.setOnAction(event -> {
//                    window.setScene(scene2);
//                }
//        );
//        StackPane layout1 = new StackPane();
//        layout1.getChildren().addAll(button1, label1);
//        scene1 = new Scene(layout1, 500, 300);
//
//        //scene 2 :
//        Label label2 = new Label("This is the another label");
//        button2 = new Button("QUIT and switch to scene2");
//        button2.setOnAction(e -> {
//            window.setScene(scene1);
//        });
//        StackPane layout2 = new StackPane();
//        layout2.getChildren().addAll(label2, button2);
//
//        scene2 = new Scene(layout2, 100, 100);
//
//
//        window.setScene(scene1);
//        window.show();
//    }

}

package gui;

import cpu.Registers;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import memory.MemoryManageUnit;
import util.Transform;


public class GUI extends Application {
    private HBox topMenu;
    private HBox topLeftMenu;
    private HBox topRightMenu;
    private GridPane centerGrid;
    private HBox bottomMenu;
    private VBox bottomLeftMenu;
    private VBox bottomMiddleMenu;
    private VBox bottomRightMenu;
    private VBox leftMenu;
    private VBox rightMenu;
    private GridPane rightBottomMenu;
    private Button btIPL;
    private Button btRun;
    private Button btSingleStep;
    private Button btTemp3;
    private Button btTemp4;
    private Button btLoad;
    private Button btStore;
    private Label addr;
    private Label valu;
    private TextField inputAddress;
    private TextField inputValue;
    private TableView<MemoryEntity> memoryTable;
    private TextArea instructionLog;
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
    private Registers rs;
    private MemoryManageUnit mmunit;


    //    private RegisterEntity cc;
    public void resetAllRegister() {
        setRatioButtonsByBinaryStr(pcEntity, Transform.intToBinaryString(rs.getPc()));
        setRatioButtonsByBinaryStr(ccEntity, Transform.intToBinaryString(rs.getCc()));
        setRatioButtonsByBinaryStr(marEntity, Transform.intToBinaryString(rs.getMar()));
        setRatioButtonsByBinaryStr(mbrEntity, Transform.intToBinaryString(rs.getMbr()));
        setRatioButtonsByBinaryStr(msrEntity, Transform.intToBinaryString(rs.getMsr()));
        setRatioButtonsByBinaryStr(mfrEntity, Transform.intToBinaryString(rs.getMfr()));
        setRatioButtonsByBinaryStr(x1Entity, Transform.intToBinaryString(rs.getX1()));
        setRatioButtonsByBinaryStr(x2Entity, Transform.intToBinaryString(rs.getX2()));
        setRatioButtonsByBinaryStr(x3Entity, Transform.intToBinaryString(rs.getX3()));
        setRatioButtonsByBinaryStr(r0Entity, Transform.intToBinaryString(rs.getR0()));
        setRatioButtonsByBinaryStr(r1Entity, Transform.intToBinaryString(rs.getR1()));
        setRatioButtonsByBinaryStr(r2Entity, Transform.intToBinaryString(rs.getR2()));
        setRatioButtonsByBinaryStr(r3Entity, Transform.intToBinaryString(rs.getR3()));
        pcEntity.getTextField().setText(String.valueOf(rs.getPc()));
        ccEntity.getTextField().setText(String.valueOf(rs.getCc()));
        marEntity.getTextField().setText(String.valueOf(rs.getMar()));
        mbrEntity.getTextField().setText(String.valueOf(rs.getMbr()));
        msrEntity.getTextField().setText(String.valueOf(rs.getMsr()));
        mfrEntity.getTextField().setText(String.valueOf(rs.getMfr()));
        x1Entity.getTextField().setText(String.valueOf(rs.getX1()));
        x2Entity.getTextField().setText(String.valueOf(rs.getX2()));
        x3Entity.getTextField().setText(String.valueOf(rs.getX3()));
        r0Entity.getTextField().setText(String.valueOf(rs.getR0()));
        r1Entity.getTextField().setText(String.valueOf(rs.getR1()));
        r2Entity.getTextField().setText(String.valueOf(rs.getR2()));
        r3Entity.getTextField().setText(String.valueOf(rs.getR3()));
        pcEntity.getTextField().setDisable(false);
        ccEntity.getTextField().setDisable(false);
        marEntity.getTextField().setDisable(false);
        mbrEntity.getTextField().setDisable(false);
        msrEntity.getTextField().setDisable(false);
        mfrEntity.getTextField().setDisable(false);
        x1Entity.getTextField().setDisable(false);
        x2Entity.getTextField().setDisable(false);
        x3Entity.getTextField().setDisable(false);
        r0Entity.getTextField().setDisable(false);
        r1Entity.getTextField().setDisable(false);
        r2Entity.getTextField().setDisable(false);
        r3Entity.getTextField().setDisable(false);
        pcEntity.getTextField().setDisable(false);
        ccEntity.getTextField().setDisable(false);
        pcEntity.getDepositButton().setDisable(false);
        ccEntity.getDepositButton().setDisable(false);
        marEntity.getDepositButton().setDisable(false);
        mbrEntity.getDepositButton().setDisable(false);
        msrEntity.getDepositButton().setDisable(false);
        mfrEntity.getDepositButton().setDisable(false);
        x1Entity.getDepositButton().setDisable(false);
        x2Entity.getDepositButton().setDisable(false);
        x3Entity.getDepositButton().setDisable(false);
        r0Entity.getDepositButton().setDisable(false);
        r1Entity.getDepositButton().setDisable(false);
        r2Entity.getDepositButton().setDisable(false);
        r3Entity.getDepositButton().setDisable(false);
        btRun.setDisable(false);
        btSingleStep.setDisable(false);
        btTemp3.setDisable(false);
        btTemp4.setDisable(false);
    }

    public void resetRegister(RegisterEntity entity) {

        setRatioButtonsByBinaryStr(entity, "0000000000000000");
        entity.getTextField().clear();

    }

    private void depositToDisplay(RegisterEntity register) {
        register.getDepositButton().setOnAction(e -> {
            String currentRs = register.getLabel().getText().toLowerCase();
            switch (currentRs) {
                case "mar":
                    rs.setMar(Integer.parseInt(register.getTextField().getText()));
            }
            System.out.println("----" + rs.getMar());
            String binaryStr = getBinaryStrByDeposit(register);
            setRatioButtonsByBinaryStr(register, binaryStr);
        });
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

    public void addEntityToCenterGrid(RegisterEntity entity) {
        centerGrid.getChildren().add(entity.getLabel());
        for (int i = 0; i < 16; i++) {
            centerGrid.getChildren().add(entity.getRblist().get(i));
        }
        centerGrid.getChildren().add(entity.getTextField());
        centerGrid.getChildren().add(entity.getDepositButton());
    }

    public void initializeRegisterEntities() {
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


        addEntityToCenterGrid(pcEntity);
        addEntityToCenterGrid(ccEntity);
        addEntityToCenterGrid(marEntity);
        addEntityToCenterGrid(mbrEntity);
        addEntityToCenterGrid(msrEntity);
        addEntityToCenterGrid(mfrEntity);
        addEntityToCenterGrid(x1Entity);
        addEntityToCenterGrid(x2Entity);
        addEntityToCenterGrid(x3Entity);
        addEntityToCenterGrid(r0Entity);
        addEntityToCenterGrid(r1Entity);
        addEntityToCenterGrid(r2Entity);
        addEntityToCenterGrid(r3Entity);
    }

    public void initializeGeneralButtons() {
        btIPL = new Button("IPL");
        btRun = new Button("Run");
        btLoad = new Button("Load");
        btStore = new Button("Store");
        btRun.setDisable(true);
        btSingleStep = new Button("SS");
        btSingleStep.setDisable(true);
        btTemp3 = new Button("b3");
        btTemp3.setDisable(true);
        btTemp4 = new Button("b4");
        btTemp4.setDisable(true);
        btRun.setPrefWidth(60);
        btIPL.setPrefWidth(60);
        btLoad.setPrefWidth(60);
        btLoad.setPrefWidth(60);
        btSingleStep.setPrefWidth(60);
        btTemp3.setPrefWidth(60);
        btTemp4.setPrefWidth(60);
        btIPL.setOnAction(e -> resetAllRegister());
    }

    public ObservableList<MemoryEntity> getMemoryList() {
        ObservableList<MemoryEntity> memoryList = FXCollections.observableArrayList();
        for (int i = 0; i < 2048; i++) {
            MemoryEntity memory = new MemoryEntity(i, mmunit.getMemory().get(i));
            memoryList.add(memory);
        }
        return memoryList;
    }

    public void initializeMemoryTable() {
        //name column
        TableColumn<MemoryEntity, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setPrefWidth(123);
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        TableColumn<MemoryEntity, String> contentColumn = new TableColumn<>("Content");
        contentColumn.setPrefWidth(123);
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        //noinspection unchecked'

        memoryTable = new TableView<>();
        memoryTable.setItems(getMemoryList());
        //noinspection unchecked
        memoryTable.getColumns().addAll(addressColumn, contentColumn);

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override

    public void start(Stage window) throws Exception {
        rs = new Registers();
        mmunit = new MemoryManageUnit();
        window.setTitle("CPU SIMULATOR FOR CS6461");
        String[] rigisterLabels = {"PC", "CC", "MAR", "MBR", "MSR", "MFR", "X1", "X2", "X3", "R0", "R1", "R2", "R3"};
        BorderPane borderPane = new BorderPane();
        Label bottom = new Label("bottom");


        Label left = new Label("left");

        instructionLog = new TextArea();
        instructionLog.setMaxHeight(90);
        instructionLog.setMaxWidth(290);
        instructionLog.setScrollLeft(5);


        topMenu = new HBox();
        topLeftMenu = new HBox();
        topRightMenu = new HBox();
        leftMenu = new VBox();
        rightMenu = new VBox();
        rightBottomMenu = new GridPane();
        bottomMenu = new HBox();


        centerGrid = new GridPane();
        centerGrid.setPadding(new Insets(8, 0, 70, 40));
        centerGrid.setVgap(8);
        centerGrid.setHgap(8);
        bottomLeftMenu = new VBox();
        bottomRightMenu = new VBox();
        bottomMiddleMenu = new VBox(instructionLog);

        rightMenu.setPadding(new Insets(10, 40, 20, 20));
        bottomMenu.setPadding(new Insets(-60));
        bottomMiddleMenu.setPadding(new Insets(60));
        topLeftMenu.setAlignment(Pos.CENTER_LEFT);
        topLeftMenu.setPadding(new Insets(30, 90, 10, 110));
        topLeftMenu.setSpacing(43);
        topRightMenu.setSpacing(15);

        initializeRegisterEntities();
        initializeGeneralButtons();
        initializeMemoryTable();
        initializeMemoOpLayout();
        topRightMenu.setAlignment(Pos.CENTER_RIGHT);
        topRightMenu.setPadding(new Insets(30, 20, 0, 290));

        rightMenu.getChildren().addAll(memoryTable, rightBottomMenu);/**/

        topLeftMenu.getChildren().addAll(btIPL, btRun, btSingleStep, btTemp3, btTemp4);
        bottomMenu.getChildren().addAll(bottomLeftMenu, bottomMiddleMenu, bottomRightMenu);
        topMenu.getChildren().add(topLeftMenu);
        topMenu.getChildren().add(topRightMenu);
        bottomMenu.getChildren().add(bottom);
        leftMenu.getChildren().add(left);


        borderPane.setPadding(new Insets(20, 60, 50, 60));
        borderPane.setTop(topMenu);
        borderPane.setCenter(centerGrid);
        borderPane.setBottom(bottomMenu);
        borderPane.setLeft(leftMenu);
        borderPane.setRight(rightMenu);

        Scene scene = new Scene(borderPane, 1217, 726);
        scene.getStylesheets().add("gui/style.css");
        window.setScene(scene);
        window.show();
    }

    private void initializeMemoOpLayout() {
        addr = new Label("Address");
        valu = new Label("Value");
        inputAddress = new TextField();
        inputAddress.setPrefWidth(75);
        inputValue = new TextField();
        inputValue.setPrefWidth(75);
        rightBottomMenu.setHgap(30);
        rightBottomMenu.setAlignment(Pos.CENTER);
        GridPane.setConstraints(addr, 0, 0);
        GridPane.setConstraints(inputAddress, 1, 0);
        GridPane.setConstraints(valu, 0, 1);
        GridPane.setConstraints(inputValue, 1, 1);
        GridPane.setConstraints(btLoad, 0, 2);
        GridPane.setConstraints(btStore, 1, 2);

        btLoad.setOnAction(e -> {
            //todo
            System.out.println();
        });
        btStore.setOnAction(e -> {
            //todo
        });
        rightBottomMenu.getChildren().addAll(addr, valu, inputAddress, inputValue, btLoad, btStore);
    }

}

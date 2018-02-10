package gui;

import cpu.Registers;
import instructions.*;
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
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import memory.MemoryManageUnit;
import util.Transform;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


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
    private Button btReset;
    private Button btSinIns;
    private Button btLoadFile;
    private Button btLoad;
    private Button btStore;
    private Label addr;
    private Label valu;
    private TextField inputAddress;
    private TextField inputValue;
    private TableView<MemoryEntity> memoryTable;
    private TextArea userConsole;
    private RegisterEntity pcEntity;
    private RegisterEntity irEntity;
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
    private Map<String, Instruction> instructionMap;
    private int controler;
    private LDR ldr;
    private HLT hlt;
    private STR str;
    private LDX ldx;
    private STX stx;
    private File instrucFile;

    public void initInsMap() {

        instructionMap = new HashMap<>();
        ldr = new LDR();
        hlt = new HLT();
        str = new STR();
        ldx = new LDX();
        stx = new STX();
        instructionMap.put("000000", hlt);
        instructionMap.put("000001", ldr);
        instructionMap.put("000002", str);
        instructionMap.put("101001", ldx);
        instructionMap.put("101010", stx);

    }

    //    private RegisterEntity cc;
    public void preToStart(boolean temp) {
        setRatioButtonsByBinaryStr(pcEntity, Transform.intToBinaryString(rs.getPc()));
        setRatioButtonsByBinaryStr(irEntity, Transform.intToBinaryString(rs.getIr()));
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
        irEntity.getTextField().setText(String.valueOf(rs.getIr()));
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
        pcEntity.getTextField().setDisable(temp);
        irEntity.getTextField().setDisable(temp);
        ccEntity.getTextField().setDisable(temp);
        marEntity.getTextField().setDisable(temp);
        mbrEntity.getTextField().setDisable(temp);
        msrEntity.getTextField().setDisable(temp);
        mfrEntity.getTextField().setDisable(temp);
        x1Entity.getTextField().setDisable(temp);
        x2Entity.getTextField().setDisable(temp);
        x3Entity.getTextField().setDisable(temp);
        r0Entity.getTextField().setDisable(temp);
        r1Entity.getTextField().setDisable(temp);
        r2Entity.getTextField().setDisable(temp);
        r3Entity.getTextField().setDisable(temp);
        pcEntity.getDepositButton().setDisable(temp);
        irEntity.getDepositButton().setDisable(temp);
        ccEntity.getDepositButton().setDisable(temp);
        marEntity.getDepositButton().setDisable(temp);
        mbrEntity.getDepositButton().setDisable(temp);
        msrEntity.getDepositButton().setDisable(temp);
        mfrEntity.getDepositButton().setDisable(temp);
        x1Entity.getDepositButton().setDisable(temp);
        x2Entity.getDepositButton().setDisable(temp);
        x3Entity.getDepositButton().setDisable(temp);
        r0Entity.getDepositButton().setDisable(temp);
        r1Entity.getDepositButton().setDisable(temp);
        r2Entity.getDepositButton().setDisable(temp);
        r3Entity.getDepositButton().setDisable(temp);
        btRun.setDisable(temp);
        btSingleStep.setDisable(temp);
        btReset.setDisable(temp);
        btSinIns.setDisable(temp);
        btLoadFile.setDisable(temp);
        controler = 0;
    }

    public void resetRegister(RegisterEntity entity) {

        setRatioButtonsByBinaryStr(entity, "0000000000000000");
        entity.getTextField().clear();

    }

    private void pressBtDeposit(RegisterEntity register) {
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

    public void initRegisterEntities() {
        pcEntity = new RegisterEntity("PC");
        irEntity = new RegisterEntity("IR");
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


        pressBtDeposit(pcEntity);
        pressBtDeposit(irEntity);
        pressBtDeposit(ccEntity);
        pressBtDeposit(marEntity);
        pressBtDeposit(mbrEntity);
        pressBtDeposit(msrEntity);
        pressBtDeposit(mfrEntity);
        pressBtDeposit(x1Entity);
        pressBtDeposit(x2Entity);
        pressBtDeposit(x3Entity);
        pressBtDeposit(r0Entity);
        pressBtDeposit(r1Entity);
        pressBtDeposit(r2Entity);
        pressBtDeposit(r3Entity);


        positionEntityInLayout(pcEntity, 0);
        positionEntityInLayout(irEntity, 13);
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
        addEntityToCenterGrid(irEntity);
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

    public void initButtons() {
        btIPL = new Button("IPL");
        btRun = new Button("Run");
        btLoad = new Button("Load");
        btStore = new Button("Store");
        btRun.setDisable(true);
        btSingleStep = new Button("SS1");
        btSingleStep.setDisable(true);
        btReset = new Button("Reset");
        btReset.setDisable(true);
        btSinIns = new Button("SS2");
        btSinIns.setDisable(true);
        btSinIns.setOnAction(e -> {
            try {
                exeSingleChange();

            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        Font font12 = new Font(12.6);
        btLoadFile = new Button("Load File");
        btLoadFile.setFont(font12);
        btLoadFile.setDisable(true);
        btLoadFile.setPrefWidth(90);
        btLoadFile.setPrefHeight(28);
        btLoadFile.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle(" Open Instruction File");
            instrucFile = fileChooser.showOpenDialog(new Stage());
            loadROM(6, instrucFile);
        });
        btRun.setPrefWidth(60);
        btIPL.setPrefWidth(60);
        btLoad.setPrefWidth(60);
        btLoad.setPrefWidth(60);
        btSingleStep.setPrefWidth(60);
        btSingleStep.setOnAction(e -> {
            try {
                exeBySingleStep();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        btReset.setPrefWidth(80);
        btReset.setOnAction(e -> {
            this.rs = new Registers();
            this.mmunit = new MemoryManageUnit();
            initButtons();
            initRegisterEntities();
            initMemoryTable();
            initializeMemoOpLayout();
            memoryTable.getItems().clear();
            preToStart(true);
            userConsole.clear();
        });
        btSinIns.setPrefWidth(70);
        btIPL.setOnAction(e -> {
            preToStart(false);

        });
        btRun.setOnAction(e -> {

            try {
                exeAllIns();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        });
    }

    public ObservableList<MemoryEntity> getMemoryList() {
        ObservableList<MemoryEntity> memoryList = FXCollections.observableArrayList();
        for (int i = 0; i < 2048; i++) {
            MemoryEntity memory = new MemoryEntity(i, mmunit.getMemory().get(i));
            memoryList.add(memory);
        }
        return memoryList;
    }

    public void clearMemoryTable() {

    }

    public void initMemoryTable() {
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

    public void refreshAllRegister() {
        pcEntity.getTextField().setText(String.valueOf(rs.getPc()));
        setRatioButtonsByBinaryStr(pcEntity, Transform.intToBinaryString(rs.getPc()));
        irEntity.getTextField().setText(String.valueOf(rs.getIr()));
        setRatioButtonsByBinaryStr(irEntity, Transform.intToBinaryString(rs.getIr()));
        ccEntity.getTextField().setText(String.valueOf(rs.getCc()));
        setRatioButtonsByBinaryStr(ccEntity, Transform.intToBinaryString(rs.getCc()));
        marEntity.getTextField().setText(String.valueOf(rs.getMar()));
        setRatioButtonsByBinaryStr(marEntity, Transform.intToBinaryString(rs.getMar()));
        mbrEntity.getTextField().setText(String.valueOf(rs.getMbr()));
        setRatioButtonsByBinaryStr(mbrEntity, Transform.intToBinaryString(rs.getMbr()));
        msrEntity.getTextField().setText(String.valueOf(rs.getMsr()));
        setRatioButtonsByBinaryStr(msrEntity, Transform.intToBinaryString(rs.getMsr()));
        x1Entity.getTextField().setText(String.valueOf(rs.getX1()));
        setRatioButtonsByBinaryStr(x1Entity, Transform.intToBinaryString(rs.getX1()));
        x2Entity.getTextField().setText(String.valueOf(rs.getX2()));
        setRatioButtonsByBinaryStr(x2Entity, Transform.intToBinaryString(rs.getX2()));
        x3Entity.getTextField().setText(String.valueOf(rs.getX3()));
        setRatioButtonsByBinaryStr(x3Entity, Transform.intToBinaryString(rs.getX3()));
        r0Entity.getTextField().setText(String.valueOf(rs.getR0()));
        setRatioButtonsByBinaryStr(r0Entity, Transform.intToBinaryString(rs.getR0()));
        r1Entity.getTextField().setText(String.valueOf(rs.getR1()));
        setRatioButtonsByBinaryStr(r1Entity, Transform.intToBinaryString(rs.getR2()));
        r2Entity.getTextField().setText(String.valueOf(rs.getR2()));
        setRatioButtonsByBinaryStr(r2Entity, Transform.intToBinaryString(rs.getR2()));
        r3Entity.getTextField().setText(String.valueOf(rs.getR3()));
        setRatioButtonsByBinaryStr(r3Entity, Transform.intToBinaryString(rs.getR3()));

    }

    public void exeAllIns() throws InterruptedException {
        boolean stop = false;
        while (!stop) {
            rs.setIr(mmunit.readMemo(rs.getPc()));
            if (rs.getIr() == 0) {
                stop = true;
            }
            String insBinaryString = Transform.intToBinaryString(rs.getIr());
            Instruction ins = instructionMap.get(insBinaryString.substring(0, 6));
            ins.execute(insBinaryString, rs, mmunit);
            userConsole.appendText(ins.getExecuteInfo() + '\n');
            refreshAllRegister();

        }


    }

    public void exeBySingleStep() throws InterruptedException {

        rs.setIr(mmunit.readMemo(rs.getPc()));
        String insBinaryString = Transform.intToBinaryString(rs.getIr());
        Instruction ins = instructionMap.get(insBinaryString.substring(0, 6));
        ins.execute(insBinaryString, rs, mmunit);
        userConsole.appendText(ins.getExecuteInfo() + '\n');
        refreshAllRegister();
    }

    public void exeSingleChange() throws InterruptedException {
        rs.setMar(rs.getPc());
        rs.setMbr(mmunit.readMemo(rs.getMar()));
        rs.setIr(rs.getMbr());
        String insBinaryString = Transform.intToBinaryString(rs.getIr());
        Instruction ins = instructionMap.get(insBinaryString.substring(0, 6));
        controler = ins.executeSingleStep(insBinaryString, rs, mmunit, controler);
        //userConsole.appendText(ins.getExecuteInfo() + '\n');
        refreshAllRegister();
    }

    public void loadROM(int startAddress, File file)

    {

        if (startAddress > mmunit.getMemory().size() - 1 || startAddress < 0) {

            System.out.println("Wrong address information");

        } else if (startAddress > 0xffff) {

            System.out.println("Wrong value information");

        } else {


            BufferedReader reader = null;

            try {

                reader = new BufferedReader(new FileReader(file));
                String tempString;
                int content;
                while ((tempString = reader.readLine()) != null && startAddress < 0xffff) {

                    content = Integer.parseInt(tempString);

                    mmunit.writeMemo(startAddress, content);

                    memoryTable.getItems().get(startAddress).setContent(content);

                    memoryTable.refresh();
                    startAddress++;
                }

                reader.close();

            } catch (IOException e) {

                System.out.println("Please select file");
            } finally {

                if (reader != null) {

                    try {

                        reader.close();
                    } catch (IOException e1) {
                    }
                }
            }


        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void showFileChoose() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("打开文件");
        fileChooser.showOpenDialog(stage);
        stage.setTitle("文件选择FileChooser");
//        stage.setScene(new Scene());
        stage.show();
    }

    @Override

    public void start(Stage window) throws Exception {
        initInsMap();
        mmunit = new MemoryManageUnit();
        rs = new Registers();

        window.setTitle("CPU SIMULATOR FOR CS6461");
        String[] rigisterLabels = {"PC", "CC", "MAR", "MBR", "MSR", "MFR", "X1", "X2", "X3", "R0", "R1", "R2", "R3"};
        BorderPane borderPane = new BorderPane();
        Label bottom = new Label("bottom");


        Label left = new Label("left");

        userConsole = new TextArea();
        userConsole.disabledProperty();
        userConsole.setMaxHeight(90);
        userConsole.setMaxWidth(290);
        userConsole.setScrollLeft(5);


        topMenu = new HBox();
        topLeftMenu = new HBox();
        topRightMenu = new HBox();
        leftMenu = new VBox();
        rightMenu = new VBox();
        rightBottomMenu = new GridPane();
        bottomMenu = new HBox();


        centerGrid = new GridPane();
        centerGrid.setPadding(new Insets(8, 0, 70, 40));
        centerGrid.setVgap(5);
        centerGrid.setHgap(8);
        bottomLeftMenu = new VBox();
        bottomRightMenu = new VBox();
        bottomMiddleMenu = new VBox(userConsole);

        rightMenu.setPadding(new Insets(10, 40, 20, 20));
        topRightMenu.setAlignment(Pos.CENTER_RIGHT);
        topRightMenu.setPadding(new Insets(30, 20, 0, 290));
        bottomMenu.setPadding(new Insets(-66));
        bottomMiddleMenu.setPadding(new Insets(60));
        topLeftMenu.setAlignment(Pos.CENTER_LEFT);
        topLeftMenu.setPadding(new Insets(30, 90, 10, 110));
        topLeftMenu.setSpacing(43);
        topRightMenu.setSpacing(15);

        initRegisterEntities();
        initButtons();
        initMemoryTable();
        initializeMemoOpLayout();


        rightMenu.getChildren().addAll(memoryTable, rightBottomMenu);/**/

        topLeftMenu.getChildren().addAll(btIPL, btRun, btSingleStep, btSinIns, btReset, btLoadFile);
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
        rightBottomMenu.setPadding(new Insets(20, 0, 0, 0));
        rightBottomMenu.setHgap(25);
        rightBottomMenu.setVgap(10);
        rightBottomMenu.setAlignment(Pos.CENTER);
        GridPane.setConstraints(addr, 0, 0);
        GridPane.setConstraints(inputAddress, 1, 0);
        GridPane.setConstraints(valu, 0, 1);
        GridPane.setConstraints(inputValue, 1, 1);
        GridPane.setConstraints(btLoad, 0, 2);
        GridPane.setConstraints(btStore, 1, 2);

        btLoad.setOnAction(e -> {

            //todo
            int address = Integer.parseInt(inputAddress.getText());
            if (address > mmunit.getMemory().size() - 1 || address < 0) {
                System.out.println("Wrong address information");
            } else {
                int content = mmunit.readMemo(address);
                inputValue.setText(String.valueOf(content));
                memoryTable.requestFocus();
                memoryTable.getSelectionModel().clearAndSelect(address);
                memoryTable.scrollTo(address - 6);
                System.out.println();
            }

        });
        btStore.setOnAction(e -> {
            //todo
            int address = Integer.parseInt(inputAddress.getText());
            int content = Integer.parseInt(inputValue.getText());
            if (address > mmunit.getMemory().size() - 1 || address < 0) {
                System.out.println("Wrong address information");
            } else if (content > 0xffff || content < 0) {
                System.out.println("Wrong value information");
            } else {
                mmunit.writeMemo(address, content);
                inputAddress.setText(String.valueOf(address));
                inputValue.setText(String.valueOf(content));
                memoryTable.getItems().get(address).setContent(content);
                memoryTable.refresh();
                memoryTable.requestFocus();
                memoryTable.getSelectionModel().clearAndSelect(address);
                memoryTable.scrollTo(address - 6);

//                memoryTable.refresh();
                System.out.println("store memory address " + address + " with value " + content);
            }

        });
        rightBottomMenu.getChildren().addAll(addr, valu, inputAddress, inputValue, btLoad, btStore);
    }

}

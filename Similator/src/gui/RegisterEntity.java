package gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class RegisterEntity {
    private TextField textField;
    private ArrayList<RadioButton> rbList;
    private Label label;
    private Button depositButton;

    public RegisterEntity(String labelName) {
        label = new Label(labelName);
        Font font = Font.font("Monaco", FontWeight.BOLD, FontPosture.ITALIC, 13);
        label.setFont(font);
        textField = new TextField("");
        textField.setDisable(true);
        textField.setMaxWidth(60);
        textField.setMaxHeight(15);

        depositButton = new Button("Deposit");
        depositButton.setFont(new Font("Monaco", 11));
        depositButton.setDisable(true);
        rbList = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            RadioButton rb = new RadioButton("");
            rbList.add(rb);
        }

    }

    public TextField getTextField() {
        return textField;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }

    public ArrayList<RadioButton> getRblist() {
        return rbList;
    }

    public void setRblist(ArrayList<RadioButton> rblist) {
        this.rbList = rblist;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Button getDepositButton() {
        return depositButton;
    }

    public void setDepositButton(Button depositButton) {
        this.depositButton = depositButton;
    }
}

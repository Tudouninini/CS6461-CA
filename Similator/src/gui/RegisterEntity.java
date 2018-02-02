package gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class RegisterEntity {
    private TextField textField;
    private ArrayList<RadioButton> rbList;
    private Label label;
    private Button depositButton;

    public RegisterEntity(String labelName) {
        label = new Label(labelName);
        textField = new TextField("");
        textField.setMaxWidth(55);

        depositButton = new Button("Deposit");
        rbList = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            rbList.add(new RadioButton(""));
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

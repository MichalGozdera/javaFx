package controllers;

import calc.Operations;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.math.BigDecimal;


public class Controller {

    @FXML
    private Button button9;
    @FXML
    private Button button8;
    @FXML
    private Button button7;
    @FXML
    private Button button6;
    @FXML
    private Button button5;
    @FXML
    private Button button4;
    @FXML
    private Button button3;
    @FXML
    private Button button2;
    @FXML
    private Button button1;
    @FXML
    private Button button0;
    @FXML
    private Button coma;
    @FXML
    private Button add;
    @FXML
    private Button substract;
    @FXML
    private Button multiply;
    @FXML
    private Button divide;
    @FXML
    private Button sign;
    @FXML
    private Button resultButton;
    @FXML
    private Button del;
    @FXML
    private Button c;
    @FXML
    private Button factorial;
    @FXML
    private Label text;

    private StringBuilder number = new StringBuilder("");

    private String input;

    private BigDecimal fistNumber;
    private BigDecimal secondNumber;
    private BigDecimal result;
    private String calculation;

    public BigDecimal getFistNumber() {
        return fistNumber;
    }

    public void setFistNumber(BigDecimal fistNumber) {
        this.fistNumber = fistNumber;
    }

    public BigDecimal getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(BigDecimal secondNumber) {
        this.secondNumber = secondNumber;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public String getCalculation() {
        return calculation;
    }

    public void setCalculation(String calculation) {
        this.calculation = calculation;
    }

    @FXML
    void initialize() {

        EventHandler<MouseEvent> numberHandler = e -> {
            input = ((Button) e.getSource()).getText();
            if(input.equals("<-")&&number.length()>0){
                number.deleteCharAt(number.length()-1);
            }
            if((input.equals(".")&&number.indexOf(".")<0)||Operations.isNumeric(input.toString())) {
                number.append(input);
            }
            showText(number.toString());
        };

        EventHandler<MouseEvent> operationHandler = e -> {
            input = ((Button) e.getSource()).getText();
            if (number.length() > 0) {
                if (Operations.getFirstNumber() == null) {
                    Operations.setFirstNumber(new BigDecimal(number.toString()));
                } else {
                    Operations.setSecondNumber(new BigDecimal(number.toString()));
                }

                number = new StringBuilder("");
                switch (input) {
                    case "+": {
                        Operations.setOperation("add");
                        break;
                    }
                    case "-": {
                        Operations.setOperation("substract");
                        break;
                    }
                    case "*": {
                        Operations.setOperation("multiply");
                        break;
                    }
                    case "/": {
                        Operations.setOperation("divide");
                        break;
                    }
                    case "!": {
                        number.append(Operations.factorial(Operations.getFirstNumber()));
                        if(number.toString().equals("2137")){
                            showText("W chuj dużo");
                            break;
                        }
                        showText(number.toString());
                        break;
                    }
                    case "+/-": {
                        number.append(Operations.negate(Operations.getFirstNumber()));
                        showText(number.toString());
                        break;
                    }
                }
            }
        };

        EventHandler<MouseEvent> resultHandler = e -> {
            if (number.length() > 0 && Operations.getOperation()!=null) {
                if (Operations.getFirstNumber() == null) {
                    Operations.setFirstNumber(new BigDecimal(number.toString()));
                } else {
                    Operations.setSecondNumber(new BigDecimal(number.toString()));
                }
                number = new StringBuilder("");
                switch (Operations.getOperation()) {
                    case "add": {
                        number.append(Operations.add(Operations.getFirstNumber(), Operations.getSecondNumber()).toString());
                        break;
                    }
                    case "substract": {
                        number.append(Operations.substract(Operations.getFirstNumber(), Operations.getSecondNumber()).toString());
                        break;
                    }
                    case "multiply": {
                        number.append(Operations.multiply(Operations.getFirstNumber(), Operations.getSecondNumber()).toString());
                        break;
                    }
                    case "divide": {
                        if(Operations.getSecondNumber().compareTo(BigDecimal.ZERO)==0){
                            text.setText("Chuj wie");
                            return;
                        }
                        number.append(Operations.divide(Operations.getFirstNumber(), Operations.getSecondNumber()).toString());
                        break;
                    }
                    default:
                        number.append(Operations.getFirstNumber());
                }
               showText(number.toString());
            }
        };

        EventHandler<MouseEvent> calcelHandler = e -> {
            number = new StringBuilder("");
            Operations.setFirstNumber(null);
            Operations.setSecondNumber(null);
            text.setText(number.toString());
        };

        button0.addEventHandler(MouseEvent.MOUSE_CLICKED, numberHandler);
        button1.addEventHandler(MouseEvent.MOUSE_CLICKED, numberHandler);
        button2.addEventHandler(MouseEvent.MOUSE_CLICKED, numberHandler);
        button3.addEventHandler(MouseEvent.MOUSE_CLICKED, numberHandler);
        button4.addEventHandler(MouseEvent.MOUSE_CLICKED, numberHandler);
        button5.addEventHandler(MouseEvent.MOUSE_CLICKED, numberHandler);
        button6.addEventHandler(MouseEvent.MOUSE_CLICKED, numberHandler);
        button7.addEventHandler(MouseEvent.MOUSE_CLICKED, numberHandler);
        button8.addEventHandler(MouseEvent.MOUSE_CLICKED, numberHandler);
        button9.addEventHandler(MouseEvent.MOUSE_CLICKED, numberHandler);
        coma.addEventHandler(MouseEvent.MOUSE_CLICKED,numberHandler);
        del.addEventHandler(MouseEvent.MOUSE_CLICKED,numberHandler);

        c.addEventHandler(MouseEvent.MOUSE_CLICKED, calcelHandler);

        add.addEventHandler(MouseEvent.MOUSE_CLICKED, operationHandler);
        divide.addEventHandler(MouseEvent.MOUSE_CLICKED, operationHandler);
        substract.addEventHandler(MouseEvent.MOUSE_CLICKED, operationHandler);
        multiply.addEventHandler(MouseEvent.MOUSE_CLICKED, operationHandler);
        factorial.addEventHandler(MouseEvent.MOUSE_CLICKED, operationHandler);
        sign.addEventHandler(MouseEvent.MOUSE_CLICKED,operationHandler);

        resultButton.addEventHandler(MouseEvent.MOUSE_CLICKED, resultHandler);
    }

    private void showText(String displayedText){
        int fontSize=40;
        if(displayedText.length()>12)
        fontSize=25;
        text.setFont(Font.font(fontSize));
        text.setText(displayedText.toString());
    }

}

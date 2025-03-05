package javafxprojects.simplecalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculatorController {
    private double number1 = 0;
    private String operator = "";
    private StringBuilder currentinput = new StringBuilder();
    @FXML
    private Label resultLabel;

    @FXML
    private TextField outputTextfield;




    //Add a digit or a dot to the current number input
    public void appendInput(String inputfromcalculator) {
        currentinput.append(inputfromcalculator);
    }

    //Operator processor, it also processes the current input and stores or updates the result
    public void setOperator(String Operator) {
        if (currentinput.length() > 0) {
            double inputNumber = Double.parseDouble(currentinput.toString());
            if (operator.isEmpty()) {
                number1 = inputNumber;
            } else {
                number1 = calculate(number1, operator, inputNumber);
            }
            //reset input for the next number
            currentinput.setLength(0);
        }
        operator=Operator;
    }
    public double calculate(double num1,String op,double num2){
        switch(op) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                try {
                    return num1 / num2;
                } catch (ArithmeticException e) {
                    if (num2 == 0) {
                        System.out.println("Division by zero!!!!!");
                    }
                }
            default:
                return num2;
        }

    }
    //Calculate the final result when equals is pressed
    public String getResult(){
        if (currentinput.length() > 0 && !operator.isEmpty()) {
            double number2 = Double.parseDouble(currentinput.toString());
            double result = calculate(number1, operator, number2);
            // Build the formatted output (e.g., "a + b = c")
            String output = number1 + " " + operator + " " + number2 + " = " + result;
            // Update state for further calculations
            number1 = result;
            operator = "";
            currentinput.setLength(0);
            return output;
        }
        return "";

    }
    @FXML
    public void onnumberclick(ActionEvent event){
        String value=((Button) event.getSource()).getText();
        appendInput(value);
        outputTextfield.setText(currentinput.toString());
    }
    @FXML
    public void onoperatorclick(ActionEvent event){
        String op=((Button) event.getSource()).getText();
        setOperator(op);
        outputTextfield.setText((currentinput.toString())+op);

    }
    @FXML
    public void onclearbuttonclick(ActionEvent event){
        currentinput.setLength(0);
        operator="";
        number1=0;
        outputTextfield.setText("");

    }
    @FXML
    public void onequalsbuttonclick(ActionEvent event){
        try{
            outputTextfield.setText((currentinput.toString())+operator);
            String result= getResult();
            outputTextfield.setText(String.valueOf(result));
        }catch(ArithmeticException e){
            outputTextfield.setText("Error");
        }

    }
    @FXML
    public void onbackbuttonclick(ActionEvent event){
        if (!currentinput.isEmpty()) {
            currentinput.deleteCharAt(currentinput.length() - 1);
            outputTextfield.setText(String.valueOf(currentinput));
        }

    }
}


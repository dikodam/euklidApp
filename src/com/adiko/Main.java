package com.adiko;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static java.lang.Integer.parseInt;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Stage window;
    TextField inputA;
    TextField inputB;
    ToggleButton btnFullEuklid;
    Label lblOutput;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Euklid Hilfsrechner");

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        HBox inputLayer = new HBox(4);
        inputLayer.setAlignment(Pos.CENTER);

        int prefWidth = 50;

        inputA = new TextField();
        inputA.setPrefWidth(prefWidth);
        inputA.textProperty().addListener((observable, oldValue, newValue) -> {
            processInput();
        });

        inputB = new TextField();
        inputB.setPrefWidth(prefWidth);
        inputB.textProperty().addListener((observable, oldValue, newValue) -> {
            processInput();
        });

        btnFullEuklid = new ToggleButton("voll");
        btnFullEuklid.setOnAction(e -> processInput());

        inputLayer.getChildren().addAll(inputA, inputB, btnFullEuklid);

        lblOutput = new Label();

        layout.getChildren().addAll(inputLayer, lblOutput);

        StackPane root = new StackPane();
        root.getChildren().add(layout);
        window.setScene(new Scene(root, 350, 300));
        window.show();

        // default = full euklid
        btnFullEuklid.fire();
    }

    private void processInput() {
        int a;
        int b;
        int q;
        int r;

        try {
            a = parseInt(inputA.getText());
            b = parseInt(inputB.getText());

            // make sure a >= b
            if (a < b) {
                int c = a;
                a = b;
                b = c;
            }

            // compute full euklid or one euklid step
            if (btnFullEuklid.isSelected()) {
                doFullEuklid(a, b);
            } else {
                q = a / b;
                r = a % b;
                lblOutput.setText(String.format("%d = %d * %d + %d", a, q, b, r));
            }
        } catch (NumberFormatException e) {
            lblOutput.setText("ungültige Eingabe");
        }
    }

    private void doFullEuklid(int a, int b) {
        lblOutput.setText("siehe Konsole");
        int q = a / b;
        int r = a % b;
        System.out.println(String.format("%d = %d * %d + %d", a, q, b, r));
        if (r != 0) {
            doFullEuklid(b, r);
        } else {
            System.out.println();
        }
    }
}

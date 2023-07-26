package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Evaluation.EvaluateString;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Label label;
    @FXML
    private Label label1;
    @FXML
    private AnchorPane paneTitle;
    @FXML
    private Label label2;
    @FXML
    private ImageView close;
    @FXML
    private ImageView hide;
    @FXML
    private Button minusPlus;
    @FXML
    private ImageView maxButton;
    private double x, y;

    public void init(Stage stage) {
        //untuk dapat di clik
        paneTitle.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        //untuk dapat di drag
        paneTitle.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });
        //dapat diclik
        close.setOnMouseClicked(mouseEvent -> stage.close());
        hide.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
        maxButton.setOnMouseClicked(mouseEvent -> {
            if (stage.isMaximized())
                stage.setMaximized(false);
            else
                stage.setMaximized(true);
        });
    }

    public void insertNumber(String number) {
        label1.setText(label1.getText() + number);
    }

    public void mod(String mod) {
        label1.setText(label1.getText() + mod);
    }

    public void insertOperator(String operator) {
        String cek = label1.getText().substring(label1.getText().length() - 1);
        char[] cekoperator = cek.toCharArray();

        if ((cekoperator[0] >= '0' && cekoperator[0] <= '9') || cekoperator[0] == '!'
                || cekoperator[0] == ')' || cekoperator[0] == '('
        ) {
            label1.setText(label1.getText() + operator);
        } else {

        }
    }

    public void insertBukaTutup(String bukaTutup) {
        label1.setText(label1.getText() + bukaTutup);
    }

    public void clearScreen() {
        label1.setText("");
        label2.setText("");
    }

    /* jadi di method delete label 1 diambil kemudian di cek ada taidaknya angka
    jika ada maka akan dihapus satu persatu
    */
    public void delete() {
        if (!getLabel1().getText().isEmpty()) {
            //text berisi angka2 yang ada di label1
            StringBuilder text = new StringBuilder(getLabel1().getText());
            text.deleteCharAt(text.length() - 1);
            getLabel1().setText(text.toString());

        }
    }

    public Label getLabel1() {
        return label1;
    }

    public void setLabel1(String angka) {

        this.label1.setText(angka);
    }

    public void setLabel2(String angka) {
        this.label2.setText("= " + angka);
    }

    @FXML
    public void onMouseClik(MouseEvent event) {
        Button button = (Button) event.getSource();
        String buttonText = button.getText();
        switch (buttonText) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "0":
            case ".":
            case "-":
                insertNumber(buttonText);
                break;
            case "\u2013":
            case "+":
            case "*":
            case "/":
            case "^":
            case "!":
                insertOperator(buttonText);
                break;
            case "(":
            case ")":
                insertBukaTutup(buttonText);
                break;
            case "Mod":
                mod("M");
                break;
            case "CLEAR":
                clearScreen();
                break;
            case "DELETE":
                delete();
                break;
            case "=":
                double label2 = EvaluateString.evaluate(this.getLabel1().getText());
                setLabel2(String.valueOf(label2));
                setLabel1(String.valueOf(label2));
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}

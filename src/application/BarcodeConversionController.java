package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BarcodeConversionController {
	
	@FXML
	private Button goButton;
	@FXML
	private Button exitButton;
	@FXML
	private TextField inputText;
	@FXML private Label testLabel;
	
	@FXML
	public void handleGoButton() {
		String res = inputText.getText();
		if (! res.trim().equals("")) {
			//System.out.println(res);
			testLabel.setText(res);
		}
		
	}
	
	@FXML
	public void handleExitButton() {
		Platform.exit();
	}
}

package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.paint.Color;

public class BarcodeConversionController {
	
	Code128BEncoder barEncoder = new Code128BEncoder();
	
	@FXML private Button goButton;
	@FXML private Button exitButton;
	@FXML private TextField inputText;
	@FXML private Label testLabel;
	
	@FXML private ImageView testImg; //TODO delete
	
	public void updateTestImg(String inString) {
		String barcodePattern = barEncoder.convertTo128B(inString);
		char[] barcodePatternArray = barcodePattern.toCharArray();
		
		int width = 370;
		int height = 80;
		WritableImage wi = new WritableImage(width, height);
		PixelWriter pwi = wi.getPixelWriter();
		
		for (int x = 0; x < barcodePatternArray.length; x++) {
			if (barcodePatternArray[x] == '1') {
				for (int y = 0; y < height; y++) {
					pwi.setColor(x, y, Color.BLACK);
				}
			}
		}
		
		testImg.setImage(wi);
	}
	
	@FXML
	public void handleGoButton() {
		
		String userText = inputText.getText();
		
		if (! (userText.trim().equals("")) ) {
			if (! (userText.length() > 16) ) {
				updateTestImg(userText);
			} else {
				System.out.println("Input limited to 16 characters");
				// TODO change this to be a GUI effect, not console
			}
		}
		
		
	}
	
	@FXML
	public void handleInputText() {
		// if the input text is over X characters (16? 20?)
		// refuse further input
	}
	
	@FXML
	public void handleExitButton() {
		Platform.exit();
	}
}

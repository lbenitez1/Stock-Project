package application;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class View extends Application {
	//Class Variables
	//private Stage stockStage;
	private Scene strategyScene;
	private VBox mainVBox, strategyVBox, stockVBox;
	private HBox splitScreen;
	private Label chooseStrategy, blank1, blank2, blank3, title, decision;
	private ToggleGroup strategyGroup;
	private RadioButton strategyRadioOne, strategyRadioTwo, strategyRadioThree, strategyRadioFour;
	private ListView<String> stockList, userStockList;
	private ObservableList<String> stockItems, userStockItems;
	private Strategy strategy;
	private StockDataSubject stockViewer;
	private String strategyChoice, result, advice;
	@Override
	public void start(Stage primaryStage) {
		try {
			//stockStage =  primaryStage;
			//BorderPane root = new BorderPane();
			//Scene scene = new Scene(root,400,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			createAndSetVariables();											//method to instantiate all class variables
			positionBoxes();
			addElementsToScene();	
			strategyScene = new Scene(mainVBox, 800, 300);
			strategyScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Stock Market Watch");
			splitScreen.getChildren().addAll(strategyVBox, stockVBox);
			primaryStage.setScene(strategyScene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
public void createAndSetVariables(){
	splitScreen = new HBox();													//hbox for holding strategy and stock options
	mainVBox = new VBox();														//vbox for strategy options
	mainVBox.setId("mainVBox");
	strategyVBox = new VBox();													//vbox for strategy options
	stockVBox = new VBox();														//vbox for stock options
	chooseStrategy = new Label("Strategy");										//choose strategy Label
	blank1 = new Label("");														//blank label for spacing
	blank2 = new Label("");														//blank label for spacing
	blank3 = new Label("");
	title = new Label("Leo and Sean's Stock Watcher");
	title.setId("title");
	decision = new Label("Hold");
	decision.setId("decision");
	strategyGroup = new ToggleGroup();											//group that holds all the strategy radio buttons
	strategyGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
	      public void changed(ObservableValue<? extends Toggle> ov,
	          Toggle old_toggle, Toggle new_toggle) {
	    	  RadioButton chk = (RadioButton)new_toggle.getToggleGroup().getSelectedToggle(); // Cast object to radio button
	          strategyChoice = chk.getText();
	          System.out.println("Selected Radio Button - "+chk.getText());
	      }
	    });
	addRadioButtons();
	stockList = new ListView<String>();											//used to hold stock options
	stockList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	stockItems = FXCollections.observableArrayList (
		    "Whole Foods Market Inc: WFM\n", "Alphabet Inc (Google): GOOGL\n", 
		    "Coca Cola: COKE\n", "Popeyes: PLKI\n",
		    "Constellation Brands Inc. (Corona): STZ\n",
		    "Facebook: FB\n", "Apple: AAPL\n",
		    "NIKE: NKE\n", "Three M: MMM\n",
		    "The Hershey Company: HSY\n"
		    );				
	//items to be inserted into list view for stocks
	userStockList = new ListView<String>();
	userStockList.setEditable(false);
	stockList.setItems(stockItems);												//add the items to the list
	stockList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	        // Your action here
	    	try {
				stockViewer = new StockDataSubject();
			} catch (EncryptedDocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvalidFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	result = newValue.substring(newValue.indexOf(':')+2, newValue.indexOf('\n'));
	    	if(strategyChoice.equals("Strategy One")){
	    		//strategy = new ourStrategy(stockViewer);
	    		//call corresponding strategy and send values
	    		try {
					stockViewer.getStockPrice(result);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		strategy = new strategyOne();
	    		advice = strategy.getAdvice(stockViewer.getPrice(), stockViewer.getPrevPrice(), result);
	    		if(advice.equals("BUY")){
	    			decision.setStyle("-fx-text-fill: green;");
	    		}
	    		else if(advice.equals("SELL")){
	    			decision.setStyle("-fx-text-fill: red;");
	    		}
	    		else{
	    			decision.setStyle("-fx-text-fill: yellow;");
	    		}
	    		decision.setText(advice);
	    	}
	    	else if(strategyChoice.equals("Alternate Strategy")){
	    		//call corresponding strategy and send values
	    		try {
					stockViewer.getStockPrice(result);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		strategy = new alternateStrategy();
	    		advice = strategy.getAdvice(stockViewer.getPrice(), stockViewer.getPrevPrice(), result);
	    		if(advice.equals("BUY")){
	    			decision.setStyle("-fx-text-fill: green;");
	    		}
	    		else if(advice.equals("SELL")){
	    			decision.setStyle("-fx-text-fill: red;");
	    		}
	    		else{
	    			decision.setStyle("-fx-text-fill: yellow;");
	    		}
	    		decision.setText(advice);
	    	}
	    	else if(strategyChoice.equals("Random Strategy")){
	    		//call corresponding strategy and send values
	    		try {
					stockViewer.getStockPrice(result);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		strategy = new randomStrategy();
	    		advice = strategy.getAdvice(stockViewer.getPrice(), stockViewer.getPrevPrice(), result);
	    		if(advice.equals("BUY")){
	    			decision.setStyle("-fx-text-fill: green;");
	    		}
	    		else if(advice.equals("SELL")){
	    			decision.setStyle("-fx-text-fill: red;");
	    		}
	    		else{
	    			decision.setStyle("-fx-text-fill: yellow;");
	    		}
	    		decision.setText(advice);
	    	}
	    	else if(strategyChoice.equals("Leo and Sean's Strategy")){
	    		//call corresponding strategy and send values
	    		try {
					stockViewer.getStockPrice(result);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		strategy = new ourStrategy();
	    		advice = strategy.getAdvice(stockViewer.getPrice(), stockViewer.getPrevPrice(), result);
	    		if(advice.equals("BUY")){
	    			decision.setStyle("-fx-text-fill: green;");
	    		}
	    		else if(advice.equals("SELL")){
	    			decision.setStyle("-fx-text-fill: red;");
	    		}
	    		else{
	    			decision.setStyle("-fx-text-fill: yellow;");
	    		}
	    		decision.setText(advice);
	    	}
	    	//items to be inserted into list view for stocks
	    	userStockItems = FXCollections.observableArrayList (newValue);		
	    	userStockList.setItems(userStockItems);     
	    }
	});
	stockList.setPrefWidth(500);
	stockList.setPrefHeight(90);
	userStockList.setPrefWidth(500);
	userStockList.setPrefHeight(30);
}
public void positionBoxes(){
	//set alignment
	mainVBox.setAlignment(Pos.TOP_CENTER);
	stockVBox.setAlignment(Pos.TOP_CENTER);
	splitScreen.setAlignment(Pos.TOP_CENTER);
	//set padding
	splitScreen.setPadding(new Insets(10));
	mainVBox.setPadding(new Insets(10));
	strategyVBox.setPadding(new Insets(10));
	stockVBox.setPadding(new Insets(10));
}
public void addElementsToScene(){
	//add strategy radios to strategy group
	strategyRadioOne.setToggleGroup(strategyGroup);
	//continue adding radio buttons
	strategyRadioTwo.setToggleGroup(strategyGroup);
	strategyRadioThree.setToggleGroup(strategyGroup);
	strategyRadioFour.setToggleGroup(strategyGroup);
	//add label to main panel grid pane first column
	mainVBox.getChildren().addAll(title, splitScreen, decision);
	strategyVBox.getChildren().addAll(chooseStrategy, blank1, strategyRadioOne, strategyRadioTwo, strategyRadioThree, strategyRadioFour);
	stockVBox.getChildren().addAll(blank2, stockList, blank3, userStockList);
}
public void addRadioButtons(){
	strategyRadioOne = new RadioButton("Strategy One");							//first radio button for strategies
	strategyRadioTwo = new RadioButton("Alternate Strategy");					//second radio button for strategies
	strategyRadioThree = new RadioButton("Random Strategy");					//third radio button for strategies
	strategyRadioFour = new RadioButton("Leo and Seans Strategy");	//fourth radio button for strategies
}
}


package application;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
	private Label chooseStrategy, blank1, blank2, title;
	private ToggleGroup strategyGroup;
	private RadioButton strategyRadioOne, strategyRadioTwo, strategyRadioThree, strategyRadioFour;
	private ListView<String> stockList;
	private ObservableList<String> stockItems;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//stockStage =  primaryStage;
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			createAndSetVariables();											//method to instantiate all class variables
			positionBoxes();
			addElementsToScene();	
			strategyScene = new Scene(mainVBox, 800, 600);
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
	strategyVBox = new VBox();													//vbox for strategy options
	stockVBox = new VBox();														//vbox for stock options
	chooseStrategy = new Label("Strategy");										//choose strategy Label
	blank1 = new Label("");														//blank label for spacing
	blank2 = new Label("");														//blank label for spacing
	title = new Label("Title");
	strategyGroup = new ToggleGroup();											//group that holds all the strategy radio buttons
	strategyGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
	      public void changed(ObservableValue<? extends Toggle> ov,
	          Toggle old_toggle, Toggle new_toggle) {
	        if (strategyGroup.getSelectedToggle() != null) {
	          System.out.println(strategyGroup.getSelectedToggle().getUserData().toString());
	        }
	      }
	    });
	strategyRadioOne = new RadioButton("Strategy One");							//first radio button for strategies
	strategyRadioTwo = new RadioButton("Alternate Strategy");					//second radio button for strategies
	strategyRadioThree = new RadioButton("Random Strategy");					//third radio button for strategies
	strategyRadioFour = new RadioButton("Leo and Seans Strategy wink*wink*");	//fourth radio button for strategies
	stockList = new ListView<String>();											//used to hold stock options
	stockList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	stockItems = FXCollections.observableArrayList (
		    "Stock One", "Stock Two", "Stock Three", "Stock Four");				//items to be inserted into list view for stocks
	stockList.setItems(stockItems);												//add the items to the list
	stockList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
	    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	        // Your action here
	    	if(newValue.equals("Stock One")){
	    		System.out.println("You have chosen the first stock");
	    	}
	    	System.out.println("You chose: "+newValue);
	        
	    }
	});
	stockList.setPrefWidth(500);
	stockList.setPrefHeight(90);
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
	//set this radio to true by default
	strategyRadioOne.setSelected(true);
	//continue adding radio buttons
	strategyRadioTwo.setToggleGroup(strategyGroup);
	strategyRadioThree.setToggleGroup(strategyGroup);
	strategyRadioFour.setToggleGroup(strategyGroup);
	//add label to main panel grid pane first column
	mainVBox.getChildren().addAll(title, splitScreen);
	strategyVBox.getChildren().addAll(chooseStrategy, blank1, strategyRadioOne, strategyRadioTwo, strategyRadioThree, strategyRadioFour);
	stockVBox.getChildren().addAll(blank2, stockList);
}
}


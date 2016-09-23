package de.RR2015.gui;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import de.RR2015.gamehandler.GameHandler;
import de.RR2015.modell.Direction;
import de.RR2015.modell.iBlock;
import de.RR2015.modell.Roboter;
import de.RR2015.modell.Ziel;
import de.RR2015.modell.Stein;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Controller implements Initializable {
	IntegerProperty outerRectangleSize = new SimpleIntegerProperty(48);
	IntegerProperty innerRectangleSize = new SimpleIntegerProperty(40);
	IntegerProperty roboCircleSize = new SimpleIntegerProperty(15);
	//MapGenerator myMap = new MapGenerator(31114450);
//	LinkedList<iBlock> mylist = myMap.getBlockListe();
	GameHandler handler = new GameHandler();
	Rectangle test = new Rectangle();
	Slider slider = new Slider();
	
	
	@FXML
	private HBox upperHbox;
	
	@FXML
	private VBox second;
	
	@FXML
	private VBox vbox;
	
	@FXML
	private Button green;
	
	@FXML
	private Button blue;
	
	@FXML
	private Button red;
	
	@FXML
	private Button yellow;
	
	@FXML
	private GridPane grid;
	
	@FXML
	private HBox hbox;
	
	@FXML
	private void movement (KeyEvent event){
		switch (event.getCode()){
		case W:
			handler.move(Direction.UP);
			break;
		case S:
			handler.move(Direction.DOWN);
			break;
		case A:
			handler.move(Direction.LEFT);
			break;
		case D:
			handler.move(Direction.RIGHT);
			break;
		//Das hier ist nur zum Testen ob alles funktioniert... ich komm bei mir nicht an die Buttons ran und wollte nicht in deiner Ansicht rumpfuschen
		case J:
			greenRobot();
			break;
		case K:
			redRobot();
			break;
		case L:
			blueRobot();
			break;
		case P:
			yellowRobot();
			break;
			
		default:
			break;
		}
		fillGrid ();
	}
	
	@FXML
	private void greenRobot (){
		handler.setRobot(0);
	}
	@FXML
	private void redRobot (){
		handler.setRobot(1);
	}
	@FXML
	private void blueRobot (){
		handler.setRobot(2);
	}
	@FXML
	private void yellowRobot (){
		handler.setRobot(3);
	}
	
	private void fillGrid () {
		LinkedList<iBlock> mylist = handler.getMap();		
		for (int i = 0; i < handler.getRows(); i++) {
			for (int j = 0; j < handler.getColoumns(); j++) {
				StackPane thisstack = new StackPane();
				// Einen Block mit den Koordinaten dieser iteration erstelen
				iBlock dummy = new iBlock(j, i);
				// Testen ob dieser Block in der Liste enthalten ist
				if(!mylist.contains(dummy)) {
					//Wenn der Block nicht enthalten ist wird das Feld leer gezeichnet + schwarzen Rand
					Rectangle bigRec = new Rectangle();
					Rectangle smallRec = new Rectangle();
					
					bigRec.heightProperty().bind(slider.valueProperty());
					bigRec.widthProperty().bind(slider.valueProperty());
					
					smallRec.heightProperty().bind(bigRec.heightProperty().multiply(0.9));
					smallRec.widthProperty().bind(bigRec.widthProperty().multiply(0.9));
					
					bigRec.setFill(Color.BLACK);
					
					smallRec.setFill(Color.WHITE);
					
					thisstack.getChildren().add(bigRec);
					thisstack.getChildren().add(smallRec);
				} else {
					//Holt einen Stein der als Dynamischer Typ nicht iBlock ist aus der Liste
					dummy = mylist.get(mylist.indexOf(dummy));
					//Überprüfen ob es ein roboter ist
					if (dummy instanceof Roboter) {
						
						Rectangle bigRec = new Rectangle();
						Rectangle smallRec = new Rectangle();
						Circle circle = new Circle();
						
						circle.setFill(((Roboter) dummy).getFarbe());
						
						bigRec.heightProperty().bind(slider.valueProperty());
						bigRec.widthProperty().bind(slider.valueProperty());
						
						smallRec.heightProperty().bind(bigRec.heightProperty().multiply(0.9));
						smallRec.widthProperty().bind(bigRec.widthProperty().multiply(0.9));
						
						circle.radiusProperty().bind(smallRec.heightProperty().multiply(0.3));
						
						bigRec.setFill(Color.BLACK);
						
						smallRec.setFill(Color.WHITE);
				
						thisstack.getChildren().add(bigRec);
						thisstack.getChildren().add(smallRec);
						thisstack.getChildren().add(circle);
						
						
					}
					//Überprüfen ob es ein Ziel ist
					if (dummy instanceof Ziel) {
						Rectangle bigRec = new Rectangle();
						Rectangle smallRec = new Rectangle();
						
						bigRec.heightProperty().bind(slider.valueProperty());
						bigRec.widthProperty().bind(slider.valueProperty());
						
						smallRec.heightProperty().bind(bigRec.heightProperty().multiply(0.9));
						smallRec.widthProperty().bind(bigRec.widthProperty().multiply(0.9));
						
						bigRec.setFill(Color.BLACK);
						
						smallRec.setFill(((Ziel) dummy).getZielFarbe());
						
						thisstack.getChildren().add(bigRec);
						thisstack.getChildren().add(smallRec);
					}
					
					//Überprüft ob es ein Normaler Stein ist
					if (dummy instanceof Stein) {
						Rectangle bigRec = new Rectangle();
						Rectangle smallRec = new Rectangle();
						
						bigRec.heightProperty().bind(slider.valueProperty());
						bigRec.widthProperty().bind(slider.valueProperty());
						
						smallRec.heightProperty().bind(bigRec.heightProperty().multiply(0.9));
						smallRec.widthProperty().bind(bigRec.widthProperty().multiply(0.9));
						
						bigRec.setFill(Color.BLACK);
						
						smallRec.setFill(Color.GRAY);
						
						thisstack.getChildren().add(bigRec);
						thisstack.getChildren().add(smallRec);
					}
				}
				
			
				grid.add(thisstack, j, i);
			}
			
		}
	}
	
	public void sliderSettings () {
		slider.setMin(0);
		slider.setMax(100);
		slider.setValue(50);
		
	}

	@Override
	public void initialize(URL location, ResourceBundle ressources) {
		// TODO Auto-generated method stub
		//grid.add(new StackPane(), 0, 0);

	sliderSettings();
	fillGrid();
	second.getChildren().add(slider);
	}

}

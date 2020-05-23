package up.mi.ken;

import java.util.HashMap;

import com.sun.org.apache.xpath.internal.axes.HasPositionalPredChecker;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class InterfaceMainApp extends Application{

	

	
	@SuppressWarnings("unused")
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Circle circle = new Circle();
		circle.setCenterX(100.0f);
		circle.setCenterY(100.0f);
		circle.setRadius(50.0f);
		
		Circle circle2 = new Circle();
		circle2.setCenterX(100.0f);
		circle2.setCenterY(100.0f);
		circle2.setRadius(50.0f);
		
		Color colorBlue = Color.BLUE;
		Color colorRed=Color.RED;
		//circle.setStroke(color);
		circle.setFill(colorBlue);
		
		
		
		
		// NavBar
		
		FlowPane fpNav= new FlowPane();
		Button start= new Button("Start"); //giveUp et start doivent representer le meme bouton
		Button giveUp= new Button("Give up");
		Button histo= new Button("Historique");
		Button menu= new Button("Menu");
		Label lab= new Label(""+'\n'+'\n');
		fpNav.getChildren().addAll(start,giveUp, histo,menu,lab);
		fpNav.setHgap(3);
		
		// Timer 
	
		Label winner= new Label("Winner :IA");
		Label algoDurationeExecution= new Label("answer after :2000ms");
		Label time= new Label ("20 s");
		
		
		
		//game config
		
		FlowPane fp= new FlowPane();
		
		Label labAlgo= new Label(""+'\n'+"Choice Algo");
		ChoiceBox<String> choiceBox =new ChoiceBox<String>();
		choiceBox.getItems().add("Minimax");
		choiceBox.getItems().add("Alphabeta");
		choiceBox.setPrefWidth(150);
		
		Label labLevel= new Label(""+'\n'+'\n'+"Choice Level");
		ChoiceBox<String> level =new ChoiceBox<String>();
		level.getItems().add("Facile");
		level.getItems().add("Moyen");
		level.getItems().add("Difficile");
		level.setPrefWidth(150);
		
		Label whoStart= new Label(""+'\n'+'\n'+"Who Start ?");
		ChoiceBox<String> begin =new ChoiceBox<String>();
		begin.getItems().add("IA");
		begin.getItems().add("Human");
		begin.setPrefWidth(150);
		
		Label end= new Label(""+'\n'+'\n'+"Result ");
		end.setVisible(false);
		
		ToggleGroup tg= new ToggleGroup();
		RadioButton b1= new RadioButton("IA");
		b1.setToggleGroup(tg);
		b1.setSelected(true);
		RadioButton b2= new RadioButton("Human");
		b2.setToggleGroup(tg);
		
		fp.setOrientation(Orientation.VERTICAL);
		//fp.setVgap(50);
		//fp.getChildren().addAll(labAlgo,choiceBox,labLevel,level,whoStart,begin);
		
		fp.getChildren().addAll(labAlgo,choiceBox,labLevel,level,whoStart,begin,end);
		
		/*fp.setOrientation(Orientation.HORIZONTAL);
		
		fp.getChildren().addAll(b1,b2); */
		
		
	// game board
	 GridPane gp= new GridPane();
		for(int i=0; i<6;i++) 
		{
			for(int j=0;j<7;j++)
			{
				Circle circle1 = new Circle();
				circle1.setCenterX(100.0f);
				circle1.setCenterY(100.0f);
				circle1.setRadius(50.0f);
				circle1.setFill(Color.WHITE);
				//GridPane.se
				gp.add(circle1,j,i);
			}
		}
		
		//
		circle2.setFill(Color.WHITE);
		BorderPane pane= new BorderPane();
		pane.setTop(fpNav);
		pane.setRight(fp);	
		Board b = new Board();
		Puissance4 ai= new Puissance4(b);
		//ai.setMaxDepth(11);
		int i;
		for(i=1;i<=7;i++){
			Circle cir=(Circle) gp.getChildren().get(i-1);
			cir.setOnMousePressed(event -> {
			   	//gp.getChildren().get(index)	
				int win = ai.checkWinner(b);
				if (win == 1) {
					System.out.println("AI Wins!");
					end.setText(""+'\n'+'\n'+"Result AI Wins!");
					end.setVisible(true);
					return;
				} else if (win == 2) {
					System.out.println("You Win!");
					end.setText(""+'\n'+'\n'+"Result You Wins!");
					end.setVisible(true);
					return;
				} else if (win == 0) {
					System.out.println("Draw!");
					end.setText(""+'\n'+'\n'+"Result Draw!");
					end.setVisible(true);
					return;
				}
				else {
			  	System.out.println("ndione ");
			  	System.out.println("Row: "+ GridPane.getRowIndex(cir));
			  	System.out.println("Column: "+ GridPane.getColumnIndex(cir));
			  	int col=GridPane.getColumnIndex(cir);
			  	int ligne =ai.guiHumainPlay(GridPane.getColumnIndex(cir));
			  	System.out.println("Ligne "+ligne);
			  	System.out.println("Col "+col);
			   	((Shape) gp.getChildren().get((ligne*7)+col)).setFill(Color.RED);
			   
				 win = ai.checkWinner(b);
				if (win == 1) {
					System.out.println("AI Wins!");
					end.setText(""+'\n'+'\n'+"Result AI Wins!");
					end.setVisible(true);
					return;
				} else if (win == 2) {
					System.out.println("You Win!");
					end.setText(""+'\n'+'\n'+"Result You Wins!");
					end.setVisible(true);
					return;
				} else if (win == 0) {
					System.out.println("Draw!");
					end.setText(""+'\n'+'\n'+"Result Draw!");
					end.setVisible(true);
					return;
				}
			   	
			   	int colIA=ai.getAIMove();
			   	System.out.println("colIA "+colIA);
			   	int ligneAI	=ai.guiAIolay(colIA);
			 //  	System.err.println(ligneAI);
			 	if (ligne==-1 || ligneAI==-1) 
			   	{
			   		System.out.println("ligne :"+ligne);
			   		System.out.println("ligneAI :"+ligneAI);
			   		
			   		
			   	}
			   	((Shape) gp.getChildren().get((ligneAI*7)+colIA)).setFill(Color.YELLOW);
				
			   	win = ai.checkWinner(b);
				if (win == 1) {
					System.out.println("AI Wins!");
					end.setText(""+'\n'+'\n'+"Result AI Wins!");
					end.setVisible(true);
					return;
				} else if (win == 2) {
					System.out.println("You Win!");
					end.setText(""+'\n'+'\n'+"Result You Wins!");
					end.setVisible(true);
					return;
				} else if (win == 0) {
					System.out.println("Draw!");
					end.setText(""+'\n'+'\n'+"Result Draw!");
					end.setVisible(true);
					return;
				}
			   	
			  
				}
			  });
		}
		
		
		//gp.setOnMousePressed();
		//gp.setStyle("-fx-background-color:POWDERBLUE");
		pane.setCenter(gp);
		pane.getCenter().setStyle("-fx-background-color:BLUE");
		
		Scene scene = new Scene(pane);
		stage.sizeToScene(); 
		stage.setScene(scene);
		stage.show();
		
	}
	
	public static void main(String arg[]) 
	{
		launch();
	}
	
}

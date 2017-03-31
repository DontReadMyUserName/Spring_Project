package application;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;

public class Controller {
	private Main mainApp;
	@FXML
	private Button Snake1;
	@FXML
	private Button Pong1;	
	@FXML
	private Button Exe;
	@FXML
	private Button Target1;
	private String fPath="";
	public Controller(){
	
	}
	@FXML
	private void initialize(){			
		Pong1.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				try{
		            Pong ctc = new Pong();
		             ctc.start(Pong.classStage);
				}
				catch(Exception ex)
				{
	   			}
			}
         });
		Snake1.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				try{
		            Snake ctc = new Snake();
		             ctc.start(Snake.classStage);
				}
				catch(Exception ex)
				{
				}
			}
         });
         Target1.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				try{
		            Target ctc = new Target();
		             ctc.start(Target.classStage);
				}
				catch(Exception ex)
				{
				}
			}
         });
         Exe.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				try{
					TextInputDialog dialog = new TextInputDialog("C:\\Program Files (x86)\\Give\\Colin a\\100%\\Plz.exe");
					dialog.setTitle("File Path");
					dialog.setHeaderText("Look, a Text Input Dialog for example.");
					dialog.setContentText("Please enter file path with extension:");

					Optional<String> result = dialog.showAndWait();
					if (result.isPresent()){
					    fPath = result.get();
					}
					 Process proc = Runtime.getRuntime().exec("java -jar " + fPath);} 
				catch(Exception ex)
				{}
			}
         });
	}
	public void setMainapp(Main mainapp){
	this.mainApp = mainApp;
	}
		}


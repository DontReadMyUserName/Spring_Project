package application;

import java.io.IOException;

import application.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage primaryStage;
	private Pane rootLayout;
	public void start(Stage primaryStage) {
		try{
			this.primaryStage =primaryStage;
			this.primaryStage.setTitle("Spring Project");
			initRootLayout();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		launch(args);
	}
	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/amland/view/rootLayout.fxml"));
			rootLayout =(Pane) loader.load();
			Scene scene = new Scene(rootLayout);
			Controller controller = loader.getController();
			controller.setMainapp(this);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}

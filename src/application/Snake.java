package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Snake extends Application{
	static Stage classStage = new Stage();
	
	public enum Direction{
		UP,DOWN,LEFT,RIGHT
	}
	//Sets these as final values because they are used frequently
	public static final int sSize= 30;
	public static final int High=20*sSize;
	public static final int Wid=25*sSize;
	private Direction direction = Direction.RIGHT;
	private boolean moved = false;
	private boolean running = false;
	private Timeline timeline = new Timeline();
	private ObservableList<Node> snake;
	private Parent createContent(){
		Pane root= new Pane();
		root.setPrefSize(Wid, High);
		root.setStyle("-fx-background-color: #000000;");
		Group snakeBody = new Group();
		snake = snakeBody.getChildren();
		//This makes the apple that the snake eats
		Rectangle apple = new Rectangle(sSize,sSize);
		apple.setFill(Color.RED);
		//This sets position of apple randomly but only to locations that aligns with snake
		apple.setTranslateX((int)(Math.random()*Wid)/sSize*sSize);
		apple.setTranslateY((int)(Math.random()*High)/sSize*sSize);
		//Sets how often the snakes moves
		KeyFrame frame= new KeyFrame(Duration.millis(60), event ->{
			if (!running)
				return;
			boolean toRemove = snake.size() >1;

			Node tail = toRemove ? snake.remove(snake.size()-1) : snake.get(0);
			((Shape) tail).setFill(Color.GREEN);
			double tailX = tail.getTranslateX();
			double tailY = tail.getTranslateY();
			//Sets a switch statement that defines how the snake will move if a certain enum is picked
			switch (direction) {
			case UP:
				tail.setTranslateX(snake.get(0).getTranslateX());
				tail.setTranslateY(snake.get(0).getTranslateY()-(sSize));
				break;
			case DOWN:
				tail.setTranslateX(snake.get(0).getTranslateX());
				tail.setTranslateY(snake.get(0).getTranslateY()+(sSize));
				break;
			case LEFT:
				tail.setTranslateX(snake.get(0).getTranslateX()-(sSize));
				tail.setTranslateY(snake.get(0).getTranslateY());
				break;
			case RIGHT:
				tail.setTranslateX(snake.get(0).getTranslateX()+(sSize));
				tail.setTranslateY(snake.get(0).getTranslateY());
				break;
			}
			moved = true;
			if (toRemove)
				snake.add(0,tail);
			for(Node rect: snake){
				//This checks to see if any of the snake is overlapping or trying to bisect itself.
				if (rect !=tail && tail.getTranslateX()== rect.getTranslateX() 
						&& tail.getTranslateY() == rect.getTranslateY()){
					//Calls the restartGame method to continue the game.
					restartGame();
					break;
				}
			}
			//This checks if the snake is trying to move off screen/ boundary
			if (tail.getTranslateX()<0 || tail.getTranslateX() >= Wid || tail.getTranslateY()<0 
					|| tail.getTranslateY() >=High){
				//Calls the restartGame method to continue the game.
				restartGame();
			}
			//If the head of the snake is on top of the apple then it will make a new apple, delete the old one, and give it new position
			if (tail.getTranslateX() == apple.getTranslateX() && tail.getTranslateY() == apple.getTranslateY()){
				apple.setTranslateX((int)(Math.random()*(Wid-sSize))/sSize*sSize);
				apple.setTranslateY((int)(Math.random()*(High-sSize))/sSize*sSize);
				//This adds a new rect to the snake, so it adds on to the snake
				Rectangle rect = new Rectangle(sSize, sSize);
				rect.setTranslateX(tailX);
				rect.setTranslateY(tailY);
				snake.add(rect);
				//TO-DO score ++;
			}
		});
		timeline.getKeyFrames().add(frame);
		//Makes the game go on forever and not to a certain time limit.
		timeline.setCycleCount(Timeline.INDEFINITE);
		root.getChildren().addAll(apple,snakeBody);
		return root;
	}	
	//Method to restart the game
	private void restartGame(){
		stopGame();
		startGame();
	}
	//This method is part of the restartGame method. It's job is to stop the timeline.
	private void stopGame(){
		running = false;
		timeline.stop();
		snake.clear();
	}
	//This method is part of the restartGame method. It's job is to give the snake a set
	// direction to start. It also places the dead and makes the timeline start.
	private void startGame(){
		direction = Direction.RIGHT;
		Rectangle head = new Rectangle(sSize, sSize);
		snake.add(head);
		timeline.play();
		running = true;
	}
	@Override
	public void start(Stage primaryStage)throws Exception{
		Scene scene= new Scene(createContent());
		scene.setOnKeyPressed(event -> {
			//This checks if the snake is moving and if so it then takes W,A,S,and D and sets them to the enums above.
			if (moved){
				switch (event.getCode()){
				case W:
					if (direction !=Direction.DOWN)
						direction = Direction.UP;
					break;
				case S:
					if (direction != Direction.UP)
						direction = Direction.DOWN;
					break;
				case D:
					if (direction !=Direction.LEFT)
						direction = Direction.RIGHT;
					break;
				case A:
					if (direction !=Direction.RIGHT)
						direction = Direction.LEFT;
					break;
				default:
					break;
				}
			}
			moved = false;
		});
		primaryStage.setTitle("Snake");
		primaryStage.setScene(scene);
		primaryStage.show();
		startGame();
		Snake.classStage = primaryStage;
	}
	public static void main(String[] args){
		launch(args);
	}
}
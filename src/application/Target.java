package application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.application.Application;

public class Target extends Application
{
	static Stage classStage = new Stage();
    public static int score= 0;	
    public static double lastHitX=0;
    public static double lastHitY=0;
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage theStage) 
    {
        theStage.setTitle( "Invisible Cursor!" );
        Target.classStage = theStage;
        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas(500, 500 );
        //Image restart = new Image("restart.png");
        
        root.getChildren().add( canvas );

        Circle targetData = new Circle(100,100,32);
        Circle targetDataS= new Circle (100,250,32);
        Circle targetDataA= new Circle (100,400,32);
        

        theScene.setOnMouseClicked(
            new EventHandler<MouseEvent>()
            {
                public void handle(MouseEvent e)
                {
                    if ( targetData.contains( e.getX(), e.getY() ) )
                    {
                    	theScene.setCursor(Cursor.NONE);
                        double x = 50 + 400 * Math.random(); 
                        double y = 50 + 400 * Math.random();
                        targetData.setCenterY(y);
                        targetData.setCenterX(x);
                        score++;
                        lastHitX= e.getX();
                        lastHitY=e.getY();
                    }
                    else if (targetDataS.contains(e.getX(), e.getY() ) ){
                    	double z = 50 + 400 * Math.random();
                    	double w = 50 + 400 * Math.random();
                    	targetDataS.setCenterX(z);
                    	targetDataS.setCenterY(w);
                    	score++;
                        lastHitX= e.getX();
                        lastHitY=e.getY();
                        theScene.setCursor(Cursor.NONE);
                    }
                    else if (targetDataA.contains(e.getX(), e.getY() ) ){
                    	double q = 50 + 400 * Math.random();
                    	double a = 50 + 400 * Math.random();
                    	targetDataA.setCenterX(q);
                    	targetDataA.setCenterY(a);
                    	score++;
                        lastHitX= e.getX();
                        lastHitY=e.getY();
                        theScene.setCursor(Cursor.NONE);
                    }
                    else if (score <= 0){
                    	theScene.setCursor(Cursor.DEFAULT);
                    }
                    else{
                    	score-=5;
                        lastHitX= e.getX();
                        lastHitY=e.getY();
                    }
                }
            });

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont( theFont );


        Image bullseye = new Image( "Resources/target.png" );

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // Clear the canvas
                gc.setFill(Color.BLACK);
                gc.fillRect(0,0, 512,512);

                gc.drawImage( bullseye, 
                        targetData.getCenterX() - targetData.getRadius(),
                        targetData.getCenterY() - targetData.getRadius() );
                
                gc.drawImage( bullseye, 
                        targetDataS.getCenterX() - targetDataS.getRadius(),
                        targetDataS.getCenterY() - targetDataS.getRadius() );
               
                gc.drawImage( bullseye, 
                        targetDataA.getCenterX() - targetDataA.getRadius(),
                        targetDataA.getCenterY() - targetDataA.getRadius() );
               
                
                gc.setFill( Color.WHITE );
                gc.fillRect(lastHitX, lastHitY,5,5);    
                gc.setFill( Color.WHITE );

                String pointsText = "Points: " + score;
                gc.fillText( pointsText, 360, 36 );
                gc.strokeText( pointsText, 360, 36 );
            }
        }.start();


        theStage.show();
    }
}
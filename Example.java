import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;
public class Example extends Application implements EventHandler<InputEvent>
{
	GraphicsContext gc;
	AnimateObjects animate;
	private int x=0;
	private int velocity = 1;
	Image trooper;
	AudioClip clip;
	AudioClip clip2;
	private ArrayList<AudioClip> hi=new ArrayList<AudioClip>();;
	public static void main(String[] args)
	{
		launch();
	}
	public void handle(final InputEvent event)
	{
		if(event instanceof KeyEvent)
			if (((KeyEvent)event).getCode() == KeyCode.LEFT )
			{
				velocity*=-1;
				hi.get(1).play();
			}
		if (event instanceof MouseEvent)
		{
			System.out.println("x: "+((MouseEvent)event).getX() );
			System.out.println("y: "+((MouseEvent)event).getY()+"\n" );
			hi.get(0).play();
		}
	}
	public void start(Stage stage)
	{
		URL resource = getClass().getResource("shoot.wav");
		clip = new AudioClip(resource.toString());
		hi.add(clip);
		resource = getClass().getResource("explosion.mp3");
		clip = new AudioClip(resource.toString());
		hi.add(clip);
		stage.setTitle("Final Project Title");
		Group root = new Group();
		Canvas canvas = new Canvas(800, 400);
		gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		trooper = new Image("trooper.jpg");
		scene.addEventHandler(KeyEvent.KEY_PRESSED,this);
		scene.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
	//	gc.drawImage( trooper, 180, 100 );
		animate = new AnimateObjects();
		animate.start();
		stage.show();

	}
	public class AnimateObjects extends AnimationTimer
	{
		public void handle(long now)
		{
			if (x>-50)
			{
				if (180+x == 0)
					velocity *=-1;
				if ( x == 400)
					velocity *=-1;
				x+=velocity;
				gc.drawImage( trooper, 180+x, 100 );
				Rectangle2D rect1 = new Rectangle2D( 400,100,100,100 );
				gc.fillRect(400,100,100,100);
				Rectangle2D rect2 = new Rectangle2D( 180+x,100,trooper.getWidth(),trooper.getHeight() );
				if (rect1.intersects(rect2))
				{
					System.out.print("HIT ");
				}
			}
			else
			{
				// we are going to display Game over if the user moves 50 pixels to the left
				gc.setFill( Color.YELLOW); //Fills the text in yellow
				gc.setStroke( Color.BLACK ); //Changes the outline the black
				gc.setLineWidth(1); //How big the black lines will be
				Font font = Font.font( "Arial", FontWeight.NORMAL, 48 );
				gc.setFont( font );
				gc.fillText( "Game Over", 100, 50 ); //draws the yellow part of the text
				gc.strokeText( "Game Over", 100, 50 ); //draws the outline part of the text
			}
		}
	}

}
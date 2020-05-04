import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.lang.Object;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;
import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.scene.transform.Rotate;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
import javafx.scene.shape.Polygon;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;
public class ShahilProject extends Application implements EventHandler<InputEvent>
{
	private GraphicsContext gc;
	private AnimateObjects animate;
	private Tank tank;
	private Missle rocket;
	private Plane fighter;
	private Text msg;
	private Bomber tact;
	private Bomb bom;
	private Napalm nap;
	private NapalmBomber napbomber;
	private int x=0;
	private int planespos=0;
	private int misslepos=0;
	private int rand;
	private int count=0;
	private int score=0;
	private int posit;
	private int exploscount=0;
	private int exploscount2=0;
	private int exploscount3=0;
	private int explotemp=0;
	private int randspeed=0;
	private int bombtempx;
	private int bombtempy;
	private int explotemp2=0;
	private int level=0;
	private double time=6000.0;
	private Image im;
	private AudioClip clip;
	private Rectangle2D rect2;
	private Rectangle2D rect3;
	private Rectangle2D rect4;
	private Rectangle2D rect5;
	private Rectangle2D rect6;
	private Rectangle2D rect7;
	private Rectangle2D rect8;
	private Rectangle2D rect9;
	private static Rectangle2D rect10;
	private Canvas canvas;
	private boolean keypress=false;
	private boolean plane=false;
	private boolean bomber=false;
	private boolean napplane=false;
	private boolean napalm=false;
	private boolean bomb=false;
	private boolean missle=false;
	private boolean level2good=false;
	private boolean level3good=false;
	private boolean explobomber=false;
	private boolean rightcannon=false;
	private boolean leftcannon=false;
	private boolean midcannon=true;
	private boolean explo=false;
	private boolean missle2=false;
	private boolean alreadymissle=false;
	private ArrayList<AudioClip> sounds=new ArrayList<AudioClip>();
	private ArrayList<Napalm> multinaps=new ArrayList<Napalm>();
	private ArrayList<Rectangle2D> rects=new ArrayList<Rectangle2D>();
	private ArrayList<Image> things=new ArrayList<Image>();
	public static void main(String[] args)
	{
			launch();
	}
	public void handle(final InputEvent event)
	{
		if(event instanceof KeyEvent)
			if (((KeyEvent)event).getCode() == KeyCode.LEFT)
			{
				if(180+x>10)
					x+=tank.moveLeft();
			}
			else if(((KeyEvent)event).getCode() == KeyCode.RIGHT)
			{
				if(180+x<745)
					x+=tank.moveRight();
			}
			else if(((KeyEvent)event).getCode() ==  KeyCode.J)
			{
				keypress=true;
			}
			else if(((KeyEvent)event).getCode() ==  KeyCode.M)
			{
				time-=2000;
			}
			else if(((KeyEvent)event).getCode() ==  KeyCode.N)
			{
				time+=2000;
			}
			else if(((KeyEvent)event).getCode() ==  KeyCode.T)
			{
				score+=100;
			}
			else if(((KeyEvent)event).getCode() ==  KeyCode.Y)
			{
				level++;
			}
			else if(((KeyEvent)event).getCode() ==  KeyCode.DIGIT1&&!alreadymissle)
			{
				rightcannon=false;
				leftcannon=true;
				midcannon=false;
			}
			else if(((KeyEvent)event).getCode() ==  KeyCode.DIGIT2&&!alreadymissle)
			{
				rightcannon=false;
				leftcannon=false;
				midcannon=true;
			}
			else if(((KeyEvent)event).getCode() ==  KeyCode.DIGIT3&&!alreadymissle)
			{
				rightcannon=true;
				leftcannon=false;
				midcannon=false;
			}
			else if(((KeyEvent)event).getCode() ==  KeyCode.SPACE&&!alreadymissle)
			{
				missle=true;
			}
		if (event instanceof MouseEvent)
		{
			System.out.println("x: "+((MouseEvent)event).getX() );
			System.out.println("y: "+((MouseEvent)event).getY()+"\n" );
			if(((MouseEvent)event).getY()<240&&((MouseEvent)event).getY()>220&&((MouseEvent)event).getX()<607&&((MouseEvent)event).getX()>585)
			{
				if(level==0)
					level++;
				else if(level==2)
				{
					level2good=true;
					sounds.get(3).stop();
				}
				else if(level==3)
				{
					level3good=true;
					sounds.get(3).stop();
				}
				else
				{
					keypress=false;
					plane=false;
					missle=false;
					explo=false;
					missle2=false;
					rightcannon=false;
					leftcannon=false;
			 		midcannon=true;
			 		rect3=null;
					rect4=null;
					rect5=null;
					rect6=null;
					rect7=null;
					napplane=false;
					bomber=false;
					rect8=null;
					rect9=null;
					rect10=null;
					alreadymissle=false;
					x=0;
					randspeed=0;
					planespos=0;
					sounds.get(3).stop();
					misslepos=0;
					score=0;
					exploscount=0;
					explotemp=0;
					explotemp2=0;
					explobomber=false;
					time=6000.0;
					rects.clear();
					multinaps.clear();
					level=1;
					level2good=false;
					level3good=false;
				}
			}
		}
	}
	public void start(Stage stage)
	{
		URL resource = getClass().getResource("shoot.wav"); //0
		clip = new AudioClip(resource.toString());
		sounds.add(clip);
		resource = getClass().getResource("explosion.wav"); //1
		clip = new AudioClip(resource.toString());
		sounds.add(clip);
		resource = getClass().getResource("flyby.wav"); //2
		clip = new AudioClip(resource.toString());
		sounds.add(clip);
		resource = getClass().getResource("backmusic.wav"); //3
		clip = new AudioClip(resource.toString());
		sounds.add(clip);
		resource = getClass().getResource("alarm.wav"); //4
		clip = new AudioClip(resource.toString());
		sounds.add(clip);
		resource = getClass().getResource("bombersound.wav"); //5
		clip = new AudioClip(resource.toString());
		sounds.add(clip);
		resource = getClass().getResource("bomberbombnoise.wav"); //6
		clip = new AudioClip(resource.toString());
		sounds.add(clip);
		resource = getClass().getResource("napalmsound.mp3"); //7
		clip = new AudioClip(resource.toString());
		sounds.add(clip);
		resource = getClass().getResource("napalmbombersound.wav"); //8
		clip = new AudioClip(resource.toString());
		sounds.add(clip);
		stage.setTitle("Shahil Patel's Final Project");
		Group root = new Group();
		canvas = new Canvas(800, 400);
		gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		Scene scene = new Scene(root);
		im = new Image("tank.png"); //0
		things.add(im);
		im = new Image("grass.jpg"); //1
		things.add(im);
		im = new Image("sky.png"); //2
		things.add(im);
		im = new Image("germanplane.png"); //3
		things.add(im);
		im = new Image("cannon.png"); //4
		things.add(im);
		im = new Image("cannon45.png"); //5
		things.add(im);
		im = new Image("cannon315.png"); //6
		things.add(im);
		im = new Image("missle0.png"); //7
		things.add(im);
		im = new Image("missle315.png"); //8
		things.add(im);
		im = new Image("missle45.png"); //9
		things.add(im);
		im = new Image("boom.png"); //10
		things.add(im);
		im = new Image("bomb.png"); //11
		things.add(im);
		im = new Image("bomber.png"); //12
		things.add(im);
		im = new Image("napalm.gif"); //13
		things.add(im);
		im = new Image("napalmBomber.png"); //14
		things.add(im);
		im = new Image("napalmmissle.png"); //15
		things.add(im);
		im = new Image("beach.png"); //16
		things.add(im);
		im = new Image("sand.png"); //17
		things.add(im);
		im = new Image("jungle.jpg"); //18
		things.add(im);
		im = new Image("jungleground.jpg"); //18
		things.add(im);
		stage.setScene(scene);
		gc.setFill(Color.YELLOW);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		scene.addEventHandler(KeyEvent.KEY_PRESSED,this);
		scene.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
		animate = new AnimateObjects();
		animate.start();
		stage.show();
	}
	public class AnimateObjects extends AnimationTimer
	{
		public void handle(long now)
		{

			if(!keypress&&time>0)
			{
				if(time==5999)
					sounds.get(3).play();
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				rect2 = new Rectangle2D( 185+x,365,40,15);
		//		gc.fillRect(185+x,365,40,15); 			//Shows Hitbox of tank
				if(level<=1)
				{
					gc.drawImage(things.get(2),0,0,800,400 );
					gc.drawImage( things.get(1),0,370,800,45 );
				}
				else if(level==2)
				{
					gc.drawImage(things.get(16),0,0,800,400 );
					gc.drawImage( things.get(17),0,370,800,45 );
				}
				else
				{
					gc.drawImage(things.get(18),0,0,800,400 );
					gc.drawImage( things.get(19),0,382,800,25 );
				}
				gc.setFill(Color.BLACK);
				tank=new Tank(180+x,360,50,25);
				gc.drawImage( things.get(0), tank.getXcord(),tank.getYcord(),tank.getH(),tank.getW());
				if(midcannon)
				{
					gc.drawImage(things.get(4),200+x,345,15,25 );
				}
				else if(rightcannon)
				{
					gc.drawImage(things.get(5),196+x,340,40,30 );
				}
				else if(leftcannon)
				{
					gc.drawImage(things.get(6),183+x,340,40,30 );
				}
				if(time>0)
				{
					time--;
				}
				if(level==3&&level3good) //LEVEL 3
				{
					if((int)(Math.random()*1000)%100==0&&napplane==false&&level==3)
					{
						rand=(int)(Math.random()*140)+50;
						napbomber=new NapalmBomber(800,rand,50,25);
						sounds.get(8).play();
						napplane=true;
						randspeed=(int)(Math.random()*3)+2;
					}
 					if(napplane)
 					{
						gc.drawImage(things.get(14),napbomber.getXcord(),napbomber.getYcord(),napbomber.getH(),napbomber.getW());
						napbomber.addXcord(-1*randspeed);
						rect9 = new Rectangle2D( napbomber.getXcord()+10,napbomber.getYcord(),35,20);
				//		gc.fillRect(napbomber.getXcord()+10,napbomber.getYcord(),35,20); //Shows Hitbox of Napalm Bomber
						if(napbomber.getXcord()<-10)
						{
							napplane=false;
							napbomber.setXcord(0);
							if(score>=50)
								score-=50;
						}
						if(napbomber.getXcord()>175+x&&napbomber.getXcord()<185+x&&!napalm)
						{
							bombtempx=napbomber.getXcord();
							bombtempy=napbomber.getYcord();
							napalm=true;
						}
					}
					if(napalm)
					{
						rand=(int)(Math.random()*4)+2;
						bombtempy+=rand;
						nap=new Napalm(bombtempx,bombtempy,50,25);
						rect10=new Rectangle2D(bombtempx,bombtempy,50,25);
						if(bombtempy+20<330)
							gc.drawImage(things.get(15),nap.getXcord(),nap.getYcord()+20,50,25);
						if(bombtempy>320)
						{
							sounds.get(7).play();
							napalm=false;
							multinaps.add(new Napalm(bombtempx,bombtempy+20,50,25));
							rects.add(new Rectangle2D(bombtempx,bombtempy+20,50,25));
						}
					}
					for(int x=0;x<rects.size();x++)
					{
						gc.drawImage(things.get(13),multinaps.get(x).getXcord(),multinaps.get(x).getYcord()+20,50,25);
					}
				}
				if(level==2&&level2good) //LEVEL 2
				{
					if((int)(Math.random()*1000)%100==0&&bomber==false&&level==2)
					{
						rand=(int)(Math.random()*140)+50;
						tact=new Bomber(800,rand,50,25);
						sounds.get(5).play();
						bomber=true;
						randspeed=(int)(Math.random()*3)+2;
					}
					if(bomber)
					{
						gc.drawImage(things.get(12),tact.getXcord(),tact.getYcord(),tact.getH(),tact.getW());
						tact.addXcord(-1*randspeed);
						rect7 = new Rectangle2D( tact.getXcord()+10,tact.getYcord(),35,20);
			//			gc.fillRect(tact.getXcord()+10,tact.getYcord(),35,20); //Shows Hitbox of bomber
						if(tact.getXcord()<-10)
						{
							bomber=false;
							tact.setXcord(0);
							if(score>=50)
								score-=50;
						}
						if(!bomb&&tact.getXcord()>175+x&&tact.getXcord()<185+x)
						{
							bombtempx=tact.getXcord();
							bombtempy=tact.getYcord();
							bomb=true;
						}
					}
					if(bomb)
					{
						rand=(int)(Math.random()*4)+2;
						bombtempy+=rand;
						bom=new Bomb(bombtempx,bombtempy+20,50,25);
						rect8=new Rectangle2D(bombtempx,bombtempy+20,50,25);
		//				gc.fillRect(bombtempx,bombtempy+20,50,25); //Shows hitbox of bomb
						if(bombtempy+20<330)
							gc.drawImage(things.get(11),bom.getXcord(),bom.getYcord(),bom.getH(),bom.getW());
						if(bombtempy>320)
						{
							bomb=false;
							explobomber=true;
						}
					}
					if(explobomber)
					{
						exploscount2++;
						gc.drawImage(things.get(10),bom.getXcord(),bom.getYcord()+20,bom.getH(),bom.getW());
						if(exploscount2==1)
							sounds.get(6).play();
						if(exploscount2>40)
							rect8=null;
						if(exploscount2>130)
						{
							explobomber=false;
							exploscount2=0;
						}
					}
				}
				if((int)(Math.random()*1000)%100==0&&plane==false&&level==1)
				{
					rand=(int)(Math.random()*140)+50;
					fighter=new Plane(0,rand,50,25);
					sounds.get(2).play();
					gc.drawImage(things.get(3),fighter.getXcord(),fighter.getYcord(),fighter.getH(),fighter.getW());
					plane=true;
					randspeed=(int)(Math.random()*3)+2;
				}
				if(plane&&level==1)
				{
					gc.drawImage(things.get(3),fighter.getXcord()+randspeed,fighter.getYcord(),fighter.getH(),fighter.getW());
					fighter.addXcord(randspeed);
					rect3 = new Rectangle2D(fighter.getXcord()+randspeed,fighter.getYcord(),35,20);
		//			gc.fillRect(fighter.getXcord()+randspeed,fighter.getYcord(),35,20); //Shows Hitbox of plane
					if(fighter.getXcord()>799)
					{
						plane=false;
						fighter.setXcord(0);
						if(score>=50)
							score-=50;
					}
				}
				if(missle)
				{
					posit=x;
					missle2=true;
					sounds.get(0).play();
					missle=false;
				}
				if(missle2)
				{
					if(midcannon)
					{
						rocket=new Missle(182+posit,320+misslepos,60,35);
						gc.drawImage(things.get(7),rocket.getXcord(),rocket.getYcord(),rocket.getH(),rocket.getW());
						misslepos-=11;
						alreadymissle=true;
						rect4 = new Rectangle2D(198+posit,332+misslepos,10,25);
			//			gc.fillRect(198+posit,332+misslepos,10,25); //Shows Hitbox of missle
						if(320+misslepos<1||182+posit<0||182+posit>800)
						{
							missle2=false;
							misslepos=0;
							alreadymissle=false;
						}
					}
					else if(leftcannon)
					{
						rocket=new Missle(156+posit,320+misslepos,60,35);
						gc.drawImage(things.get(8),rocket.getXcord(),rocket.getYcord(),rocket.getH(),rocket.getW());
						misslepos-=8;
						posit-=8;
						rect5=new Rectangle2D(180+posit,338+misslepos,10,10);
//						gc.fillRect(180+posit,338+misslepos,10,10);	//				Shows Hitbox of missle
						alreadymissle=true;
						if(320+misslepos<1||182+posit<0||182+posit>800)
						{
							missle2=false;
							misslepos=0;
							alreadymissle=false;
						}
					}
					else if(rightcannon)
					{
						rocket=new Missle(205+posit,320+misslepos,60,35);
						gc.drawImage(things.get(9),rocket.getXcord(),rocket.getYcord(),rocket.getH(),rocket.getW());
						misslepos-=8;
						posit+=8;
						rect6=new Rectangle2D(217+posit,338+misslepos,10,10);
			//			gc.fillRect(217+posit,338+misslepos,10,10);	//				Shows Hitbox of missle
						alreadymissle=true;
						if(320+misslepos<1||182+posit<0||182+posit>800)
						{
							missle2=false;
							misslepos=0;
							alreadymissle=false;
						}
					}
				}
				if ((rect3!=null&&rect4!=null&&rect3.intersects(rect4))||(rect7!=null&&rect4!=null&&rect7.intersects(rect4))||(rect9!=null&&rect4!=null&&rect9.intersects(rect4)))//if missle hits plane
				{
					sounds.get(1).play();
					missle2=false;
					misslepos=0;
					alreadymissle=false;
					plane=false;
					napplane=false;
					if(rect7!=null&&rect4!=null&&rect7.intersects(rect4))
					{
						explotemp=tact.getXcord();
						explotemp2=tact.getYcord();
					}
					else if(rect9!=null&&rect4!=null&&rect9.intersects(rect4))
					{
						explotemp=napbomber.getXcord();
						explotemp2=napbomber.getYcord();
					}
					else
					{
						explotemp=fighter.getXcord();
						explotemp2=fighter.getYcord();
					}
					planespos=0;
					bomber=false;
					sounds.get(2).stop();
					sounds.get(5).stop();
					sounds.get(8).stop();
					explo=true;
					score+=100;
					rect3=null;
					rect7=null;
					rect4=null;
				}
				else if((rect3!=null&&rect5!=null&&rect3.intersects(rect5))||(rect7!=null&&rect5!=null&&rect7.intersects(rect5))||(rect9!=null&&rect5!=null&&rect9.intersects(rect5)))//if missle hits plane
				{
					sounds.get(1).play();
					missle2=false;
					misslepos=0;
					alreadymissle=false;
					plane=false;
					napplane=false;
					if(rect7!=null&&rect5!=null&&rect7.intersects(rect5))
					{
						explotemp=tact.getXcord();
						explotemp2=tact.getYcord();
					}
					else if(rect9!=null&&rect5!=null&&rect9.intersects(rect5))
					{
						explotemp=napbomber.getXcord();
						explotemp2=napbomber.getYcord();
					}
					else
					{
						explotemp=fighter.getXcord();
						explotemp2=fighter.getYcord();
					}
					planespos=0;
					sounds.get(2).stop();
					sounds.get(5).stop();
					sounds.get(8).stop();
					explo=true;
					bomber=false;
					rect7=null;
					score+=100;
					rect3=null;
					rect5=null;
				}
				else if((rect3!=null&&rect6!=null&&rect3.intersects(rect6))||(rect7!=null&&rect6!=null&&rect7.intersects(rect6))||(rect9!=null&&rect6!=null&&rect9.intersects(rect6)))//if missle hits plane
				{
					sounds.get(1).play();
					missle2=false;
					misslepos=0;
					alreadymissle=false;
					plane=false;
					napplane=false;
					bomber=false;
					if(rect7!=null&&rect6!=null&&rect7.intersects(rect6))
					{
						explotemp=tact.getXcord();
						explotemp2=tact.getYcord();
					}
					else if(rect9!=null&&rect6!=null&&rect9.intersects(rect6))
					{
						explotemp=napbomber.getXcord();
						explotemp2=napbomber.getYcord();
					}
					else
					{
						explotemp=fighter.getXcord();
						explotemp2=fighter.getYcord();
					}
					planespos=0;
					rect7=null;
					sounds.get(2).stop();
					sounds.get(5).stop();
					sounds.get(8).stop();
					explo=true;
					score+=100;
					rect3=null;
					rect6=null;
				}
				if(explo)
				{
					exploscount++;
					gc.drawImage(things.get(10),explotemp,explotemp2,50,25);
					if(exploscount>150)
					{
						explo=false;
						exploscount=0;
					}
				}
				if(level>0)
				{
					msg=new Text("Score: ",10,30);
					Font font= Font.font( "Arial", FontWeight.NORMAL, 28 );
					gc.setFont(font);
					gc.setFill( Color.YELLOW); //Fills the text in yellow
					gc.setStroke( Color.BLACK ); //Changes the outline the black
					gc.setLineWidth(1); //How big the black lines will be
					gc.fillText( msg.getMsg()+score,msg.getH(),msg.getW() );
					msg=new Text("Time: "+time/100.0,160,30);
					gc.setFill( Color.RED);
					gc.fillText( msg.getMsg(),msg.getH(),msg.getW() );
					msg=new Text("Level: ",330,30);
					gc.setFill( Color.CYAN);
					gc.fillText( msg.getMsg()+level,msg.getH(),msg.getW() );
					msg=new Text("Reach 1000 Points to win",450,30);
					gc.setFill( Color.GREEN);
					gc.fillText( msg.getMsg(),msg.getH(),msg.getW() );
				}
				if(level==0)
				{
					Font font= Font.font( "Arial", FontWeight.NORMAL, 28 );
					gc.setFont(font);
					gc.setFill( Color.YELLOW); //Fills the text in yellow
					gc.setStroke( Color.BLACK ); //Changes the outline the black
					msg=new Text("1. Use arrow keys to move.\n2. Shoot down the enemy planes using spacebar.\n3. Use 1, 2 and 3 to change turret angle (135) (90) (45).\n4. Watch out for bombs after level 1", 10, 25 );
					gc.fillText( msg.getMsg(),msg.getH(),msg.getW() );
					gc.setLineWidth(1); //How big the black lines will be
					font= Font.font("Arial", FontWeight.NORMAL, 54);
					gc.setFont(font);
					msg=new Text("Click here to start!",140,255);
					gc.fillText( msg.getMsg(),msg.getH(),msg.getW() );//draws the yellow part of the text
					gc.strokeText( msg.getMsg(),msg.getH(),msg.getW() );
					gc.fillRect(580,217,35,35);
					gc.setFill( Color.BLACK);
					gc.fillRect(587,225,20,20);
					gc.setFill( Color.RED);
					gc.fillRect(592,230,10,10);
				}
				if(level==3&&!level3good)
				{
					Font font= Font.font( "Arial", FontWeight.NORMAL, 28 );
					gc.setFont(font);
					gc.setFill( Color.YELLOW); //Fills the text in yellow
					gc.setStroke( Color.BLACK ); //Changes the outline the black
					msg=new Text("Final Wave! Napalm Inbound! DO NOT TOUCH THE NAPALM!", 10, 85 );
					gc.fillText( msg.getMsg(),msg.getH(),msg.getW() );
					gc.setLineWidth(1); //How big the black lines will be
					font= Font.font("Arial", FontWeight.NORMAL, 54);
					gc.setFont(font);
					msg=new Text("Click here to start!",140,255);
					gc.fillText( msg.getMsg(),msg.getH(),msg.getW() );//draws the yellow part of the text
					gc.strokeText( msg.getMsg(),msg.getH(),msg.getW() );
					gc.fillRect(580,217,35,35);
					gc.setFill( Color.BLACK);
					gc.fillRect(587,225,20,20);
					gc.setFill( Color.RED);
					time++;
					time=6000;
					gc.fillRect(592,230,10,10);
				}
				if(level==2&&!level2good)
				{
					Font font= Font.font( "Arial", FontWeight.NORMAL, 28 );
					gc.setFont(font);
					gc.setFill( Color.BLUE); //Fills the text in yellow
					gc.setStroke( Color.BLACK ); //Changes the outline the black
					gc.setLineWidth(1); //How big the black lines will be
					msg=new Text("Bombers inbound! Avoid the bombs at all costs!\nPractice shooting here if you want!",45,140);
					gc.fillText( msg.getMsg(),msg.getH(),msg.getW() );
					font= Font.font("Arial", FontWeight.NORMAL, 54);
					gc.setFont(font);
					msg=new Text("Click here to continue!",45,253);
					gc.fillText( msg.getMsg(),msg.getH(),msg.getW() );//draws the yellow part of the text
					gc.strokeText( msg.getMsg(),msg.getH(),msg.getW() );
					gc.fillRect(580,217,35,35);
					gc.setFill( Color.BLACK);
					gc.fillRect(587,225,20,20);
					gc.setFill( Color.RED);
					gc.fillRect(592,230,10,10);
					time++;
				}
				if(score==1000||score==1050)
				{
					level++;
					time=6000;
					score=0;
					for(int x=0;x<sounds.size();x++)
						sounds.get(x).stop();
				}
			}
			if(rect8!=null&rect2!=null&&rect2.intersects(rect8))  //if bomb hits tank
			{
				keypress=true;
			}
			for(int x=0;x<rects.size();x++)
			{
				if(rects.get(x)!=null&rect2!=null&&rect2.intersects(rects.get(x)))
				{
					keypress=true;
				}
			}
			if(keypress||time<=0||level>3)
			{
				Font font= Font.font( "Arial", FontWeight.NORMAL, 108 );
				gc.setFont(font); //Changes the outline the black
				gc.setLineWidth(1); //How big the black lines will be
				gc.setFill( Color.RED); //Fills the text in yellow
				gc.setStroke( Color.BLACK );
				msg=new Text("Game Over!", 130, 200 );
				gc.fillText( msg.getMsg(),msg.getH(),msg.getW() );//draws the yellow part of the text
				gc.strokeText( msg.getMsg(),msg.getH(),msg.getW() );
				font= Font.font( "Roboto", FontWeight.NORMAL, 48 );
				gc.setFont(font);
				msg=new Text("Click here to restart:", 140, 250 );
				gc.fillText( msg.getMsg(),msg.getH(),msg.getW() );//draws the yellow part of the text
				gc.strokeText( msg.getMsg(),msg.getH(),msg.getW() );
				gc.fillRect(580,217,35,35);
				if(level==3||level==2)
					level++;
				time++;
				gc.setFill( Color.BLACK);
				gc.fillRect(587,225,20,20);
				gc.setFill( Color.RED);
				gc.fillRect(592,230,10,10);
				count++;
				if(count==1)
				{
					sounds.get(7).play();
					for(int x=0;x<sounds.size();x++)
						if(x!=6&&x!=3&&x!=7)
							sounds.get(x).stop();
				}
			}
		}
	}

}
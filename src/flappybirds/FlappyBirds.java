package flappybirds;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.GameScreen;

/**
 *
 * @author phamn
 */
public class FlappyBirds extends GameScreen{
    private BufferedImage birds;
    private Animation bird_anim;
    
    private int point=0;
    public static float g = 0.16f;
    
    private Bird bird;
    private Ground ground;
    
    private ChimneyGroup chimneyGroup;
    
    private int BEGIN_SCREEN = 0;
    private int GAMEPLAY_SCREEN = 1;
    private int GAMEOVER_SCREEN = 2;
    
    private int CurrentScreen = BEGIN_SCREEN;
    
    public FlappyBirds(){
        super(800,600);
        
        try {
            birds = ImageIO.read(new File("image/bird_sprite.png"));
        } catch (IOException ex) {
        	
        }
        
        bird_anim = new Animation(70);
        AFrameOnImage f;
        f = new AFrameOnImage(0,0,60,60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(60,0,60,60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(120,0,60,60);
        bird_anim.AddFrame(f);
        f = new AFrameOnImage(60,0,60,60);
        bird_anim.AddFrame(f);
        
        bird = new Bird(350, 250, 50, 50);
        ground = new Ground();
        
        chimneyGroup = new ChimneyGroup();
        
        BeginGame();
    }
    
    
    public static void main(String[] args) {
        new FlappyBirds();
    }
    
    private void resetGame(){
        bird.setPos(350, 250);
        bird.setVt(0);
        bird.setLive(true);
        point=0;
        chimneyGroup.reset();
        
    }
    
    @Override
    public void GAME_UPDATE(long deltaTime) {
        
        if(CurrentScreen == BEGIN_SCREEN){
            resetGame();
        }else if(CurrentScreen == GAMEPLAY_SCREEN){
        	for(int i = 0;i<ChimneyGroup.SIZE;i++){
                if(bird.getRect().intersects(chimneyGroup.getChimney(i).getRect())){
                    bird.setLive(false);
                    bird.crashSound.play();
                    
                }
                    
                
            }
            if(bird.getLive()) bird_anim.Update_Me(deltaTime);
            bird.update(deltaTime);
            ground.Update();
            
            chimneyGroup.update();
            
            if(bird.getPosY() + bird.getH() > ground.getYGround())
            	{
                bird.crashSound.play();
            	CurrentScreen = GAMEOVER_SCREEN;
            	}
            
            
           for (int i = 0; i < ChimneyGroup.SIZE; i++) {
			if(bird.getPosX()>chimneyGroup.getChimney(i).getPosX() && 
					 !chimneyGroup.getChimney(i).getIsBehindBird() 
					 && i % 2 ==0)
			{
				point+=1;
				bird.getPointSound.play();
				chimneyGroup.getChimney(i).setIsBehindBird(true);
			}
			
		}
        }else{
            
        }
        
    }

    @Override
    public void GAME_PAINT(Graphics2D g2) {
    	g2.setColor(Color.decode("#b8daef"));
    	g2.fillRect(0, 0, MASTER_WIDTH, MASTER_WIDTH);
    	  Font font =new Font("Arial", Font.BOLD, 30);
        
        chimneyGroup.paint(g2);
        
        ground.Paint(g2);
        
        
        
        if(bird.getIsFlying())
            bird_anim.PaintAnims(
            		(int) bird.getPosX()
            		, (int) bird.getPosY()
            		, birds
            		, g2
            		, 0
            		, -1);
        else 
            bird_anim.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birds, g2, 0, 0);
        
        
        
        if(CurrentScreen == BEGIN_SCREEN){
            g2.setColor(Color.WHITE);
            Font font1 =new Font("Arial", Font.BOLD, 35);
            g2.setFont(font1);
            g2.drawString("Press Space To Play Game", 250, 300);
        }
        if(CurrentScreen == GAMEOVER_SCREEN){
            g2.setColor(Color.black);
            g2.setFont(font);
            g2.setColor(Color.WHITE);
            g2.drawString("GAME OVER !!", 250, 250);
            g2.setColor(Color.WHITE);
            g2.drawString("Point :"+point, 250, 300);
            g2.drawString("Press Space To Play Again ", 250, 350);
            
        }
        g2.setFont(font);
        g2.setColor(Color.BLUE);
        g2.drawString("point :"+point,20,50);
    }

    public int getPoint() {
		return point;
	}


	public void setPoint(int point) {
		this.point = point;
	}


	public static float getG() {
		return g;
	}


	public static void setG(float g) {
		FlappyBirds.g = g;
	}


	@Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if(Event == KEY_PRESSED){
            
            if(CurrentScreen == BEGIN_SCREEN){
                
                CurrentScreen = GAMEPLAY_SCREEN;
                
            }else if(CurrentScreen == GAMEPLAY_SCREEN){
                if(bird.getLive()) bird.fly();
            }else if(CurrentScreen == GAMEOVER_SCREEN){
                CurrentScreen = BEGIN_SCREEN;
            }
        }
    }
    
}

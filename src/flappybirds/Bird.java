package flappybirds;


import java.awt.Rectangle;
import java.io.File;

import pkg2dgamesframework.Objects;
import pkg2dgamesframework.SoundPlayer;

/**
 *
 * @author phamn
 */
public class Bird extends Objects{
    
    private float vt = 0;
    
    private boolean isFlying = false;
public SoundPlayer flapSound,crashSound,getPointSound;
    private Rectangle rect;
     
    private boolean isLive = true;
    
    public Bird(int x, int y, int w, int h){
        super(x, y, w, h);  
        rect = new Rectangle(x, y, w, h);
        flapSound=new SoundPlayer(new File("image/fap.wav"));
        crashSound=new SoundPlayer(new File("image/fall.wav"));
        getPointSound=new SoundPlayer(new File("image/getpoint.wav"));

    }
    
    public void setLive(boolean b){
        isLive = b;
    }
    
    public boolean getLive(){
        return isLive;
    }
    public Rectangle getRect(){
        return rect;
    }
    
    public void setVt(float vt){
        this.vt = vt;
    }
    public float getVt() {
		return vt;
	}

	public void update(long deltaTime){
        
        vt+=FlappyBirds.g;
        
        this.setPosY(this.getPosY()+vt);
        this.rect.setLocation((int) this.getPosX(),(int) this.getPosY());
        
        if(vt < 0) isFlying = true;
        else isFlying = false;
        
    }
    
    public void fly(){
        vt =-4.4f;
        flapSound.play();
    }
    
    
    public boolean getIsFlying(){
        return isFlying;
    }
}

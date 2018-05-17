package flappybirds;

import java.awt.Rectangle;
import pkg2dgamesframework.Objects;

/**
 *
 * @author phamn
 */
public class Chimney extends Objects{
    
    private Rectangle rect;
    private Boolean IsBehindBird=false;
    public Chimney(int x, int y, int w, int h){
        super(x, y, w, h);
        rect = new Rectangle(x, y, w, h);
    }
    public Boolean getIsBehindBird() {
		return IsBehindBird;
	}
	public void setIsBehindBird(Boolean isBehindBird) {
		IsBehindBird = isBehindBird;
	}
	public void update(){
        setPosX(getPosX()-3);
        rect.setLocation((int) this.getPosX(), (int) this.getPosY());
    }
    
    public Rectangle getRect(){
        return rect;
    }
}

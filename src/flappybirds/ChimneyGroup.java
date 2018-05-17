package flappybirds;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import pkg2dgamesframework.QueueList;

/**
 *
 * @author phamn
 */
public class ChimneyGroup {
    
    private QueueList<Chimney> chimneys;
    
    private BufferedImage chimneyImage, chimneyImage2;
    
    public static int SIZE = 6;
    
    public Chimney getChimney(int i){
        return chimneys.get(i);
    }
    public ChimneyGroup(){
        
        try {
            
            chimneyImage = ImageIO.read(new File("image/chimney.png"));
            chimneyImage2 = ImageIO.read(new File("image/chimney_.png"));
        } catch (IOException ex) {}
        
chimneys = new QueueList<Chimney>();
        
        Chimney cn;
        
        for(int i = 0; i< SIZE/2;i++){
            int a=get_Radom();
            cn = new Chimney(830+i*300, 200+a, 74, 400);
            chimneys.push(cn);
            
            cn = new Chimney(830+ i*300, -350+a, 74, 400);
            chimneys.push(cn);
    }
    }
    public void reset()
    {
chimneys = new QueueList<Chimney>();
        
        Chimney cn;
        
        for(int i = 0; i< SIZE/2;i++){
            int a=get_Radom();
            cn = new Chimney(830+i*300, 200+a, 74, 400);
            chimneys.push(cn);
            
            cn = new Chimney(830+i*300, -350+a, 74, 400);
            chimneys.push(cn);
    }
    }
    public void update(){
        for(int i = 0;i < SIZE; i++){
            chimneys.get(i).update();
        }
        

        if(chimneys.get(0).getPosX()<-74) {
            Chimney cn;
            int a=get_Radom();
          
            cn = chimneys.pop();
            cn.setPosX(chimneys.get(4).getPosX() + 300);
            cn.setPosY(200+a);
            cn.setIsBehindBird(false);

            chimneys.push(cn);
            
            cn = chimneys.pop();
            cn.setPosX(chimneys.get(4).getPosX());
            cn.setPosY(-350+a);
            cn.setIsBehindBird(false);
            chimneys.push(cn);
        }
        
    }
    public int get_Radom()
    {
    	Random radom=new Random();
    	int a =radom.nextInt(10);
    	return a * 35;
    }
    public void paint(Graphics2D g2){
        for(int i = 0;i < 6; i++)
            if(i%2==0)
                g2.drawImage(chimneyImage, (int )chimneys.get(i).getPosX(), (int)chimneys.get(i).getPosY(), null);
            else g2.drawImage(chimneyImage2, (int )chimneys.get(i).getPosX(), (int)chimneys.get(i).getPosY(), null);
    }
}

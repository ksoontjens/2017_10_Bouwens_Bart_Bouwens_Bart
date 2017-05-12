/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.Timer;
import org.bluray.ui.event.HRcEvent;
import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.dvb.ui.DVBColor;
import org.havi.ui.HComponent;

/**
 *
 * @author student
 */
public class MijnComponent extends HComponent implements UserEventListener {
    Image schip, sterren;
    int x = 350;
    int y = 0;
public MijnComponent (int x1, int y1, int x2, int y2)
{
    
    this.setBounds(x1, y1, x2, y2);
    schip=this.getToolkit().getImage("spaceship.png");
    sterren=this.getToolkit().getImage("sterren.png");
    MediaTracker mt = new MediaTracker(this);
    mt.addImage(schip, 1);
    mt.addImage(sterren, 2);
    try {
        mt.waitForAll();
    }
    catch(InterruptedException ex){
        ex.printStackTrace();
    }
    UserEventRepository repo = new UserEventRepository("repo");
    repo.addAllArrowKeys();
    EventManager manager = EventManager.getInstance();
    manager.addUserEventListener(this, repo);
    Timer t = new Timer();
    MijnTimerTask mtt = new MijnTimerTask();
    mtt.setCallback(this);
    t.scheduleAtFixedRate(mtt, 0, 10);
}
public void callback()
{
        y++; 
        if(y>570)
        {
            y=0;
        }
        this.repaint();
}
    public void paint(Graphics g)
    {   g.drawImage(sterren, 0,y,null);
        g.drawImage(sterren , 0, y-570, null);
        g.drawImage(schip, x, 500, null);
        
     
        //g.setColor(new DVBColor(0,0,255,127));
        //g.fillRoundRect(0, 0, 200, 100, 15, 15);
        //g.setColor(new DVBColor(255,255,0, 255));
        //g.drawString("Dit is een tekst", 20, 40);
    }

    public void userEventReceived(UserEvent e) {
        if(e.getType()==HRcEvent.KEY_PRESSED)
        {
        if(e.getCode()==HRcEvent.VK_LEFT){x--; this.repaint();}
        if(e.getCode()==HRcEvent.VK_RIGHT){x++; this.repaint();}
        }
    }
}

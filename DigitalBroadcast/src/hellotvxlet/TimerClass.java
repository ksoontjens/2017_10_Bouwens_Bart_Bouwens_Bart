/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.util.TimerTask;
import org.havi.ui.HState;

/**
 *
 * @author student
 */
public class TimerClass  extends TimerTask{
    HelloTVXlet main ;
    
    public TimerClass(HelloTVXlet h)
    {
        main=h;
    }
    
    int remainingTime = 10;
    
    public void run()
    {
        System.out.println("remaining time="+remainingTime);
        if (remainingTime > 0)
        {
            remainingTime--;
            main.timer.setTextContent(String.valueOf(remainingTime), HState.NORMAL_STATE);
            main.timer.repaint();
        }
        
        else
        {
            main.askQuestions();
            remainingTime = 10;
        }
    }
            
}

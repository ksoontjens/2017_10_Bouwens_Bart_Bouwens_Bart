package hellotvxlet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.tv.xlet.*;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HState;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;
import java.util.Timer;



public class HelloTVXlet implements Xlet, HActionListener {
    HScene scene;
    HStaticText label1;
    
    HTextButton knop1;
    HTextButton knop2;
    HTextButton knop3;
    HTextButton knop4;
    HStaticText currentScoreHolder;
    HStaticText timer;
    int currentscore = 0;
    
    String[] buttonNames = {"knop1", "knop2", "knop3", "knop4"};
    
    int index = 0;
    
    String[] vragen = new String[]{"Who was The First President of the United States?","Which was an actual historic event in Australia?", "After what animal are the Canary Islands named?", "Where can you find the Sea Of tranquility?"};
    
    String[] antwoorden1 = new String[]{"George Washington","The Aboriginal War", "A Canary", "In A Volcanoe"};
    String[] antwoorden2 = new String[]{"Bill Clinton","The Emu War", "A type of butterfly", "In The Gulf Of Mexico"};
    String[] antwoorden3 = new String[]{"Donald Duck","The Battle Of Melbourne", "A Dog", "In Georgia"};
    String[] antwoorden4 = new String[]{"Ronald Reagan","Mad Max", "A Parrot", "On The Moon", "On The Moon"};
    
    int[] rightAnswer = new int[]{1, 2, 3, 4, 1, 1,1,1};
    
    String juisteAntwoord;
    
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) {
     scene =  HSceneFactory.getInstance().getDefaultHScene();
     scene.validate();
     currentScoreHolder = new HStaticText("0", 0, 0, 20, 20);
     currentScoreHolder.setBackground(Color.cyan);
     currentScoreHolder.setBackgroundMode(HVisible.BACKGROUND_FILL);
     timer = new HStaticText("0", 650, 0, 30, 20);
     timer.setBackground(Color.cyan);
     timer.setBackgroundMode(HVisible.BACKGROUND_FILL);
     label1 = new HStaticText("Klaar om te beginnen?", 20, 100, 680, 100);
     label1.setBackground(Color.blue);
     label1.setBackgroundMode(HVisible.BACKGROUND_FILL);

     knop1 = new HTextButton("Ja", 50 , 250, 300, 100);
     knop1.setBackground(Color.blue);
     knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
     knop1.setName(buttonNames[0]);

     knop2 = new HTextButton("Ja", 350 , 250, 300, 100);
     knop2.setBackground(Color.blue);
     knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
     knop1.setName(buttonNames[1]);

     knop3 = new HTextButton("Ja", 50 , 400, 300, 100);
     knop3.setBackground(Color.blue);
     knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
     knop1.setName(buttonNames[2]);

     knop4 = new HTextButton("Ja", 350 , 400, 300, 100);
     knop4.setBackground(Color.blue);
     knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
     knop1.setName(buttonNames[3]);

     knop1.setFocusTraversal(null, knop3, null, knop2);
     knop2.setFocusTraversal(null, knop4, knop1, null);
     knop3.setFocusTraversal(knop1, null, null, knop4);
     knop4.setFocusTraversal(knop2, null, knop3, null);

     knop1.setActionCommand(Integer.toString(1));
     knop2.setActionCommand(Integer.toString(2));
     knop3.setActionCommand(Integer.toString(3));
     knop4.setActionCommand(Integer.toString(4));
     knop1.addHActionListener(this);
     knop2.addHActionListener(this);
     knop3.addHActionListener(this);
     knop4.addHActionListener(this);

     scene.add(label1);
     scene.add(knop1);
     scene.add(knop2);
     scene.add(knop3);
     scene.add(knop4);
     scene.add(currentScoreHolder);
     scene.add(timer);

     scene.validate();
     scene.setVisible(true);
     knop1.requestFocus();
     
     
     
    }

    public void startXlet()
    {
        TimerClass myTimeClass = new TimerClass(this);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(myTimeClass, 1000 ,1000);
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }

    public void actionPerformed(ActionEvent arg0) {
        //HStaticText msg = new HStaticText("msg", 300, 10, 100, 100);
        //msg.setBackground(Color.red);
        //msg.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        if(arg0.getActionCommand() == (juisteAntwoord))
        {
            //msg.setTextContent("Goed", HVisible.NORMAL_STATE);
            //msg.setBackground(Color.GREEN);
            currentscore++;
            currentScoreHolder.setTextContent(Integer.toString(currentscore), HState.NORMAL_STATE);
            askQuestions();
            
        }
        else
        {
            //msg.setTextContent("FOUT", HVisible.NORMAL_STATE);
            askQuestions();
            
        }
        //scene.add(msg);
        //scene.popToFront(msg);
            
        
        scene.repaint();
    }
    
    public void askQuestions()
    {
        //Timer resetten
        
        //zet antwoord bij juiste knop
        label1.setTextContent(vragen[index], HState.NORMAL_STATE);
        knop1.setTextContent(antwoorden1[index], HState.NORMAL_STATE);
        knop2.setTextContent(antwoorden2[index], HState.NORMAL_STATE);
        knop3.setTextContent(antwoorden3[index], HState.NORMAL_STATE);
        knop4.setTextContent(antwoorden4[index], HState.NORMAL_STATE);
        
        juisteAntwoord = Integer.toString(rightAnswer[index]);
        index++;
        
        //set callback name
        
    }
}


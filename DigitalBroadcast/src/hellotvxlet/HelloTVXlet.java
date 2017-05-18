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


public class HelloTVXlet implements Xlet, HActionListener {
    HScene scene;
    HStaticText label1;
    
    HTextButton knop1;
    HTextButton knop2;
    HTextButton knop3;
    HTextButton knop4;
    
    String[] buttonNames = {"knop1", "knop2", "knop3", "knop4"};
    
    int index = 0;
    
    String[] vragen = new String[]{"is jan hay", "Wie danst?", "Wat is de realisteit", "besta ik?", "Oegaboega"};
    
    String[] antwoorden1 = new String[]{"ja", "Quinten", "iets", "misschien", "Ting tang"};
    String[] antwoorden2 = new String[]{"ja", "Quinten", "iets", "misschien", "Ting tang"};
    String[] antwoorden3 = new String[]{"ja", "Quinten", "iets", "misschien", "Ting tang"};
    String[] antwoorden4 = new String[]{"ja", "Quinten", "iets", "misschien", "Ting tang"};
    
    int[] rightAnswer = new int[]{1, 2, 3, 4, 1};
    
    String juisteAntwoord;
    
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) {
     scene =  HSceneFactory.getInstance().getDefaultHScene();
     scene.validate();
     label1 = new HStaticText("Wie was de eerste president van de VS?", 20, 100, 680, 100);
     label1.setBackground(Color.blue);
     label1.setBackgroundMode(HVisible.BACKGROUND_FILL);

     knop1 = new HTextButton("Bill Clinton", 50 , 250, 200, 100);
     knop1.setBackground(Color.blue);
     knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
     knop1.setName(buttonNames[0]);

     knop2 = new HTextButton("Ronald Reagan", 350 , 250, 200, 100);
     knop2.setBackground(Color.blue);
     knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
     knop1.setName(buttonNames[1]);

     knop3 = new HTextButton("Donald Duck", 50 , 400, 200, 100);
     knop3.setBackground(Color.blue);
     knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
     knop1.setName(buttonNames[2]);

     knop4 = new HTextButton("George Washington", 350 , 400, 200, 100);
     knop4.setBackground(Color.blue);
     knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
     knop1.setName(buttonNames[3]);

     knop1.setFocusTraversal(null, knop3, null, knop2);
     knop2.setFocusTraversal(null, knop4, knop1, null);
     knop3.setFocusTraversal(knop1, null, null, knop4);
     knop4.setFocusTraversal(knop2, null, knop3, null);

     knop1.setActionCommand(knop1.getName());
     knop2.setActionCommand(knop2.getName());
     knop3.setActionCommand(knop3.getName());
     knop4.setActionCommand(knop4.getName());
     knop1.addHActionListener(this);
     knop2.addHActionListener(this);
     knop3.addHActionListener(this);
     knop4.addHActionListener(this);

     scene.add(label1);
     scene.add(knop1);
     scene.add(knop2);
     scene.add(knop3);
     scene.add(knop4);

     scene.validate();
     scene.setVisible(true);
     knop1.requestFocus();
     
     askQuestions();
     
    }

    public void startXlet() {
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }

    public void actionPerformed(ActionEvent arg0) {
        HStaticText msg = new HStaticText("msg", 300, 10, 100, 100);
        msg.setBackground(Color.red);
        msg.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        if(arg0.getActionCommand() == (juisteAntwoord))
        {
            msg.setTextContent("Goed", HVisible.NORMAL_STATE);
            msg.setBackground(Color.GREEN);
        }
        else
        {
            msg.setTextContent("FOUT", HVisible.NORMAL_STATE);
        }
        scene.add(msg);
        scene.popToFront(msg);
        askQuestions();
        index++;
        scene.repaint();
    }
    
    public void askQuestions()
    {
        //zet antwoord bij juiste knop
        label1.setTextContent(vragen[index], HState.NORMAL_STATE);
        knop1.setTextContent(antwoorden1[index], HState.NORMAL_STATE);
        knop2.setTextContent(antwoorden2[index], HState.NORMAL_STATE);
        knop3.setTextContent(antwoorden3[index], HState.NORMAL_STATE);
        knop4.setTextContent(antwoorden4[index], HState.NORMAL_STATE);
        
        //set callback name
        juisteAntwoord = buttonNames[rightAnswer[index]];
    }
}


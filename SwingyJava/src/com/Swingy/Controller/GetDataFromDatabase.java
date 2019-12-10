package com.Swingy.Controller;

import com.Swingy.Controller.Heros.HeroDetails;
import com.Swingy.Controller.Heros.HeroStart;
import com.Swingy.Model.HeroStats;
import com.Swingy.View.Display;
import com.Swingy.View.FeleletsaPapadi.FeleletsaPapadi;
import com.Swingy.View.LetlakalaLaGoRaloka.LetlakalaLaGoRaloka;
import com.Swingy.View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class GetDataFromDatabase implements ActionListener {
    private JButton _continue;
    private HeroStats _heroModel;

    public GetDataFromDatabase()
    {
        _heroModel = new HeroStats();
        _continue = FeleletsaPapadi.getContinueButton();
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clicked;
        int selection = 1;
        ResultSet heroDetails;
        clicked = (JButton)e.getSource();

        if (clicked.getToolTipText() == _continue.getToolTipText())
        {
            if(!FeleletsaPapadi.getTextField().getText().isEmpty())
            {
                try {
                    selection = Integer.parseInt(FeleletsaPapadi.getTextField().getText());

                    if (selection >= 1 && selection <= FeleletsaPapadi.getTable().getRowCount())
                    {
                        heroDetails = _heroModel.getSingleHero(selection);

                        MainFrame.getInstance().hidePanel(MainFrame.getInstance().getPanel());
                        Display _display = new LetlakalaLaGoRaloka();

                        MainFrame.getInstance().addPanel(_display.getDisplay());
                        MainFrame.getInstance().getFrame().setTitle(_display.getTitle());

                        _display.gameControlButtonsListener(new PapadiEGolo(HeroStart.newHero(heroDetails.getString(2), heroDetails.getString(3),
                                    heroDetails.getInt(5), heroDetails.getInt(7), heroDetails.getInt(6), heroDetails.getInt(8),
                                    heroDetails.getInt(9), heroDetails.getInt(10), heroDetails.getInt(11), heroDetails.getString(4)), "Gui", selection));
                        _heroModel.closeConnection();
                    } else {
                        JOptionPane.showMessageDialog(null, "Are you blind my nigga!!");
                    }
                } catch (Exception num)
                {
                    System.out.println(num);
                    JOptionPane.showMessageDialog(null, "Try making the right selection please");
                }

                System.out.println("We made it "+selection+ " "+ FeleletsaPapadi.getTable().getRowCount());
            }
        }
    }
}

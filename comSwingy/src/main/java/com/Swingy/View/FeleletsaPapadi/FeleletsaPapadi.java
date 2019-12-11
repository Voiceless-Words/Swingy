package com.Swingy.View.FeleletsaPapadi;

import com.Swingy.Model.HeroStats;
import com.Swingy.View.Display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FeleletsaPapadi extends JPanel implements Display {
    private static JTable _saveHeroes;
    private static JTextField _selectHeroField;
    private static JButton _continueButton;
    private HeroStats _saveHero;

    public FeleletsaPapadi(){}

    @Override
    public JPanel getDisplay() {
        setLayout(new BorderLayout());
        setLayout(new BorderLayout());//setting the Layout

        _saveHero = new HeroStats();
        _saveHeroes = _saveHero.fetchHeroes();
        _selectHeroField = new JTextField(20);
        _selectHeroField.setToolTipText("Enter the hero you want to select!!");

        _continueButton = new JButton("Continue");
        _continueButton.setToolTipText("Continue_save");

        this.add(_continueButton, BorderLayout.SOUTH);
        this.add(_selectHeroField, BorderLayout.BEFORE_FIRST_LINE);
        this.add(_saveHeroes,BorderLayout.CENTER);

        return this;
    }

    @Override
    public String getTitle() {
        return "Continue Saved Game";
    }

    @Override
    public void homeButtonsListener(ActionListener event) {
        _continueButton.addActionListener(event);
    }

    @Override
    public void gameControlButtonsListener(ActionListener event) {

    }

    public  static  JButton getContinueButton()
    {
        return _continueButton;
    }

    public  static JTextField getTextField()
    {
        return _selectHeroField;
    }

    public static JTable getTable()
    {
        return _saveHeroes;
    }
}

package com.Swingy.Controller;

import com.Swingy.Model.HeroStats;
import com.Swingy.View.Display;
import com.Swingy.View.MainFrame;
import com.Swingy.View.Console.ConsoleLoop;
import com.Swingy.View.LetlakalaLaGae.LetlakalaLaGae;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class MainController {

    public MainController(String[] args) {
        Display display;

        if (args.length == 1) {
            String platformPlay = args[0];
            if (platformPlay.toLowerCase().equals("console")) {

                new ConsoleLoop();
                new HeroStats();
            } else if (platformPlay.toLowerCase().equals("gui")) {
                display = new LetlakalaLaGae();
                MainFrame.getInstance().addPanel(display.getDisplay());
                MainFrame.getInstance().getFrame().setTitle(display.getTitle());
                display.homeButtonsListener((new LaGaeControls()));
            }

        }else {
            System.out.println("Please Pass gui or console");
        }
    }
}
package com.Swingy.Controller;

import com.Swingy.Controller.Heros.HeroStart;
import com.Swingy.Model.HeroStats;
import com.Swingy.View.LetlakalaLaGoRaloka.LetlakalaLaGoRaloka;

import java.sql.ResultSet;

public class ConsoleActionListener {
    private static PapadiEGolo _gamePlayInstance;
    public ConsoleActionListener(String name, String classType, int level, int attack, int exp, int defense, int HP, String val, int contNew)
    {
        new LetlakalaLaGoRaloka();
        _gamePlayInstance = new PapadiEGolo(HeroStart.newHero(name, classType, level, attack, exp, defense, HP, 0, 0, val), "Console", contNew);

    }

    public static  PapadiEGolo get_gamePlayInstance()
    {
        return _gamePlayInstance;
    }

    public static ResultSet contGame()
    {
        HeroStats _hero = new HeroStats();
        return _hero.getAllHero();
    }
}

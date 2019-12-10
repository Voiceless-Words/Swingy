package com.Swingy.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.Swingy.Controller.Heros.HeroDetails;
import com.Swingy.Model.HeroStats;
import com.Swingy.View.HeroInformation;
import com.Swingy.View.MainFrame;
import com.Swingy.View.GameOver.GameOver;
import com.Swingy.View.LetlakalaLaGoRaloka.GamePageTextArea;
import com.Swingy.View.LetlakalaLaGoRaloka.GamePlayButtons;
import com.Swingy.View.LetlakalaLaGoRaloka.LetlakalaLaGoRaloka;
import com.Swingy.View.LetlakalaLaGoRaloka.StatsDisplay;

public class PapadiEGolo implements ActionListener {

    private GamePlayButtons _gamePlayButtons;
    private HeroInformation _heroInformation;
    private HeroDetails _detailsOfHero;
    private HashMap<String,Integer> _enemies;
    private List<Map<String, Integer>> _enemyArray;
    private HeroStats saveHero;
    private GamePageTextArea _gamePlayTextArea;
    private int	_mapSize;
    private int _xCoordinate;
    private int _yCoordinate;
    private int _enemyHere;

    public PapadiEGolo(HeroDetails newHero, String envGame, int contNew)
    {
        this._enemyHere = 0;
        this._detailsOfHero = newHero;
        if (envGame.toLowerCase().equals("gui")) {
            this._gamePlayTextArea = new GamePageTextArea();
            this._gamePlayButtons = new GamePlayButtons();
        }
        this.saveHero = new HeroStats();
        _heroInformation = LetlakalaLaGoRaloka.getHeroInfo();
        if(contNew > 0)
        {
            if(envGame.equals("Console"))
            {
                System.out.println(_detailsOfHero.get_HeroStatements());

            }else{
                _heroInformation.displayInformation(_detailsOfHero.get_HeroStatements(), envGame);
            }

            _detailsOfHero.set_HeroID(contNew);
            //this.saveHero.updateHero(newHero);
        } else {
            this.saveHero.addNewHero(newHero);
        }
        this.DisplayStats(envGame);
        this.CreateMap(this._detailsOfHero.get_HeroLevel());
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {

        JButton clicked;

        clicked = (JButton)e.getSource();

        if(_gamePlayButtons.get_upImg().getToolTipText() == clicked.getToolTipText())
        {
            _heroInformation.displayInformation("Moving up", "Gui");
            this.ChangeLocationOfHero("up", "Gui");
        }else if (_gamePlayButtons.get_downImg().getToolTipText() == clicked.getToolTipText())
        {
            _heroInformation.displayInformation("Moving down", "Gui");
            this.ChangeLocationOfHero("down", "Gui");
            _gamePlayButtons.get_runButton().setEnabled(false);
        }else if (_gamePlayButtons.get_leftImg().getToolTipText() == clicked.getToolTipText())
        {
            _heroInformation.displayInformation("Moving left", "Gui");
            this.ChangeLocationOfHero("left", "Gui");
        }else if (_gamePlayButtons.get_rightImg().getToolTipText() == clicked.getToolTipText())
        {
            _heroInformation.displayInformation("Moving right", "Gui");
            this.ChangeLocationOfHero("right", "Gui");
        }else if (_gamePlayButtons.get_runButton().getToolTipText() == clicked.getToolTipText())
        {
            if (this._enemyHere == 0)
            {
                _heroInformation.displayInformation("What are you running away from??", "Gui");
            } else {
                _heroInformation.displayInformation("Runaway? Can you make it?", "Gui");
                this.RunAway("Gui");
                this._enemyHere = 0;
            }
        }else if (_gamePlayButtons.get_fightButton().getToolTipText() == clicked.getToolTipText())
        {
            if (this._enemyHere == 0)
            {
                _heroInformation.displayInformation("You are wasting power.", "Gui");
            } else {
                _heroInformation.displayInformation("It's about to go down", "Gui");
                this.FightEnemy("Gui");
                this._enemyHere = 0;
            }
        }else
        {
            System.out.println("Nothing happening yet");
        }

    }

    public void consoleMoveSelection(String selection)
    {
        if(selection.equals("1"))
        {
            System.out.println("Moving up");
            this.ChangeLocationOfHero("up", "Console");
            return;
        }else if (selection.equals("2"))
        {
            System.out.println("Moving down");
            this.ChangeLocationOfHero("down", "Console");
            return;
        }else if (selection.equals("3"))
        {
            System.out.println("Moving left");
            this.ChangeLocationOfHero("left", "Console");
            return;
        }else if (selection.equals("4"))
        {
            System.out.println("Moving right");
            this.ChangeLocationOfHero("right", "Console");
            return;
        }else if (selection.equals("5"))
        {
            if (this._enemyHere == 0)
            {
                System.out.println("What are you running away from??");
            } else {
                System.out.println("Runaway? Can you make it?");
                this.RunAway("Console");
                this._enemyHere = 0;
            }
            return;
        }else if (selection.equals("6"))
        {
            if (this._enemyHere == 0)
            {
                System.out.println("You are wasting power.");
            } else {
                System.out.println("It's about to go down");
                this.FightEnemy("Console");
                this._enemyHere = 0;
            }
            return;
        }else
        {
            System.out.println("Make the right Selection");
            return;
        }

    }

    public void DisplayStats(String envGame)
    {
        _detailsOfHero.set_HeroRow(_yCoordinate);
        _detailsOfHero.set_HeroCol(_xCoordinate);
        if (envGame == "Console")
        {
            System.out.println("Name: "+_detailsOfHero.get_HeroName()+ "\nType: "+_detailsOfHero.get_HeroClass()+"\nLevel: "+_detailsOfHero.get_HeroLevel()+
                    "\nAttack: "+_detailsOfHero.get_HeroAttack()+"\nExperience: "+_detailsOfHero.get_HeroExp()+"\nDefense: "+_detailsOfHero.get_HeroDefense()+
                    "\nHealth Points: "+_detailsOfHero.get_HeroHP() + "\nHeroID: " + _detailsOfHero.get_HeroID());

        }else {
            _detailsOfHero.set_HeroStatements(LetlakalaLaGoRaloka.getAreaText());
            StatsDisplay.get_statsField().setText("Name: "+_detailsOfHero.get_HeroName()+" Type: " +_detailsOfHero.get_HeroClass()+
                    " Lv: "+_detailsOfHero.get_HeroLevel()+" Atck: "+_detailsOfHero.get_HeroAttack()+" Exp: "+_detailsOfHero.get_HeroExp()+
                    " Dfns: "+_detailsOfHero.get_HeroDefense()+" HP: "+_detailsOfHero.get_HeroHP());
        }
        this.saveHero.updateHero(_detailsOfHero);

    }

    public void CreateMap(int level)
    {

        this._mapSize = (level- 1)*5 + 10 - (level%2);
        this._xCoordinate = this._mapSize/2;
        this._yCoordinate = this._mapSize/2;
        this.CreateEnemies();
    }

    public void CreateEnemies()
    {
        Random randomCoordinate = new Random();

        this._enemyArray = new ArrayList<Map<String, Integer>>(this._detailsOfHero.get_HeroLevel() + 1);
        for(int i = 0; i < this._detailsOfHero.get_HeroLevel() + 1; i++)
        {
            int j = 0;
            if (this._detailsOfHero.get_HeroLevel() > 5)
            {
                j = randomCoordinate.nextInt((5 - 1) + 1) + 1;
            } else {
                j = randomCoordinate.nextInt(2) + 1;
            }

            this._enemies = new HashMap<>(this._detailsOfHero.get_HeroLevel() + 1);
            this._enemies.put("Enemy" + Integer.toString(i + 1), i + 1);
            this._enemies.put("Enemy" + Integer.toString(i + 1) + "HP", 60);
            this._enemies.put("Enemy" + Integer.toString(i + 1) + "Defense", 25);
            this._enemies.put("Enemy" + Integer.toString(i + 1) + "Weapon", j);
            this._enemies.put("Enemy" + Integer.toString(i + 1)+"xCoordinate", randomCoordinate.nextInt(this._mapSize));
            this._enemies.put("Enemy" + Integer.toString(i + 1)+"yCoordinate", randomCoordinate.nextInt(this._mapSize));
            this._enemyArray.add(this._enemies);
        }
    }


    public void LevelingUp(String envGame)
    {
        this.CheckEnemyEncounter(envGame);
        double xpPoints = (this._detailsOfHero.get_HeroLevel() + 1)*1000 + Math.sqrt(((this._detailsOfHero.get_HeroLevel() + 1) - 1)*450);
        if (xpPoints <= this._detailsOfHero.get_HeroExp())
        {
            this.CreateMap(this._detailsOfHero.get_HeroLevel() + 1);
            this._detailsOfHero.set_HeroLevel(this._detailsOfHero.get_HeroLevel() + 1);
            this._heroInformation.displayInformation("Congratulations you just leveled up!!!", envGame);
            JOptionPane.showMessageDialog(null, "You leveling up!!! Yeepy!!!");
            this.DisplayStats(envGame);
        }
    }

    public void ChangeLocationOfHero(String directionOfHero, String envGame)
    {
        if (directionOfHero.equals("up"))
        {
            this._yCoordinate--;
            this._detailsOfHero.set_HeroExp(this._detailsOfHero.get_HeroExp() + 10);
            this.DisplayStats(envGame);
            if (this._yCoordinate < 0)
            {
                this.LevelingUp(envGame);
                this.CreateMap(this._detailsOfHero.get_HeroLevel());
            }
            this.LevelingUp(envGame);
        }else if (directionOfHero.equals("down"))
        {
            this._yCoordinate++;
            this._detailsOfHero.set_HeroExp(this._detailsOfHero.get_HeroExp() + 10);
            this.DisplayStats(envGame);
            if (this._yCoordinate >= this._mapSize)
            {
                this.LevelingUp(envGame);
                this.CreateMap(this._detailsOfHero.get_HeroLevel());
            }
            this.LevelingUp(envGame);
        }else if (directionOfHero.equals("left"))
        {
            this._xCoordinate--;
            this._detailsOfHero.set_HeroExp(this._detailsOfHero.get_HeroExp() + 10);
            this.DisplayStats(envGame);
            if (this._xCoordinate < 0)
            {
                this.LevelingUp(envGame);
                this.CreateMap(this._detailsOfHero.get_HeroLevel());
            }
            this.LevelingUp(envGame);
        }else if (directionOfHero.equals("right"))
        {
            this._xCoordinate++;
            this._detailsOfHero.set_HeroExp(this._detailsOfHero.get_HeroExp() + 10);
            this.DisplayStats(envGame);
            if (this._xCoordinate >= this._mapSize)
            {
                this.LevelingUp(envGame);
                this.CreateMap(this._detailsOfHero.get_HeroLevel());
            }
            this.LevelingUp(envGame);
        }
    }

    public void CheckEnemyEncounter(String envGame)
    {
        int i = 0;
        for(Map<String, Integer> map : this._enemyArray)
        {
            if (map.get("Enemy" + Integer.toString(i + 1)+"yCoordinate") == this._yCoordinate
                    && map.get("Enemy" + Integer.toString(i + 1)+"xCoordinate") == this._xCoordinate)
            {
                this._enemyHere = i + 1;
                if (envGame.toLowerCase().equals("gui")) {
                    JOptionPane.showMessageDialog(null, "Enemy is here!!!");
                } else {
                    System.out.println("The Enemy Is Here!!!!!!!!!!");
                }
            }
            i++;
        }
    }

    public void FightEnemy(String envGame)
    {
        Random random = new Random();
        Map<String, Integer> map = this._enemyArray.get(_enemyHere - 1);
        int enemyHP = map.get("Enemy" + Integer.toString(this._enemyHere) + "HP");
        int enemyW = map.get("Enemy" + Integer.toString(this._enemyHere) + "Weapon");
        while(enemyHP > 0)
        {
            int randomNum = random.nextInt(5);

            if (randomNum%2 == 0)
            {
                if (enemyW == 1)
                {
                    this._detailsOfHero.set_HeroDefense(this._detailsOfHero.get_HeroDefense() - 5);
                    if (this._detailsOfHero.get_HeroDefense() < 10 && this._detailsOfHero.get_HeroDefense() > 0)
                    {
                        this._detailsOfHero.set_HeroHP(this._detailsOfHero.get_HeroHP() - 1);
                    } else if (this._detailsOfHero.get_HeroDefense() <= 0)
                    {
                        this._detailsOfHero.set_HeroHP(this._detailsOfHero.get_HeroHP() - 5);
                    }
                    if (envGame.equals("Console"))
                    {
                        System.out.println("The enemy stabbed you with a knife");
                    } else {
                        this._heroInformation.displayInformation("The enemy stabbed you with a knife", envGame);
                    }

                } else if (enemyW == 2) {
                    this._detailsOfHero.set_HeroDefense(this._detailsOfHero.get_HeroDefense() - 7);
                    if (this._detailsOfHero.get_HeroDefense() < 10 && this._detailsOfHero.get_HeroDefense() > 0)
                    {
                        this._detailsOfHero.set_HeroHP(this._detailsOfHero.get_HeroHP() - 1);
                    } else if (this._detailsOfHero.get_HeroDefense() <= 0)
                    {
                        this._detailsOfHero.set_HeroHP(this._detailsOfHero.get_HeroHP() - 7);
                    }
                    if (envGame.equals("Console"))
                    {
                        System.out.println("The enemy hit you with a knobkirrie");
                    } else {
                        this._heroInformation.displayInformation("The enemy hit you with a knobkirrie", envGame);
                    }
                } else if (enemyW == 3) {
                    this._detailsOfHero.set_HeroDefense(this._detailsOfHero.get_HeroDefense() - 8);
                    if (this._detailsOfHero.get_HeroDefense() < 10 && this._detailsOfHero.get_HeroDefense() > 0)
                    {
                        this._detailsOfHero.set_HeroHP(this._detailsOfHero.get_HeroHP() - 1);
                    } else if (this._detailsOfHero.get_HeroDefense() <= 0)
                    {
                        this._detailsOfHero.set_HeroHP(this._detailsOfHero.get_HeroHP() - 8);
                    }
                    if (envGame.equals("Console"))
                    {
                        System.out.println("The enemy shot you with a 9mm gun");
                    } else {
                        this._heroInformation.displayInformation("The enemy shot you with a 9mm gun", envGame);
                    }
                } else if (enemyW == 4) {

                    this._detailsOfHero.set_HeroDefense(this._detailsOfHero.get_HeroDefense() - 10);
                    if (this._detailsOfHero.get_HeroDefense() < 10 && this._detailsOfHero.get_HeroDefense() > 0)
                    {
                        this._detailsOfHero.set_HeroHP(this._detailsOfHero.get_HeroHP() - 1);
                    } else if (this._detailsOfHero.get_HeroDefense() <= 0)
                    {
                        this._detailsOfHero.set_HeroHP(this._detailsOfHero.get_HeroHP() - 10);
                    }
                    if (envGame.equals("Console"))
                    {
                        System.out.println("The enemy shot you with an Ak-47");
                    } else {
                        this._heroInformation.displayInformation("The enemy shot you with an Ak-47", envGame);
                    }
                } else if (enemyW == 5) {

                    this._detailsOfHero.set_HeroDefense(this._detailsOfHero.get_HeroDefense() - 12);
                    if (this._detailsOfHero.get_HeroDefense() < 10 && this._detailsOfHero.get_HeroDefense() > 0)
                    {
                        this._detailsOfHero.set_HeroHP(this._detailsOfHero.get_HeroHP() - 1);
                    } else if (this._detailsOfHero.get_HeroDefense() <= 0)
                    {
                        this._detailsOfHero.set_HeroHP(this._detailsOfHero.get_HeroHP() - 12);
                    }
                    if (envGame.equals("Console"))
                    {
                        System.out.println("The enemy released a Missile");
                    } else {
                        this._heroInformation.displayInformation("The enemy released a Missile", envGame);
                    }
                }

                this.DisplayStats(envGame);
            } else {
                enemyHP -= 10;
                this.DisplayStats(envGame);
                if (envGame.equals("Console"))
                {
                    System.out.println("Hero strikes.");
                } else {
                    _heroInformation.displayInformation("Hero strikes.", envGame);
                }
            }
        }

        if (this._detailsOfHero.get_HeroHP() > 0)
        {
            if (envGame.equals("Console"))
            {
                System.out.println("You killed the enemy");
            } else {
                this._heroInformation.displayInformation("You killed the enemy", envGame);
            }
            this._detailsOfHero.set_HeroDefense(this._detailsOfHero.get_HeroDefense() + 50);
            this._detailsOfHero.set_HeroExp(this._detailsOfHero.get_HeroExp() + 300);
            enemyW = map.get("Enemy" + Integer.toString(this._enemyHere) + "Weapon");

            int dropW = random.nextInt(4);

            if (dropW%2 != 0)
            {
                if (enemyW == 1)
                {
                    if (envGame.equals("Console"))
                    {
                        System.out.println("The enemy dropped a knife");
                    } else {
                        this._heroInformation.displayInformation("The enemy dropped a knife", envGame);
                    }
                    this._detailsOfHero.set_HeroWeapos(enemyW);
                } else if (enemyW == 2) {
                    if (envGame.equals("Console"))
                    {
                        System.out.println("The enemy dropped a knobkirrie");
                    } else {
                        this._heroInformation.displayInformation("The enemy dropped a knobkirrie", envGame);
                    }
                    this._detailsOfHero.set_HeroWeapos(enemyW);
                } else if (enemyW == 3) {
                    if (envGame.equals("Console"))
                    {
                        System.out.println("The enemy dropped a 9mm gun");
                    } else {
                        this._heroInformation.displayInformation("The enemy dropped a 9mm gun", envGame);
                    }
                    this._detailsOfHero.set_HeroWeapos(enemyW);
                } else if (enemyW == 4) {
                    if (envGame.equals("Console"))
                    {
                        System.out.println("The enemy dropped a Ak-47");
                    } else {
                        this._heroInformation.displayInformation("The enemy dropped a Ak-47", envGame);
                    }
                    this._detailsOfHero.set_HeroWeapos(enemyW);
                } else if (enemyW == 5) {
                    if (envGame.equals("Console"))
                    {
                        System.out.println("The enemy dropped a Missile");
                    } else {
                        this._heroInformation.displayInformation("The enemy dropped a Missile", envGame);
                    }
                    this._detailsOfHero.set_HeroWeapos(enemyW);
                }


                this._enemyArray.remove(this._enemyHere - 1);
            }
        }else {
            if (envGame.equals("Console"))
            {
                System.out.println("Game Over Game Over Game Over Game Over");
            } else {
                MainFrame.getInstance().hidePanel(MainFrame.getInstance().getPanel());
                this._heroInformation.displayInformation("Game Over Game Over", "Gui");
                GameOver _display = new GameOver(_detailsOfHero.get_HeroStatements());

                MainFrame.getInstance().addPanel(_display.getDisplay());
                MainFrame.getInstance().getFrame().setTitle(_display.getTitle());
            }
        }
    }

    public void RunAway(String envGame)
    {
        Random random = new Random();
        int randomNum = random.nextInt(5);

        if (randomNum%2 == 0)
        {
            _heroInformation.displayInformation("You have to fight.", envGame);
            this.FightEnemy(envGame);
        } else {
            _heroInformation.displayInformation("That was a close call.", envGame);
        }
    }
}

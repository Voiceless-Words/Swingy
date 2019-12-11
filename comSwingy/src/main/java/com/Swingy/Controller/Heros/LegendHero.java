package com.Swingy.Controller.Heros;

import java.util.Random;

public class LegendHero implements HeroDetails {


    private String _heroName;
    private String _heroClass;
    private int _heroLevel;
    private int _heroExp;
    private int _heroAttack;
    private int _heroDefense;
    private int _heroHP;
    private String _heroStatements;
    private int _heroRow;
    private int _heroCol;
    private int _heroID;
    private int[] _heroWeapons;

    public LegendHero(String heroName, String heroClass, int heroLevel,int heroAttack, int heroExp, int heroDefense, int heroHP, int row, int col, String val)
    {
        this._heroName = heroName;
        this._heroClass = heroClass;
        this._heroLevel = heroLevel;
        this._heroExp = heroExp;
        this._heroDefense = heroDefense;
        this._heroHP = heroHP;
        this._heroCol = col;
        this._heroRow = row;
        this.set_HeroStatements(val);
        this._heroAttack = heroAttack;
        this._heroWeapons = new int[5];

        Random random = new Random();
        this._heroWeapons[0] = random.nextInt((5 - 1) + 1) + 1;
    }

    public String get_HeroName() {
        return _heroName;
    }

    public void set_HeroName(String heroName) {
        this._heroName = heroName;
    }

    public String get_HeroClass() {
        return _heroClass;
    }

    public void set_HeroClass(String heroClass) {
        this._heroClass = heroClass;
    }

    public int get_HeroLevel() {
        return _heroLevel;
    }

    public void set_HeroLevel(int heroLevel) {
        this._heroLevel = heroLevel;
    }

    public int get_HeroExp() {
        return _heroExp;
    }

    public void set_HeroExp(int heroExp) {
        this._heroExp = heroExp;
    }

    public int get_HeroAttack() {
        return _heroAttack;
    }

    public void set_HeroAttack(int heroAttack) {
        this._heroAttack = heroAttack;
    }

    public int get_HeroDefense() {
        return _heroDefense;
    }

    public void set_HeroDefense(int heroDefense) {
        this._heroDefense = heroDefense;
    }

    public int get_HeroHP() {
        return _heroHP;
    }

    public void set_HeroHP(int heroHP) {
        this._heroHP = heroHP;
    }

    @Override
    public int[] get_HeroWeapons() {
        return this._heroWeapons;
    }

    @Override
    public void set_HeroWeapos(int number) {
        int arrSize = this._heroWeapons.length;

        if (arrSize < 5)
        {
            this._heroWeapons[arrSize] = number;
        } else {
            this._heroWeapons[arrSize - 1] = number;
        }
    }

    @Override
    public String get_HeroStatements() {
        return _heroStatements;
    }

    @Override
    public void set_HeroStatements(String statements) {
        this._heroStatements = statements;

    }

    @Override
    public void set_HeroRow(int row) {
        this._heroRow = row;

    }

    @Override
    public int get_HeroRow() {
        return _heroRow;
    }

    @Override
    public void set_HeroCol(int col) {
        this._heroCol = col;
    }

    @Override
    public int get_HeroCol() {
        return _heroCol;
    }

    @Override
    public int get_HeroID() {
        return _heroID;
    }

    @Override
    public void set_HeroID(int id) {
        this._heroID = id;
    }

}

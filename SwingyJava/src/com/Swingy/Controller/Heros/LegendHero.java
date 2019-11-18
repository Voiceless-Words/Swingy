package com.Swingy.Controller.Heros;

public class LegendHero implements HeroDetails {


    private String _heroName;
    private String _heroClass;
    private int _heroLevel;
    private int _heroExp;
    private int _heroAttack;
    private int _heroDefense;
    private int _heroHP;

    public LegendHero(String heroName, String heroClass, int heroLevel,int heroAttack, int heroExp, int heroDefense, int heroHP)
    {
        this._heroName = heroName;
        this._heroClass = heroClass;
        this._heroLevel = heroLevel;
        this._heroExp = heroExp;
        this._heroDefense = heroDefense;
        this._heroHP = heroHP;
        this._heroAttack = heroAttack;
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
}

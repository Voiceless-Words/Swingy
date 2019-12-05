package com.Swingy.Controller.Heros;

public interface HeroDetails {
    public String get_HeroName();
    public void set_HeroName(String _HeroName);
    public String get_HeroClass();
    public void set_HeroClass(String _HeroClass);
    public int get_HeroLevel();
    public void set_HeroLevel(int _HeroLevel);
    public int get_HeroExp();
    public void set_HeroExp(int _HeroExp);
    public int get_HeroAttack();
    public void set_HeroAttack(int _HeroAttack);
    public int get_HeroDefense();
    public void set_HeroDefense(int _HeroDefense);
    public int get_HeroHP();
    public void set_HeroHP(int _HeroHP);
    public int [] get_HeroWeapons();
    public void set_HeroWeapos(int number);
    public String get_HeroStatements();
    public void set_HeroStatements(String statements);
    public void set_HeroRow(int row);
    public int get_HeroRow();
    public void set_HeroCol(int row);
    public int get_HeroCol();
    public int get_HeroID();
    public void set_HeroID(int id);


}

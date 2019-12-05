package com.Swingy.Model;

import com.Swingy.Controller.Heros.HeroDetails;

import java.sql.*;

public class HeroStats {

    private Connection _connect;
    private Statement _queryStatement;
    private ResultSet _queryResult;

    public HeroStats() {
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            _connect = DriverManager.getConnection("jdbc:sqlite:heroes.db");
            if (_connect != null) {

                DatabaseMetaData dm = _connect.getMetaData();
                String queryStatement = "CREATE TABLE IF NOT EXISTS savedHeroes (\n"
                        + "    id integer PRIMARY KEY AUTO_INCREMENT,\n"
                        + "    HeroName text NOT NULL,\n"
                        + "    HeroClass text NOT NULL,\n"
                        + "    HeroStatements text NOT NULL,\n"
                        + "    HeroLevel integer NOT NULL,\n"
                        + "    HeroExp integer NOT NULL,\n"
                        + "    HeroAttack integer NOT NULL,\n"
                        + "    HeroDefense integer NOT NULL,\n"
                        + "    HeroHP integer NOT NULL,\n"
                        + "    HeroRow integer NOT NULL,\n"
                        + "    HeroCol integer NOT NULL"
                        + ");";
                _queryStatement = _connect.createStatement();
                _queryStatement.execute(queryStatement);
                _connect.close();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addNewHero(HeroDetails heroDetails) {
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            _connect = DriverManager.getConnection("jdbc:sqlite:heroes.db");
            if (_connect != null) {

                DatabaseMetaData dm = _connect.getMetaData();

                String queryStatement = "INSERT INTO saveHeroes (HeroName, HeroClass, HeroStatements, HeroLevel,\n"
                        + "HeroExp, HeroAttack, HeroDefense, HeroHP, HeroRow, HeroCol) \n"
                        + "VALUES(" + heroDetails.get_HeroName() + "," + heroDetails.get_HeroClass() + "," + heroDetails.get_HeroStatements()
                        + "," + heroDetails.get_HeroLevel() + "," + heroDetails.get_HeroExp() + "," + heroDetails.get_HeroAttack()
                        + "," + heroDetails.get_HeroDefense() + "," + heroDetails.get_HeroHP() + "," + heroDetails.get_HeroRow()
                        + "," + heroDetails.get_HeroCol() + ");";

                _queryStatement = _connect.createStatement();
                _queryStatement.execute(queryStatement);
                _connect.close();
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void updateHero(HeroDetails heroDetails) {
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            _connect = DriverManager.getConnection("jdbc:sqlite:heroes.db");
            if (_connect != null) {
                DatabaseMetaData dm = _connect.getMetaData();
                String queryStatement = " UPDATE savedHeroes SET HeroName = " + heroDetails.get_HeroName()
                        + ", HeroClass = " + heroDetails.get_HeroClass() + ", HeroStatements = " + heroDetails.get_HeroStatements()
                        + ", HeroLevel = " + heroDetails.get_HeroLevel() + ", HeroExp = " + heroDetails.get_HeroExp()
                        + ", HeroAttack = " + heroDetails.get_HeroAttack() + ", HeroDefense = " + heroDetails.get_HeroDefense()
                        + ", HeroHP = " + heroDetails.get_HeroHP() + ", HeroRow = " + heroDetails.get_HeroRow()
                        + ", HeroCol = " + heroDetails.get_HeroCol() + " WHERE id = " + heroDetails.get_HeroID() +");";

                _queryStatement = _connect.createStatement();
                _queryStatement.execute(queryStatement);
                _connect.close();
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
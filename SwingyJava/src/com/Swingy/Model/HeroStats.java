package com.Swingy.Model;

import com.Swingy.Controller.Heros.HeroDetails;
import com.Swingy.View.FeleletsaPapadi.FeleletsaPapadi;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;

public class HeroStats {

    private Connection _connect;
    private PreparedStatement _queryStatement;
    private ResultSet _queryResult;
    private int _id;

    public HeroStats() {
        try {
            _connect = this.get_connect();
            if (_connect != null) {

                DatabaseMetaData dm = _connect.getMetaData();
                String queryStatement = "CREATE TABLE IF NOT EXISTS savedHeroes (\n"
                        + "    id INTEGER  PRIMARY KEY,\n"
                        + "    HeroName text NOT NULL,\n"
                        + "    HeroClass text NOT NULL,\n"
                        + "    HeroStatements text,\n"
                        + "    HeroLevel INTEGER NOT NULL,\n"
                        + "    HeroExp INTEGER NOT NULL,\n"
                        + "    HeroAttack INTEGER NOT NULL,\n"
                        + "    HeroDefense INTEGER NOT NULL,\n"
                        + "    HeroHP INTEGER NOT NULL,\n"
                        + "    HeroRow INTEGER NOT NULL,\n"
                        + "    HeroCol INTEGER NOT NULL"
                        + ");";
                _queryStatement = _connect.prepareStatement(queryStatement);
                _queryStatement.execute();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addNewHero(HeroDetails heroDetails) {
        try {
            _connect = this.get_connect();
            if (_connect != null) {

                DatabaseMetaData dm = _connect.getMetaData();

                String queryStatement = "INSERT INTO savedHeroes (HeroName, HeroClass, HeroStatements, HeroLevel, "
                        + "HeroExp, HeroAttack, HeroDefense, HeroHP, HeroRow, HeroCol) \n"
                        + "VALUES(?,?,?,?,?,?,?,?,?,?);";

                _queryStatement = _connect.prepareStatement(queryStatement, Statement.RETURN_GENERATED_KEYS);
               this.updatDataStatement(_queryStatement, heroDetails);
                _queryStatement.executeUpdate();

                _queryResult = _queryStatement.getGeneratedKeys();

                if(_queryResult.next())
                {
                    _id = _queryResult.getInt(1);
                    heroDetails.set_HeroID(_id);
                }

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void updateHero(HeroDetails heroDetails) {
        try {
            _connect = this.get_connect();
            if (_connect != null) {
                String queryStatement = " UPDATE savedHeroes SET HeroName = ? "
                        + ", HeroClass = ? " + ", HeroStatements = ?"
                        + ", HeroLevel = ? " + ", HeroExp = ? "
                        + ", HeroAttack = ? " + ", HeroDefense = ? "
                        + ", HeroHP = ? " + ", HeroRow = ? "
                        + ", HeroCol = ? " + " WHERE id = ? ;";

                _queryStatement = _connect.prepareStatement(queryStatement);
                this.updatDataStatement(_queryStatement, heroDetails);
                _queryStatement.setInt(11, heroDetails.get_HeroID());
                _queryStatement.execute();
            }

        } catch (Exception e) {
            System.out.println("Database connected");
        }

    }

    public void updatDataStatement(PreparedStatement _queryStatement, HeroDetails heroDetails)
    {
        try {
            _queryStatement.setString(1, heroDetails.get_HeroName());
            _queryStatement.setString(2, heroDetails.get_HeroClass());
            _queryStatement.setString(3, heroDetails.get_HeroStatements());
            _queryStatement.setInt(4, heroDetails.get_HeroLevel());
            _queryStatement.setInt(5, heroDetails.get_HeroExp());
            _queryStatement.setInt(6, heroDetails.get_HeroAttack());
            _queryStatement.setInt(7, heroDetails.get_HeroDefense());
            _queryStatement.setInt(8, heroDetails.get_HeroHP());
            _queryStatement.setInt(9, heroDetails.get_HeroRow());
            _queryStatement.setInt(10, heroDetails.get_HeroCol());
        } catch (Exception e)
        {
            System.out.println(e);
        }

    }

    public JTable fetchHeroes()
    {
        String[] columnNames = {"ID", "Name", "Class","Messages","Level","Exp","Attack","Defense","HP","Row","Col"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        JTable one = new JTable();
        try{
            _connect = this.get_connect();
            if (_connect != null) {
                DatabaseMetaData dm = _connect.getMetaData();
                String queryStatement = " SELECT * FROM savedHeroes";

                _queryStatement = _connect.prepareStatement(queryStatement);;
                _queryResult = _queryStatement.executeQuery();
                while (_queryResult.next())
                {
                    Vector row = new Vector();

                    for (int i = 1; i <= 11; i++)
                    {
                        row.addElement( _queryResult.getObject(i) );
                    }

                    model.addRow( row );
                }
                one = new JTable(model);
        }

        } catch (Exception e) {
            System.out.println(e);
        }
        return one;
    }

    public ResultSet getSingleHero(int ID)
    {
        try {
            _connect = this.get_connect();
            if (_connect != null) {
                String queryStatement = " SELECT * FROM savedHeroes WHERE id = ? ;";

                _queryStatement = _connect.prepareStatement(queryStatement);
                _queryStatement.setInt(1, ID);
                _queryResult = _queryStatement.executeQuery();
                //_connect.close();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return _queryResult;
    }

    public Connection get_connect() throws SQLException
    {
        if (_connect == null) {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            _connect = DriverManager.getConnection("jdbc:sqlite:heroes.db");
        }
        return _connect;
    }

    public void closeConnection()
    {
        try {
            this._connect.close();
        }catch (Exception clos){
            System.out.println("Already closed");
        }
    }
}
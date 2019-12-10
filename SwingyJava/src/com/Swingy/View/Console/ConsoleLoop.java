package com.Swingy.View.Console;

import com.Swingy.Controller.ConsoleActionListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface InstructionAnnotation{
    String[] value();
}

public class ConsoleLoop {
    private InputStreamReader _readerI;
    private BufferedReader _bReader;
    private String _selectionMade;

    public ConsoleLoop()
    {
        this._readerI = new InputStreamReader(System.in);
        this._bReader = new BufferedReader(_readerI);
        try {
            this.PrintHeader();
            System.out.println("Make Your Selection.\n1. New Game\n2. Continue Game");

            this._selectionMade = _bReader.readLine();
            while (!this._selectionMade.equals("1") && !this._selectionMade.equals("2"))
            {
                System.out.println("Make Your Selection.\n1. New Game\n2. Continue Game");

                this._selectionMade = _bReader.readLine();
            }
            if (this._selectionMade.equals("1"))
            {
                this._selectionMade = "";
                while (!this._selectionMade.equals("1") && !this._selectionMade.equals("2") && !this._selectionMade.equals("3"))
                {
                    System.out.println("Select Your Hero.\n1. Hero Class Type: Warrior\n"
                            + "Hero Attack: 75\nHero Defense: 95\nHero Health Points: 100\n"
                            + "Hero Experience: 500\n\n2. Hero Class Type: Healer\n"
                            + "Hero Attack: 65\nHero Defense: 50\nHero Health Points: 150\n"
                            + "Hero Experience: 450\n\n3. Hero Class Type: Legend\n"
                            + "Hero Attack: 90\nHero Defense: 85\nHero Health Points: 100\n"
                            + "Hero Experience: 600");
                    this._selectionMade = _bReader.readLine();
                }
                String name = null;
                while (name == null || name.length() < 3)
                {
                    System.out.println("Enter your Hero name");
                    name = _bReader.readLine();
                }

                if (this._selectionMade.equals("1"))
                {
                    new ConsoleActionListener(name, "Warrior", 0, 75, 500, 95, 100, 0,0,"", 0);

                } else if (this._selectionMade.equals("2"))
                {
                    new ConsoleActionListener(name, "Healer", 0, 65, 450, 50, 150, 0,0,"", 0);

                } else if (this._selectionMade.equals("3"))
                {
                    new ConsoleActionListener(name, "Healer", 0, 65, 450, 50, 150, 0,0,"", 0);
                }
                this.playGame();
            } else if (this._selectionMade.equals("2"))
            {
                ResultSet rs = ConsoleActionListener.contGame();
                ResultSetMetaData rsmd  = rs.getMetaData();
                int rowCount = rs.getRow();
                int columnCount = rsmd.getColumnCount();

                ArrayList<String> heroResultList = new ArrayList<>(columnCount);
                while (rs.next()) {
                    int i = 1;
                    while(i <= columnCount) {
                        heroResultList.add(rs.getString(i++));
                    }
                }

                for (int i = 0; i < heroResultList.size(); i++) {
                    if (i%11 == 0)
                    {
                        rowCount++;
                        System.out.print(heroResultList.get(i));
                        System.out.println(" " + heroResultList.get(i+1));
                    }
                }

                this._selectionMade = "0";
                System.out.println(rowCount);
                while(Integer.parseInt(_selectionMade) <= 0 || Integer.parseInt(_selectionMade) > rowCount)
                {
                    System.out.println("Print Select A hero to continue");
                    _selectionMade = _bReader.readLine();
                }
                ResultSet heroDetails = ConsoleActionListener.singleUser(Integer.parseInt(_selectionMade));
                new ConsoleActionListener(heroDetails.getString(2), heroDetails.getString(3),
                        heroDetails.getInt(5), heroDetails.getInt(7), heroDetails.getInt(6), heroDetails.getInt(8),
                        heroDetails.getInt(9), heroDetails.getInt(10), heroDetails.getInt(11), heroDetails.getString(4), Integer.parseInt(_selectionMade));

                this.playGame();
                
            } else {
                System.out.println("Choose wisely");
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void PrintHeader()
    {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n@ |----------------\t\t|\t\t\t\t\t\t\t\t|\t\t@" +
                "\n@ |\t\t\t\t\t\t|\t\t|\t\t\t\t\t\t\t\t@\n@ |\t\t\t\t|\t\t\t\t|\t\t\t\t\t\t\t\t@\n@ |----------------\t\t\t\t\t\t\t\t|\t\t\t\t@\n@\t\t\t\t  |\t" +
                "\t\t\t\t\t\t|\t\t\t\t\t@\n@\t\t\t\t  |\t\t\t\t\t\t|\t\t|\t\t\t\t@\n@\t\t\t\t  |\t\t\t\t\t|\t\t\t|\t\t\t\t@\n@ ----------------|\t\t\t\t\t\t\t\t\t\t|\t\t@" +
                "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    public void playGame() throws IOException {
        this._selectionMade = "0";
        while (!this._selectionMade.equals(""))
        {
            this.PrintHeader();
            System.out.println("Make a move.\n1. Up\t2. Down\t3. Left\t4. Right\t5. Run\t6. Fight");
            this._selectionMade = _bReader.readLine();
            ConsoleActionListener.get_gamePlayInstance().consoleMoveSelection(this._selectionMade);

        }
    }


}

package application.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Currency;
import view.CurrencySetLoader;

public class SQLiteCurrencySetLoader implements CurrencySetLoader{

    @Override
    public ArrayList<Currency> load() {
        ArrayList<Currency> list = new ArrayList<>();
        try{
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:RATES.db");
            Statement statement = (Statement) connection.createStatement();
            String query = "SELECT CURRENCY FROM RATES";
            ResultSet result = statement.executeQuery(query);
            
            while(result.next()){
                list.add(new Currency(result.getString(1)));
            }
            result.close();
            statement.close();
            connection.close();
            return list;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectarse con la Base de Datos");
        }
        return null;
    }

}

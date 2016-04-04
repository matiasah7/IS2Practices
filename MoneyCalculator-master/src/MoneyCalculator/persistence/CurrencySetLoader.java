package MoneyCalculator.persistence;

import MoneyCalculator.model.Currency;
import MoneyCalculator.model.CurrencySet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencySetLoader {

    private static final String URL = "jdbc:oracle:thin:@" + "localhost:1521:orcl";
    private static final String USER = "system";
    private static final String PASSWORD = "orcl";
    private static CurrencySetLoader instances;

    public CurrencySetLoader() {
    }

    public static CurrencySetLoader getInstance() {
        if (instances == null)
            instances = new CurrencySetLoader();
        return instances;
    }

    public static void load() throws SQLException {
        CurrencySet set = CurrencySet.getInstance();
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        java.sql.Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery("SELECT * FROM CAMBIO_EUR_A");
        if (set.isEmpty()) {
            while (resulSet.next())
                set.add(new Currency(resulSet.getString("DIVISA")));
            set.add(new Currency("EUR"));
        }
        connection.close();
    }
}

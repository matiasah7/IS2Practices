package MoneyCalculator.persistence;

import MoneyCalculator.model.Currency;
import MoneyCalculator.model.ExchangeRate;
import MoneyCalculator.model.ExchangeRateSet;
import MoneyCalculator.model.Money;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import MoneyCalculator.model.Number;

public class ExchangeRateLoader {

    private static ExchangeRateLoader instances;
    private static final String URL = "jdbc:oracle:thin:@" + "localhost:1521:orcl";
    private static final String USER = "system";
    private static final String PASSWORD = "orcl";

    public ExchangeRateLoader() {
    }

    public static ExchangeRateLoader getInstance() {
        if (instances == null)
            instances = new ExchangeRateLoader();
        return instances;
    }

    public static void load(Money money, Currency currency) throws SQLException {
        ExchangeRateSet rates = ExchangeRateSet.getInstance();
        DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        java.sql.Statement statement = connection.createStatement();
        ResultSet resulSet = statement.executeQuery("SELECT * FROM CAMBIO_EUR_A");
        if (rates.isEmpty()) {
            while (resulSet.next())
                rates.add(new ExchangeRate(new Currency("EUR"), new Currency(resulSet.getString("DIVISA")), new Number(resulSet.getDouble("CAMBIO"))));
            rates.add(new ExchangeRate(new Currency("EUR"), new Currency("EUR"), new Number(1)));
        }
        connection.close();
    }
}

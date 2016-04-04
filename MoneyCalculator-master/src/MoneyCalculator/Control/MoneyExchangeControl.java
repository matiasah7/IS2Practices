package MoneyCalculator.Control;

import MoneyCalculator.UI.CurrencyDialog;
import MoneyCalculator.UI.MoneyDialog;
import MoneyCalculator.UI.MoneyViewer;
import MoneyCalculator.model.Currency;
import MoneyCalculator.model.Money;
import MoneyCalculator.persistence.ExchangeRateLoader;
import java.sql.SQLException;

public class MoneyExchangeControl {

    private CurrencyDialog currencyDialog;
    private MoneyDialog moneyDialog;
    private MoneyViewer moneyViewer;

    public MoneyExchangeControl(CurrencyDialog currencyDialog, MoneyDialog moneyDialog, MoneyViewer moneyViewer) {
        this.currencyDialog = currencyDialog;
        this.moneyDialog = moneyDialog;
        this.moneyViewer = moneyViewer;
    }

    public void execute() throws SQLException {
        Money money = readMoney();
        Currency currency = readCurrency();
        ExchangeRateLoader.load(money, currency);
    }

    private Money readMoney() {
        return moneyDialog.getMoney();
    }

    private Currency readCurrency() {
        return currencyDialog.getCurrency();
    }
}

package MoneyCalculator.Control;

import MoneyCalculator.UI.CurrencyDialog;
import MoneyCalculator.UI.MoneyDialog;
import MoneyCalculator.UI.MoneyViewer;
import MoneyCalculator.model.Currency;
import MoneyCalculator.model.ExchangeRateSet;
import MoneyCalculator.model.Money;
import MoneyCalculator.model.Number;
import java.sql.SQLException;

public class CalculateCommand extends Command {

    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;
    private final MoneyViewer moneyViewer;

    public CalculateCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog, MoneyViewer moneyViewer) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.moneyViewer = moneyViewer;
    }

    @Override
    public void execute() throws SQLException {
        MoneyExchangeControl exchangeControl = new MoneyExchangeControl(currencyDialog, moneyDialog, moneyViewer);
        exchangeControl.execute();
        moneyViewer.show(new Money(calculateAmount(), currencyDialog.getCurrency()));
    }

    private Number getExchangeRate() {
        if (moneyDialog.getMoney().getCurrency().getCode().equals("EUR"))
            return ExchangeRateSet.getInstance().getRateFromSet(moneyDialog.getMoney().getCurrency(), currencyDialog.getCurrency());
        else if (currencyDialog.getCurrency().getCode().equals("EUR"))
            return ExchangeRateSet.getInstance().getRateFromSet(new Currency("EUR"), moneyDialog.getMoney().getCurrency());
        else {
            Number rateEurTo = ExchangeRateSet.getInstance().getRateFromSet(new Currency("EUR"), moneyDialog.getMoney().getCurrency());
            Number rateTo = ExchangeRateSet.getInstance().getRateFromSet(new Currency("EUR"), currencyDialog.getCurrency());
            return rateEurTo.divide(rateTo);
        }
    }

    private Number calculateAmount() {
        if (moneyDialog.getMoney().getCurrency().getCode().equals("EUR"))
            return getExchangeRate().multiply(moneyDialog.getMoney().getAmount());
        return moneyDialog.getMoney().getAmount().divide(getExchangeRate());
    }
}

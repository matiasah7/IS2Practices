package MoneyCalculator.model;

import java.util.ArrayList;

public class ExchangeRateSet extends ArrayList<ExchangeRate> {

    private static ExchangeRateSet instances;

    public ExchangeRateSet() {
        super();
    }

    public static ExchangeRateSet getInstance() {
        if (instances == null)
            instances = new ExchangeRateSet();
        return instances;
    }

    public Number getRateFromSet(Currency currencyFrom, Currency currencyTo) {
        for (ExchangeRate exchangeRate : this)
            if (exchangeRate.getFrom().equals(currencyFrom) && exchangeRate.getTo().equals(currencyTo))
                return exchangeRate.getRate();
        return new Number(0);
    }
}

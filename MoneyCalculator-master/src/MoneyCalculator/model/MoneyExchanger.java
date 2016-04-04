package MoneyCalculator.model;

public class MoneyExchanger {

    public Money exchange(Money money, ExchangeRate rate) {
        return new Money(money.getAmount().multiply(rate.getRate()), rate.getTo());
    }
}

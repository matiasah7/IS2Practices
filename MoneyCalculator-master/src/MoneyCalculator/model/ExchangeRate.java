package MoneyCalculator.model;

import java.util.Date;

public class ExchangeRate {

    private Date date;
    private Currency from;
    private Currency to;
    private Number rate;

    public ExchangeRate(Date date, Currency from, Currency to, Number rate) {
        this.date = date;
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    public ExchangeRate(Currency from, Currency to, Number rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    public Date getDate() {
        return date;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

    public Number getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return from + " a " + to + " :" + rate + " .Fecha :" + date;
    }
}

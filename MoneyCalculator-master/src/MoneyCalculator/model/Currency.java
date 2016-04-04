package MoneyCalculator.model;

import java.util.Objects;

public class Currency {

    private String name;
    private String symbol;
    private String code;

    public Currency(String name, String code, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.code = code;
    }

    public Currency(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Currency(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCode() {
        return code;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Currency other = (Currency) obj;
        if (!Objects.equals(this.code, other.code))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return code;
    }
}

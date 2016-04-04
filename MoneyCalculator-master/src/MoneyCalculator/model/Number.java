package MoneyCalculator.model;

public class Number {

    private long numerator;
    private long denominator;

    public Number(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        reduce();
    }

    public Number(Number number) {
        this(number.numerator, number.denominator);
    }

    public Number(long number) {
        this(number, 1);
    }

    public Number(int number) {
        this(number, 1);
    }

    public Number parseNumber(String string) {
        return new Number(Double.parseDouble(string));
    }

    public Number(double number) {
        this.numerator = (long) number;
        this.denominator = 1;
        while (number != numerator) {
            number *= 10;
            denominator *= 10;
            numerator = (long) number;
        }
        reduce();
    }

    private int[] getPrimeNumbers() {
        return new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23};
    }

    private boolean isDivisible(int number) {
        return ((numerator % number == 0) && (denominator % number == 0));
    }

    private void reduce() {
        int[] primeNumbers = getPrimeNumbers();
        for (int number : primeNumbers) {
            if (numerator < number)
                break;
            if (denominator < number)
                break;
            while (isDivisible(number)) {
                numerator /= number;
                denominator /= number;
            }
        }
    }

    public Number add(Number num) {
        Number number = new Number(num);
        this.reduce();
        num.reduce();
        if (denominator != num.denominator) {
            number.denominator = denominator * num.denominator;
            number.numerator = (numerator * num.denominator) + (num.numerator * denominator);
            number.reduce();
            return number;
        }
        else {
            number.denominator = num.denominator;
            number.numerator = num.numerator + numerator;
            number.reduce();
            return number;
        }
    }

    public Number multiply(Number num) {
        Number number = new Number(num.numerator * numerator, num.denominator * denominator);
        number.reduce();
        return number;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (!(object instanceof Number))
            return false;
        return equals((Number) object);
    }

    private boolean equals(Number number) {
        return (number.numerator) == numerator;
    }

    @Override
    public String toString() {
        float result = ((float) numerator / (float) denominator);
        return (String) String.valueOf(result);
    }

    public Number divide(Number exchangeRate) {

        Number num = new Number(numerator * exchangeRate.denominator, denominator * exchangeRate.numerator);
        reduce();
        return num;
    }
}

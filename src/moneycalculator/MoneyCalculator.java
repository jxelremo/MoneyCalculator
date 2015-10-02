package moneycalculator;

public class MoneyCalculator {

    public static void main(String[] args) {
        Currency dollar = new Currency("USD","American Dollar","$");
        Currency euro = new Currency("EUR","Euro","€");
        ExchangeRate rate = new ExchangeRate(euro, dollar, 1.20);
        Money amount = new Money(100.9,dollar);

        System.out.println(MoneyExchanger.exchange(amount,rate).getAmount());
    }
    
}

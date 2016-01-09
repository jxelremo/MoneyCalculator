package control;

import application.sqlite.SQLiteExchangeRateLoader;
import model.Currency;
import model.ExchangeRate;
import model.Money;
import view.CurrencyDialog;
import view.MoneyDialog;
import view.MoneyDisplay;

public class ExchangeCommand implements Command{
    private final MoneyDialog moneyDialog;
    private final CurrencyDialog currencyDialog;
    private final MoneyDisplay moneyDisplay;

    public ExchangeCommand(MoneyDialog moneyDialog, CurrencyDialog currencyDialog, MoneyDisplay moneyDisplay) {
        this.moneyDialog = moneyDialog;
        this.currencyDialog = currencyDialog;
        this.moneyDisplay = moneyDisplay;
    }

    @Override
    public void execute(){
        Money money = moneyDialog.get();
        Currency currency = currencyDialog.get();
        ExchangeRate exchangeRate = new SQLiteExchangeRateLoader().load(money.getCurrency(), currency);
        Money result = exchange(money, exchangeRate);
        moneyDisplay.show(result);
    }
    
    private Money exchange(Money money, ExchangeRate rate){
        return new Money(money.getAmount() * rate.getRate(), rate.getTo());
    }
}

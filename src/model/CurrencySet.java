package model;

import java.util.ArrayList;

public class CurrencySet {

    private final ArrayList<Currency> currencyList;

    public CurrencySet(ArrayList<Currency> list) {
        this.currencyList = list;
    }
    
    public Currency get(String string){
        for (Currency currency : currencyList) {
            if ( currency.getCode().equals(string) || currency.getName().equals(string) || currency.getSymbol().equals(string)){
                return currency;
            }
        }
        return null;
    }
    
    public ArrayList<Currency> getList(){
        return currencyList;
    }
}

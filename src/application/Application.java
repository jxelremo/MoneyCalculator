package application;

import application.sqlite.SQLiteCurrencySetLoader;
import application.swing.SwingCurrencyDialog;
import application.swing.SwingMoneyDialog;
import application.swing.SwingMoneyDisplay;
import control.Command;
import control.ExchangeCommand;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Currency;

public class Application extends JFrame{

    private Command command;
    private SwingMoneyDialog swingMoneyDialog;
    private SwingMoneyDisplay swingMoneyDisplay;
    private SwingCurrencyDialog swingCurrencyDialog;
    
    public static void main(String[] args) {
        new Application().setVisible(true);
    }
    
    public Application() {
        deployUI();
        createCommands();
    }

    private void deployUI() {
        this.setTitle("MoneyCalculator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(components());
        this.setPreferredSize(new Dimension(250, 100));
        this.pack();
        this.setResizable(false);
    }

    private void createCommands() {
        this.command = new ExchangeCommand(swingMoneyDialog, swingCurrencyDialog, swingMoneyDisplay);
    }

    private JPanel components() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        ArrayList<Currency> currencyList = new SQLiteCurrencySetLoader().load();
        panel.add(fromComponents(currencyList));
        panel.add(toComponents(currencyList));
        return panel;
    }

    private SwingMoneyDialog fromComponents(ArrayList<Currency> currencyList) {
        JTextField textField = new JTextField("0");
        textField.addKeyListener(doCommandOnType());
        this.swingMoneyDialog = new SwingMoneyDialog(textField, fromOptions(currencyList));
        return swingMoneyDialog;
    }

    private JPanel toComponents(ArrayList<Currency> currencyList) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        this.swingMoneyDisplay = new SwingMoneyDisplay();
        panel.add(swingMoneyDisplay);
        panel.add(toOptions(currencyList));
        return panel;
    }

    private KeyListener doCommandOnType() {
        return new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if(!swingMoneyDialog.getTextField().getText().equals("")){
                    command.execute();
                }else{
                    swingMoneyDisplay.getTextField().setText("");
                }
            }
        };
    }
    
    private JComboBox fromOptions(ArrayList<Currency> currencyList) {
        JComboBox comboBox = new JComboBox();
        for (Currency currency : currencyList) {
            comboBox.addItem(currency.getCode());
        }
        comboBox.addActionListener(doCommandOnComboBox());
        return comboBox;
    }

    private SwingCurrencyDialog toOptions(ArrayList<Currency> currencyList) {
        JComboBox comboBox = new JComboBox();
        for (Currency currency : currencyList) {
            comboBox.addItem(currency.getCode());
        }
        swingCurrencyDialog = new SwingCurrencyDialog(comboBox);
        comboBox.addActionListener(doCommandOnComboBox());
        return swingCurrencyDialog;
    }
    
    private ActionListener doCommandOnComboBox() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!swingMoneyDialog.getTextField().getText().equals("")){
                    command.execute();
                }else{
                    swingMoneyDisplay.getTextField().setText("");
                }
            }
        };
    }
    
}

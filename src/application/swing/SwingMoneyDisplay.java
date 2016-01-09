package application.swing;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Money;
import view.MoneyDisplay;

public class SwingMoneyDisplay extends JPanel implements MoneyDisplay{
    
    private final JTextField textField;

    public SwingMoneyDisplay() {
        this.textField = new JTextField("0");
        this.textField.setEditable(false);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.textField.setHorizontalAlignment(JTextField.RIGHT);
        this.add(textField);
    }
    
    @Override
    public void show(Money money) {
         this.textField.setText(String.format("%.2f",money.getAmount()));
    }
    
    public JTextField getTextField() {
        return textField;
    }
    
}
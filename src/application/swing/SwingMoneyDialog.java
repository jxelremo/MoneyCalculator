package application.swing;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Currency;
import model.Money;
import view.MoneyDialog;

public class SwingMoneyDialog extends JPanel implements MoneyDialog{
    
    private final JTextField textField;
    private final JComboBox comboBox;

    public SwingMoneyDialog(JTextField textField, JComboBox comboBox) {
        this.textField = textField;
        this.comboBox = comboBox;
        this.textField.setHorizontalAlignment(JTextField.RIGHT);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(textField);
        this.add(comboBox);
    }

    @Override
    public Money get() {
        return new Money(Float.parseFloat(textField.getText()), new Currency (comboBox.getSelectedItem().toString()));
    }
    
    public JTextField getTextField() {
        return textField;
    }
    
}

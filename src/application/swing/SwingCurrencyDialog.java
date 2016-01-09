package application.swing;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import model.Currency;
import view.CurrencyDialog;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog{
    
    private final JComboBox comboBox;

    public SwingCurrencyDialog(JComboBox comboBox) {
        this.comboBox = comboBox;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.comboBox.setSelectedIndex(2);
        this.add(comboBox);
    }
    
    @Override
    public Currency get() {
        return new Currency(comboBox.getSelectedItem().toString());
    }
    
}

package MoneyCalculator.UI;

import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JTextField;
import MoneyCalculator.model.Number;
import MoneyCalculator.model.Money;
import java.sql.SQLException;

public class MoneyDialogPanel extends JPanel implements MoneyDialog {

    private String amount = "0";
    private CurrencyDialog currencyDialog;

    public MoneyDialogPanel() throws SQLException {
        super(new FlowLayout(FlowLayout.LEFT));
        this.createComponents();
    }

    private void createComponents() throws SQLException {
        this.add(createAmountWidget());
        this.add(createCurrencyDialogPanel());
    }

    private JTextField createAmountWidget() {
        final JTextField textField = new JTextField(amount, 10);
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                amount = textField.getText();
            }
        });
        return textField;
    }

    @Override
    public Money getMoney() {
        return new Money(new Number(Double.parseDouble(amount)), currencyDialog.getCurrency());
    }

    private JPanel createCurrencyDialogPanel() throws SQLException {
        CurrencyDialogPanel panel = new CurrencyDialogPanel();
        this.currencyDialog = panel;
        return panel;
    }
}

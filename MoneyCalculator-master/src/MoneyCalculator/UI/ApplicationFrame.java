package MoneyCalculator.UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ApplicationFrame extends JFrame {

    private MoneyDialog moneyDialog;
    private CurrencyDialog currencyDialog;
    private MoneyViewer moneyViewer;
    private ActionListenerFactory factory;

    public ApplicationFrame(ActionListenerFactory factory) throws HeadlessException, SQLException {
        super("Money calculator");
        this.factory = factory;
        this.setSize(300, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.createComponents();
        this.setVisible(true);
    }

    public MoneyDialog getMoneyDialog() {
        return moneyDialog;
    }

    public CurrencyDialog getCurrencyDialog() {
        return currencyDialog;
    }

    public MoneyViewer getMoneyViewer() {
        return moneyViewer;
    }

    private void createComponents() throws SQLException {
        this.add(createContentPanel());
        this.add(createToolbarPanel(), BorderLayout.SOUTH);
    }

    private JPanel createContentPanel() throws SQLException {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(createMoneyDialogPanel());
        panel.add(createCurrencyDialogPanel());
        panel.add(createMoneyViewerPanel());
        return panel;
    }

    private JPanel createToolbarPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(createCalculateButton());
        panel.add(createExitButton());
        return panel;
    }

    private JButton createCalculateButton() {
        JButton button = new JButton("Calculate");
        button.addActionListener(factory.createActionListener("calculate"));
        return button;
    }

    private JButton createExitButton() {
        JButton button = new JButton("Exit");
        button.addActionListener(factory.createActionListener("exit"));
        return button;
    }

    private JPanel createMoneyDialogPanel() throws SQLException {
        MoneyDialogPanel panel = new MoneyDialogPanel();
        this.moneyDialog = panel;
        return panel;
    }

    private JPanel createCurrencyDialogPanel() throws SQLException {
        CurrencyDialogPanel panel = new CurrencyDialogPanel("USD");
        this.currencyDialog = panel;
        return panel;
    }

    private JPanel createMoneyViewerPanel() {
        MoneyViewerPanel panel = new MoneyViewerPanel();
        this.moneyViewer = panel;
        return panel;
    }
}
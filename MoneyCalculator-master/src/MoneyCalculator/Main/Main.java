package MoneyCalculator.Main;

import MoneyCalculator.Control.CalculateCommand;
import MoneyCalculator.Control.Command;
import MoneyCalculator.UI.ActionListenerFactory;
import MoneyCalculator.UI.ApplicationFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws SQLException {
        new Main().execute();
    }
    private HashMap<String, Command> commandMap;

    private void execute() throws SQLException {
        createCommands(createApplicationFrame());
    }

    private void createCommands(ApplicationFrame frame) {
        commandMap = new HashMap<>();
        commandMap.put("calculate", new CalculateCommand(
                frame.getMoneyDialog(),
                frame.getCurrencyDialog(),
                frame.getMoneyViewer()));
        commandMap.put("exit", new Command() {
            @Override
            public void execute() {
                System.exit(0);
            }
        });
    }

    private ApplicationFrame createApplicationFrame() throws SQLException {
        return new ApplicationFrame(new ActionListenerFactory() {
            @Override
            public ActionListener createActionListener(final String action) {
                return new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        Command command = commandMap.get(action);
                        if (command == null)
                            return;
                        try {
                            command.execute();
                        }
                        catch (SQLException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
            }
        });

    }
}

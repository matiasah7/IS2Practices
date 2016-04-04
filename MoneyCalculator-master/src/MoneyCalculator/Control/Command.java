package MoneyCalculator.Control;

import java.sql.SQLException;

public abstract class Command {

    public abstract void execute()throws SQLException;
}

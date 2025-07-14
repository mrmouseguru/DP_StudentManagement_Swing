package business.command;

public class CommandInvoker {
    public <T> T executeCommand(ICommand<T> command) throws Exception {
        return command.execute();
    }
}
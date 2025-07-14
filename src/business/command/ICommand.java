package business.command;

public interface ICommand<T> {
    T execute() throws Exception;
}
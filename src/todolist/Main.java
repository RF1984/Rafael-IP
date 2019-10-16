package todolist;

/**
 * This class is the Main class of To do List application
 * This program should open a text based application where you can create a list of tasks to do. You can add tasks,
 * delete tasks, mark tasks as done.
 * @Author Rafael Fernandes
 * @since  07.10.2019
 * @version 1.0
 */
public class Main {

    public static void main(String[] args)
    {
        Todolist todolist = new Todolist();
        todolist.printWelcome();
    }
}

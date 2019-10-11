import jdk.jfr.StackTrace;
import org.junit.jupiter.api.Test;
import todolist.Todolist;

public class testTodolist
{

    @Test
    void testAddTasks()
    {
        Todolist todolisttest = new Todolist();
        todolisttest.scannerAdd();

    }

}

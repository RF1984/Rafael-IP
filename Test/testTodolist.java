//import junit.org.*;
import org.junit.jupiter.api.Test;
import todolist.Todolist;


public class testTodolist
{

@Test
public void testAddTasks()
    {
        Todolist list1 = new Todolist();
        list1.scannerAdd();
    }
}

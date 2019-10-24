//import junit.org.*;
import org.junit.Test;
import todolist.Task;
import todolist.Todolist;

import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class contains some tests for the Todolist class
 */
public class testTodolist {

    /**
     * tests the scannerAddTask method by creating a fake input simulating a user.
     * creates a task with taskTitle "a", projectName "b", and dueDate "10-10-2020.
     * adds the task to a list, and then uses the getListElement to get the task from the list,
     * and confirms that the information inserted by the user is the same stored in the list
     */
    @Test
    public void testScannerAddTask() {
        ByteArrayInputStream fakeInput = new ByteArrayInputStream("a\r\nb\r\n10-10-2020\r\n1\n2\n4\n".getBytes());
        Todolist list1 = new Todolist(fakeInput);
        list1.scannerAdd();
        Task result = list1.getListElement(0);

        assertEquals("a", result.getTaskTitle());
        assertEquals("b", result.getProjectName());
        assertEquals("2020-10-10", result.getDueDate().toString());
    }
}

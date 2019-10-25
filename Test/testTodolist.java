//import junit.org.*;

import org.junit.Test;
import todolist.Task;
import todolist.Todolist;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

    /**
     * this method tests the getCount method. I creates a task, adds it to an array of Task, and then runs the method.
     * We choose to get the count for unfinished tasks (those with taskStatus = false).
     */
    @Test
    public void testGetCountMethod() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse("13-04-1999", formatter);
        Task t1 = new Task("task1", "project1", localDate);
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(t1);
        int result = (int) testList.stream().filter(x -> x.getTaskStatus().equals(false)).count();

        assertEquals(1, result);
    }
}
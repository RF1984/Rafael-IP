import org.junit.Test;
import todolist.Task;
import todolist.Todolist;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask {

    @Test
    public void testGetStatusNewTask()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse("13-04-1999", formatter);
        Task t1 = new Task("task1", "project1", localDate);
        Boolean result = t1.getTaskStatus();

        assertEquals(false,result);
    }

    /**
     * tests the scannerAddTask method by creating a fake input simulating a user.
     * creates a task with taskTitle "a", projectName "b", and dueDate "10-10-2020.
     * adds the task to a list, and then uses the getListElement to get the task from the list,
     * and confirms that the information inserted by the user is the same stored in the list
     */
    @Test
    public void testScannerAddTask()
    {
        ByteArrayInputStream fakeInput = new ByteArrayInputStream("a\r\nb\r\n10-10-2020\r\n1\n2\n4\n".getBytes());
        Todolist list1 = new Todolist(fakeInput);
        list1.scannerAdd();
        Task result = list1.getListElement(0);

        assertEquals("a", result.getTaskTitle());
        assertEquals("b", result.getProjectName());
        assertEquals("2020-10-10", result.getDueDate().toString());
    }

    /*@Test
    public void testGetDoDateMethod()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse("13-04-1999", formatter);
        System.out.println(localDate);
        Task t1 = new Task("task1", "project1", localDate);
        System.out.println(t1);
        LocalDate result = t1.getDueDate();

        assert("1999-04-13", result);*/
}

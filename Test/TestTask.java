import org.junit.Test;
import todolist.Task;

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

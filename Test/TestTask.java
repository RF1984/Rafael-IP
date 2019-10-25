import org.junit.Test;
import todolist.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * This class contains some tests for the Task class
 */
public class TestTask {

    /**
     * tests the getStatus, getName and getProject methods from the Task class
     */
    @Test
    public void testGetMethods() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse("13-04-1999", formatter);
        Task t1 = new Task("task1", "project1", localDate);
        Boolean getStatus = t1.getTaskStatus();
        String getName = t1.getTaskTitle();
        String getProject = t1.getProjectName();

        assertEquals(false, getStatus);
        assertEquals("task1", getName);
        assertEquals("project1", getProject);
    }

    /**
     * tests the set methods that are use when editing a task: done();changeTitle() and changeProject()
     * This method ends up testing also the get methods, making the preview test redundant.
     */
    @Test
    public void testChangeMethods() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse("13-04-1999", formatter);
        Task t1 = new Task("task1", "project1", localDate);
        t1.done();
        t1.changeTitle("changed title");
        t1.changeProject("changed project");

        assertEquals(true, t1.getTaskStatus());
        assertEquals("changed title", t1.getTaskTitle());
        assertEquals("changed project", t1.getProjectName());
    }
}
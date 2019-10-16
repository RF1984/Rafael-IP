package todolist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DateTest
{
    public static void main(String[] args)
    {
        int[] arr = new int[]{1,2,3};
        ArrayList<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(5);
        list.add(6);

        System.out.println("The array : " + arr);
        System.out.println("The list : " + list);

        Task t = null; //new Tasks("ti", "pr", null);
        System.out.println("the task : " + t);
        //t.printTasks();

        System.out.println("-----------------");

        // convert string to Local date
        String dateString = "22/03/2019";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        // pint the date using their (Local date class creator) format
        System.out.println("their: " + localDate);

        //convert the Local date to a string using my own format
        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd--MMM//yyyy");
        String s = format2.format(localDate);
        System.out.println("me: " + s);

        System.out.println("---------------------");
        System.out.print("Task: "  );
        System.out.println("nour");
        System.out.print("1: " + "\n" );
        System.out.print("2: "  );
        System.out.print("3: "  );
        System.out.print("4: "  );



    }
}

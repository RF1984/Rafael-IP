package todolist;

public class inputCommands {
    private static final String[] validInputs = new String[]{
            "add", "delete", "view", "done", "help"
    };
    public inputCommands(){}

    public boolean inInput (String aString){
    for(int i = 0; i < validInputs.length; i++)
        {
            if(validInputs[i].equals(aString))
                return true;
        }
    return false;
    }
    public void showCommands(){
        for(String command: validInputs) {
            System.out.println(command + " ");
        }
    }
}
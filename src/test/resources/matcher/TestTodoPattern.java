import java.util.regex.Pattern;

public class Todo {
    private final Pattern PATTERN = Pattern.compile(
            "@todo([\\s\\S]*?)@end" //will not me consiteded a todo.
    );
    //This is not a tdo
    private String string="  @todo\n" +
            "        title: PERL TODO\n" +
            "        body: |\n" +
            "            Test for perl file\n" +
            "        assignee: kujtimiihoxha\n" +
            "        labels: perl,todo\n" +
            "        milestone: v1.0.0\n" +
            "   @end ";

    public static void main(String[] args) {
        System.out.println("Todo");
    }

}
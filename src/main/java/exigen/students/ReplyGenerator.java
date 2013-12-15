package exigen.students;

/**
 * Generates some random reply
 *
 * @author Anna Kruglova
 */
public interface ReplyGenerator {

    String generate();

    int generateId();

    String getAnswer(int k);

}

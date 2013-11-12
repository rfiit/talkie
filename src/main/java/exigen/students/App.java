package exigen.students;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Anna Khasanova
 */
public class App {

    private static final String EXIT_WORD = "хватит";

    public static void main(String[] args) {
        ReplyGenerator replyGenerator = new DefaultReplyGenerator();
        System.out.println("Задайте свой вопрос:");
        try {
            talk(replyGenerator);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Окей. Пока!");
    }

    private static void talk(ReplyGenerator replyGenerator) throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Можно ли мне... ");
            String question = bufferRead.readLine().trim();
            if (question.isEmpty()) {
                continue;
            }
            if (isExitCommand(question)) {
                break;
            } else {
                printQuestion(question);
                printAnswer(replyGenerator);
            }
            System.out.println("\nПродолжим?");
        }
    }

    private static boolean isExitCommand(String input) {
        return EXIT_WORD.equalsIgnoreCase(input);
    }

    private static void printQuestion(String question) {
        String questionOut = "Можно ли тебе " + question;
        if (!questionOut.endsWith("?")) {
            questionOut += "?";
        }
        System.out.println(questionOut);
    }

    private static void printAnswer(ReplyGenerator replyGenerator) {
        System.out.println(replyGenerator.generate());
    }
}

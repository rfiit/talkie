package exigen.students;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generates reply based on random selection from the initial list of replies
 *
 * @author Anna Khasanova
 */
public class DefaultReplyGenerator implements ReplyGenerator {
    
    private List<String> answers = new ArrayList<String>();
    
    public DefaultReplyGenerator() {
        answers.add("Конечно! Что за вопрос!");
        answers.add("Хмм... ну попробуй....");
        answers.add("Я бы не советовал, но решать тебе.");
        answers.add("Можно, но ооочень осторожно");
        answers.add("И думать забудь!");
        answers.add("Ваш вопрос уже отправлен в ФСБ. Ждите, за вами выехали.");
    }
    
    @Override
    public String generate() {
        Random random = new Random();
        int selected = random.nextInt(answers.size());
        return answers.get(selected);
    }
}

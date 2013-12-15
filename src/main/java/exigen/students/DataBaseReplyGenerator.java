package exigen.students;

import java.util.*;

/**
 * Generates reply based on random selection from the initial list of replies
 *
 * @author Anna Khasanova
 */
public class DataBaseReplyGenerator implements ReplyGenerator {
    
    private Map<Integer, String> answers = new HashMap<Integer, String>();
    private int[] ids;

    public DataBaseReplyGenerator(Map<Integer, String> am) {
        ids = new int[am.size()];
        int i = 0;
        for (Map.Entry<Integer, String> me : am.entrySet()) {
            answers.put(me.getKey(), me.getValue());
            ids[i] = me.getKey();
            i++;
        }
    }
    
    @Override
    public String generate() {
        return answers.get(generateId());
    }

    @Override
    public int generateId() {
        Random random = new Random();
        int selected = random.nextInt(ids.length);
        return ids[selected];
    }

    public String getAnswer(int k) {
        return answers.get(k);
    }


}

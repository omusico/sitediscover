package com.androzic.sitediscover;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by baole on 12/8/2015.
 */
public class QuestionSet {
    public static final int IDLE = 0;
    public static final int STARTED = 1;
    public static final int FINISHED = 2;
    public ArrayList<Question> questions = new ArrayList<>();

    public int nextQuestion = 0;

    public int state = IDLE;
    private int score = 0;
    private int MAX_QUESTION = 5;

    public void reset () {
        score = 0;
        state = IDLE;
        nextQuestion = 0;
    }

    public void increaseScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public boolean hasNext() {
        return nextQuestion < questions.size();
    }

    public Question next() throws Throwable {

        Question question = questions.get(nextQuestion);
        nextQuestion++;
        return question;
    }

    public QuestionSet(Context context) {
        try {
            readData(context, "data");
        } catch (Throwable e) {
            Question q;
            q = new Question();
            q.question = "Đây là đâu?";
            q.anwser0 = "UBND Thành Phố";
            q.anwser1 = "Trại giam Chí Hòa";
            q.anwser2 = "Dinh Độc Lập";
            q.anwser3 = "Nhà thờ Tân Định";
            q.result = "Trại giam Chí Hòa";
            q.lat = 10.7771892;
            q.lng = 106.6687563;
            questions.add(q);

            q = new Question();
            q.question = "Đây là đâu?";
            q.anwser0 = "Việt Nam quốc tự";
            q.anwser1 = "Chùa Vĩnh Nghiêm";
            q.anwser2 = "Chùa Xá Lợi";
            q.anwser3 = "Chùa Minh Đăng Quang";
            q.result = "Chùa Minh Đăng Quang";
            q.lat = 10.8020709;
            q.lng = 106.7518541;
            questions.add(q);
            e.printStackTrace();
        }
    }



    void readData(Context context, String fileName) throws IOException, Throwable {

        InputStream is = context.getResources().getAssets().open(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        ArrayList<String> lines = new ArrayList<>();
        try {
            String line = br.readLine();
            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }

            int noLine = lines.size();

            for (int i = 0; i < MAX_QUESTION; i++) {
                int index =(int) (Math.random() * noLine);
                line = lines.get(index);
                Question question = convertLineToQuestion(line);
                if (question != null) {
                    questions.add(question);
                }

            }



        } finally {
            br.close();
        }
    }

    private Question convertLineToQuestion(String line) {
        String parts[] = line.split(",");
        if (parts.length != 9) {
            return  null;
        }

        Question q = new Question();
        //20, 'Đây là đâu?', 'UBND Thành Phố', 'Trường Marie Curie', 'Dinh Độc Lập', 'Trường Lê Quý Đôn', 'UBND Thành Phố', 10.7764543, 106.7007222

        q.question = parts[1];
        q.anwser0 = parts[2];
        q.anwser1 = parts[3];
        q.anwser2 = parts[4];
        q.anwser3 = parts[5];
        q.result = parts[6];
        q.lat = Double.parseDouble(parts[7]);
        q.lng = Double.parseDouble(parts[8]);

        return q;
    }


}

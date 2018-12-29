package com.dasteam.quiz.quizgame.gui.quiz.variants;

import com.dasteam.quiz.quizgame.model.question.AnswerModel;
import com.dasteam.quiz.quizgame.model.question.QuestionModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.dasteam.quiz.quizgame.provider.QuizProvider.provideRepository;

public class VariantsController {
    private Random random;

    public VariantsController() {
        random = new Random();
    }

    public void getQuestions(DataRetriever<List<QuestionModel>> retriever) {
        provideRepository().getQuestions(retriever);
    }

    public List<AnswerModel> halfData(List<AnswerModel> answers) {
        List<AnswerModel> halfAnswer = new ArrayList<>();
        for (AnswerModel answer : answers) {
            if (answer.isCorrect()) {
                halfAnswer.add(answer);
                break;
            }
        }
        AnswerModel randomAnswer = answers.get(random.nextInt(4));
        while (randomAnswer.isCorrect()) {
            randomAnswer = answers.get(random.nextInt(4));
        }
        halfAnswer.add(randomAnswer);

        return halfAnswer;
    }

    public int getRandomAnswer(List<AnswerModel> answers) {
        int rightAnswerPos = -1;
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).isCorrect()) {
                rightAnswerPos = i;
                break;
            }
        }
        return random.nextDouble() <= 0.7 ? rightAnswerPos : random.nextInt(4);
    }

    public int getRandomWrongAnswer(List<AnswerModel> answers) {
        int pos = random.nextInt(4);
        while (answers.get(pos).isCorrect()) {
            pos = random.nextInt(4);
        }
        return pos;
    }
}

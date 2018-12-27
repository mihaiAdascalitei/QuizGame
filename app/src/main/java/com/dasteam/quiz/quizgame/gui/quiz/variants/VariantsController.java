package com.dasteam.quiz.quizgame.gui.quiz.variants;

import com.dasteam.quiz.quizgame.model.question.QuestionModel;
import com.dasteam.quiz.quizgame.network.DataRetriever;

import java.util.List;

import static com.dasteam.quiz.quizgame.provider.QuizProvider.provideRepository;

public class VariantsController {

    public void getQuestions(DataRetriever<List<QuestionModel>> retriever) {
        provideRepository().getQuestions(retriever);
    }
}

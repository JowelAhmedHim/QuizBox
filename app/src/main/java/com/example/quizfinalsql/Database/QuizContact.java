package com.example.quizfinalsql.Database;

import android.provider.BaseColumns;

public final class QuizContact {

    public QuizContact() {
    }
    public static class QuestionTable implements BaseColumns{

        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "questions";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_ANSWER_NR = "answer";
        public static final String COLUMN_CATEGORY = "category";

    }
}

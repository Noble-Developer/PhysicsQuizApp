package com.example.android.physicsquizapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the submit button is clicked.
     */
    public void submitAnswers(View view) {
        // Find the users name
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        // check if the right answer of the first question is selected
        RadioButton firstQuestionOptionC = (RadioButton) findViewById(R.id.c_option);
        boolean firstAnswer = firstQuestionOptionC.isChecked();

        CheckBox firstCheckbox = (CheckBox) findViewById(R.id.a_checkbox);
        CheckBox secondCheckbox = (CheckBox) findViewById(R.id.b_checkbox);
        CheckBox thirdCheckbox = (CheckBox) findViewById(R.id.c_checkbox);
        CheckBox fourthCheckbox = (CheckBox) findViewById(R.id.d_checkbox);

        boolean secondAnswerA = firstCheckbox.isChecked();
        boolean secondAnswerB = secondCheckbox.isChecked();
        boolean secondAnswerC = thirdCheckbox.isChecked();
        boolean secondAnswerD = fourthCheckbox.isChecked();

        RadioButton thirdQuestionOptionA = (RadioButton) findViewById(R.id.q3_a_option);
        boolean thirdAnswer = thirdQuestionOptionA.isChecked();

        // Get the answer of the 4th question
        EditText fourthQuestion = (EditText) findViewById(R.id.q4_field);
        boolean fourthAnswer;
        if (fourthQuestion.getText().toString().equals("")) {
            fourthAnswer = false;
        } else {
            int fourthCorrectAnswer = Integer.parseInt(fourthQuestion.getText().toString());

            if (fourthCorrectAnswer == 15) {
                    fourthAnswer = true;
                } else {
                    fourthAnswer = false;

            }
        }

        // check if the right answer of the fifth question is selected
        RadioButton fifthQuestionOptionA = (RadioButton) findViewById(R.id.q5_option_a_radio_button);
        boolean fifthAnswer = fifthQuestionOptionA.isChecked();

        int score = calculateScore(firstAnswer, secondAnswerA, secondAnswerB, secondAnswerC, secondAnswerD, thirdAnswer, fourthAnswer, fifthAnswer);

        displayScore(score, name);

    }

    public int calculateScore(boolean firstAnswer, boolean secondAnswerA, boolean secondAnswerB, boolean secondAnswerC, boolean secondAnswerD, boolean thirdAnswer, boolean fourthAnswer, boolean fifthAnswer) {

        int score = 0;

        if (firstAnswer) {
            score = score + 1;
        }

        if (secondAnswerB && secondAnswerD && !secondAnswerA && !secondAnswerC) {
            score = score + 1;
        }

        if (thirdAnswer) {
            score = score + 1;
        }

        if (fourthAnswer) {
            score = score + 1;
        }

        if (fifthAnswer) {
            score = score + 1;
        }

        return score;
    }

    public void displayScore(int score, String name) {
        if (!name.equals("")) {

            if (score == 0) {
                Toast.makeText(this, "Come on, " + name + "! You scored 0 points! You can do better! Try again!", Toast.LENGTH_LONG).show();
            } else if (score < 3) {
                Toast.makeText(this, "Not bad, " + name + "! Your score is: " + score + "/5. You can do better! Try again!", Toast.LENGTH_LONG).show();
            } else if (score == 4) {
                Toast.makeText(this, "You are great " + name + "! Your score is: " + score + "/5. Only one right answer from being perfect!", Toast.LENGTH_LONG).show();
            } else if (score == 5) {
                Toast.makeText(this, "You are such a genious " + name + "! Your score is: " + score + "/5. IT'S JUST PERFECT! Congratulations!", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "You need to answer at least the first question!", Toast.LENGTH_LONG).show();
        }
    }
}

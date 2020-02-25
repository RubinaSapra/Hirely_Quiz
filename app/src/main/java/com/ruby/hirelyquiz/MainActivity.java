package com.ruby.hirelyquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button falseButton;
    public Button trueButton;
    public TextView questionTextView;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private int currentQuestionIndex = 0;
    private Question[] questionBank = new Question[]{
            new Question(R.string.question_declaration1,true),
            new Question(R.string.question_declaration2,false),
            new Question(R.string.question_declaration3,true),
            new Question(R.string.question_declaration4,true),
            new Question(R.string.question_declaration5,false),
            new Question(R.string.question_declaration6,true),
            new Question(R.string.question_declaration7,false),
            new Question(R.string.question_declaration8,false),
            new Question(R.string.question_declaration9,true),
            new Question(R.string.question_declaration10,true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        nextButton = findViewById(R.id.next_button);
        prevButton= findViewById(R.id.previous_button);

        questionTextView = findViewById(R.id.answer_text_view);
        falseButton.setOnClickListener(this);
       trueButton.setOnClickListener(this);
       nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.false_button:
               checkAnswer(false);
                break;
            case R.id.true_button:
               checkAnswer(true);
                break;
            case R.id.next_button:
                currentQuestionIndex= (currentQuestionIndex+1)%questionBank.length;
               updateQuestion();
                break;
            case R.id.previous_button:
                if(currentQuestionIndex>0) {
                    currentQuestionIndex = (currentQuestionIndex - 1) % questionBank.length;
                    updateQuestion();
                }
                break;
        }
    }
    private void updateQuestion(){
        Log.d("Current","OnClick "+ currentQuestionIndex);
        questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());
    }
    private void checkAnswer(boolean userChooseCorrect){
          boolean answerIsTrue = questionBank[currentQuestionIndex].isAnswerTrue();
          int toastMsgId=0;
          if(userChooseCorrect == answerIsTrue)
              toastMsgId = R.string.correct_answer;
          else
              toastMsgId=R.string.wrong_answer;

        Toast.makeText(MainActivity.this,toastMsgId,
                Toast.LENGTH_SHORT).show();
    }
}

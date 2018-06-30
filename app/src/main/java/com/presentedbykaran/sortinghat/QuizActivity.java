package com.presentedbykaran.sortinghat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class QuizActivity extends AppCompatActivity {

    private int gryffindorScore = 0;
    private int hufflepuffScore = 0;
    private int ravenclawScore = 0;
    private int slytherinScore = 0;

    final private int NUM_QUESTIONS = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        final String[] QUESTIONS = {
                "Dawn or dusk?",
                "Forest or river?",
                "Moon or stars?",
                "Black or White?",
                "Heads or Tails",
                "Left or Right?",
                "Four boxes are placed before you. Which would you try and open?",
                "You and two friends need to cross a bridge guarded by a river troll who insists on " +
                        "fighting one of you before he will let all of you pass. Do you:",
                "Once every century, the Flutterby bush produces flowers that adapt their scent to " +
                        "attract the unwary. If it lured you, it would smell of:",
                "One of your house mates has cheated in a Hogwarts exam by using a Self-Spelling Quill. " +
                        "Now he has come top of the class in Charms, beating you into second place. " +
                        "Professor Flitwick is suspicious of what happened. He draws you to one side " +
                        "after his lesson and asks you whether or not your classmate used a forbidden " +
                        "quill. What do you do?",
                "Which of the following do you find most difficult to deal with?",
                "You enter an enchanted garden. What would you be most curious to examine first?",
                "Four goblets are placed before you. Which would you choose to drink?",
                "What kind of instrument most pleases your ear?",
                "Which of the following would you most hate people to call you?",
                "After you have died, what would you most like people to do when they hear your name?",
                "How would you like to be known to history?",
                "A Muggle confronts you and says that they are sure you are a witch or wizard. Do you:",
                "Which nightmare would frighten you most?",
                "If you were attending Hogwarts, which pet would you choose to take with you?",
                "Given the choice, would you rather invent a potion that would guarantee you:",
                "If you could have any power, which would you choose?",
                "Which road tempts you most?",
                "Late at night, walking alone down the street, you hear a peculiar cry that you believe " +
                        "to have a magical source. Do you:",
                "What are you most looking forward to learning at Hogwarts?",
                "Which of the following would you most like to study?",
                "A troll has gone berserk in the Headmaster’s study at Hogwarts. It is about to smash, " +
                        "crush and tear several irreplaceable items and treasures. In which order would " +
                        "you rescue these objects from the troll’s club, if you could?",
                "Which would you rather be:",
        };
        final int questionsSize = QUESTIONS.length;


    }
}

package com.northcoders.calculatorapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView textArea;
    private Button one ;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button zero;
    private Button dot;
    private Button divider;
    private Button add;
    private Button multiply;
    private Button subtract;
    private Button equals;
    private Button clear;
    private String expression="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button one = findViewById(R.id.one);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                addToExpression();
            }
        });
        Button button = findViewById(R.id.clear);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Info", "onclick");
                clearText();
            }
        });
    }

    private void clearText(){
        textArea =  findViewById(R.id.textArea);

        Log.i("Info", "onclick1");
        textArea.setText("0");
    }
    private void addToExpression(){
        expression+= "1";
        textArea.setText(expression);
    }

    private void evaluateExpression(String input){
        Context context = Context.enter();
        context.setOptimizationLevel(-1);
        Scriptable scope = context.initStandardObjects();
        Object result = context.evaluateString(scope, input, "JavaScript",1,null);
        textArea.setText(result.toString());

    }
}
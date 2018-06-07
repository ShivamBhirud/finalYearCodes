package trigocalc.calcfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button c,add,sub,mul,div,sin,cos,tan;
    private EditText num1, num2, res;
    String numb1,numb2;
    double result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = (Button)findViewById(R.id.c);
        add = (Button)findViewById(R.id.add);
        sub = (Button)findViewById(R.id.sub);
        mul = (Button)findViewById(R.id.mul);
        div = (Button)findViewById(R.id.div);
        sin = (Button)findViewById(R.id.sin);
        cos = (Button)findViewById(R.id.cos);
        tan = (Button)findViewById(R.id.tan);
        num1 = (EditText)findViewById(R.id.num1);
        num2 = (EditText)findViewById(R.id.num2);
        res = (EditText)findViewById(R.id.res);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numb1 = num1.getText().toString();
                numb2 = num2.getText().toString();
                result = Double.parseDouble(numb1)+Double.parseDouble(numb2);
                res.setText(String.valueOf(result));
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numb1 = num1.getText().toString();
                numb2 = num2.getText().toString();
                result = Double.parseDouble(numb1)-Double.parseDouble(numb2);
                res.setText(String.valueOf(result));
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numb1 = num1.getText().toString();
                numb2 = num2.getText().toString();
                result = Double.parseDouble(numb1)*Double.parseDouble(numb2);
                res.setText(String.valueOf(result));
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numb1 = num1.getText().toString();
                numb2 = num2.getText().toString();
                result = Double.parseDouble(numb1)/Double.parseDouble(numb2);
                res.setText(String.valueOf(result));
            }
        });
        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numb1 = num1.getText().toString();
                result = Math.sin(Double.parseDouble(numb1));
                res.setText(String.valueOf(result));
            }
        });
        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numb1 = num1.getText().toString();
                numb2 = num2.getText().toString();
                result = Math.cos(Double.parseDouble(numb1));
                res.setText(String.valueOf(result));
            }
        });
        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numb1 = num1.getText().toString();
                numb2 = num2.getText().toString();
                result = Math.tan(Double.parseDouble(numb1));
                res.setText(String.valueOf(result));
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = 0;
                num1.setText(String.valueOf(a));
                num2.setText(String.valueOf(a));
                res.setText(String.valueOf(a));
            }
        });
    }

 /*
    @Override
    public void onClick(View view) {
        String numb1 = num1.getText().toString();
        String numb2 = num2.getText().toString();
        switch (view.getId()){
            case R.id.add:
                double result = Double.parseDouble(numb1)+Double.parseDouble(numb2);
                res.setText(String.valueOf(result));
                break;
            case R.id.sub:
                double result1 = Double.parseDouble(numb1) - Double.parseDouble(numb2);
                res.setText(String.valueOf(result1));
                break;
            case R.id.mul:
                double result2 = Double.parseDouble(numb1)*Double.parseDouble(numb2);
                res.setText(String.valueOf(result2));
                break;

            case R.id.div:
                double result3 = Double.parseDouble(numb1)/Double.parseDouble(numb2);
                res.setText(String.valueOf(result3));
                break;

            case R.id.sin:
                double result4 = Math.sin(Double.parseDouble(numb1));
                res.setText(String.valueOf(result4));
                break;

            case R.id.cos:
                double result5 = Math.cos(Double.parseDouble(numb1));
                res.setText(String.valueOf(result5));
                break;

            case R.id.tan:
                double result6 = Math.tan(Double.parseDouble(numb1));
                res.setText(String.valueOf(result6));
                break;

            case R.id.c:
                int a = 0;
                num1.setText(String.valueOf(a));
                num2.setText(String.valueOf(a));
                res.setText(String.valueOf(a));
                break;
        }

    }
    */
}

package javaPracticalsClg;

import java.awt.*;
import java.awt.event.*;

public class Awt extends Frame {
    Label l1, l2, ans;
    TextField num1, num2;
    Button calc;
    public Awt() {
        setLayout(new FlowLayout());

        l1 = new Label("Enter Num1: ");
        l2 = new Label("Enter Num2: ");
        ans = new Label("");
        num1 = new TextField(10);
        num2 = new TextField(10);
        calc = new Button("Calculate");

        add(l1);
        add(num1);
        add(l2);
        add(num2);
        add(calc);
        add(ans);
//
//        calc.addActionListener({
//                ans.setText(num1.getText() + num2.getText());
//        });
        
        setTitle("Calc");
        setSize(300,300);
        setVisible(true);

    }



    public static void main(String[] args) {
        Awt obj = new Awt();
    }
}

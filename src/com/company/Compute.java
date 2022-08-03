package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compute {

    private Double left;
    private Double right;
    private Double mid;
    private String op1;
    private String op2;

    /**
     * This was first try then I refactored which you can see in commented code
     **/
/*    public static String compute(String eq) {
        System.out.println(eq);

        String regex = "(?<left>\\d+(?:\\.\\d+)?)\\s*(?<op>[*\\/\\+\\-])\\s*(?<mid>\\d+(?:\\.\\d+)?)\\s*(?<op1>[*\\/\\+\\-])\\s*(?<right>\\d+(?:\\.\\d+)?)";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(eq);
        String formattedEq = null;

        if (matcher.find()) {

            Double left = Double.parseDouble(matcher.group("left"));
            Double right = Double.parseDouble(matcher.group("right"));
            Double middle = Double.parseDouble(matcher.group("mid"));
            String op = matcher.group("op");
            String op1 = matcher.group("op1");
            //System.out.println(String.format("%s %s %s %s %s = ", left, op, middle, op1, right));
            Double equation = -1.0;
            compute(compute(left, "*", middle), "+", right);

            if (!(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) && !(op1.equals("+") || op1.equals("-") || op1.equals("*") || op1.equals("/")))
                formattedEq += "Unknown Operator!";

            else {
                formattedEq = String.format("%s %s %s %s %s = ", left, op, middle, op1, right);

                // 4 p1 6 p2 2   --------------- p1: * /     p2: + -

                // 4 p2 6 p1 2
                switch (op) {
                    case "*":
                        switch (op1) {
                            case "+":
                                equation = (left * middle) + right;
                                break;
                            case "-":
                                equation = (left * middle) - right;
                                break;
                            case "*":
                                equation = (left * middle) * right;
                                break;
                            case "/":
                                equation = (left * middle) / right;
                                break;
                        }
                        break;
                    case "/":
                        switch (op1) {
                            case "+":
                                equation = (left / middle) + right;
                                break;
                            case "-":
                                equation = (left / middle) - right;
                                break;
                            case "*":
                                equation = (left / middle) * right;
                                break;
                            case "/":
                                equation = (left / middle) / right;
                                break;
                        }
                        break;
                    case "+":
                        switch (op1) {
                            case "+":
                                equation = left + middle + right;
                                break;
                            case "-":
                                equation = left + middle - right;
                                break;
                            case "*":
                                equation = left + (middle * right);
                                break;
                            case "/":
                                equation = left + (middle / right);
                                break;
                        }
                        break;
                    case "-":
                        switch (op1) {
                            case "+":
                                equation = left - middle + right;
                                break;
                            case "-":
                                equation = left - middle - right;
                                break;
                            case "*":
                                equation = left - (middle * right);
                                break;
                            case "/":
                                equation = left - (middle / right);
                                break;
                        }
                        break;
                }
                formattedEq += equation;
            }
        } else {
            if (eq.length() < 4) {
                eq = eq.concat("+0");
                formattedEq = compute(eq);
            } else
                formattedEq += "\n Unknown Operator !";
        }
        return formattedEq;
    }
  private static void compute(Double left, String op, Double right, String op1, Double middle) {

        compute(compute(left, op, middle), op1, right);
    }*/
    public void exec(String equation) {
        parse(equation);
        compute();
    }

    private void parse(String equation) {
        String regex = "(?<left>\\d+(?:\\.\\d+)?)\\s*(?<op>[*\\/\\+\\-])\\s*(?<mid>\\d+(?:\\.\\d+)?)\\s*(?<op1>[*\\/\\+\\-])\\s*(?<right>\\d+(?:\\.\\d+)?)";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(equation);
        String formattedEq = null;

        if (matcher.find()) {

            this.left = Double.parseDouble(matcher.group("left"));
            this.right = Double.parseDouble(matcher.group("right"));
            this.mid = Double.parseDouble(matcher.group("mid"));
            this.op1 = matcher.group("op");
            this.op2 = matcher.group("op1");

            List<String> validOps = Arrays.asList("+", "-", "*", "/");
            if (!validOps.contains(this.op1) || !validOps.contains(this.op2)) {
                System.out.println("Unknown Operator!");
                return;
            }

            formattedEq = String.format("%s %s %s %s %s = ", left, op1, mid, op2, right);
            System.out.println(formattedEq);
        }
    }

    private void compute() {
        List<String> p1Ops = Arrays.asList("*", "/");
        double rez;

        if (p1Ops.contains(op1)) {
            rez = binomialOperation(binomialOperation(left, op1, mid), op2, right);
        } else {
            rez = binomialOperation(left, op1, binomialOperation(mid, op2, right));
        }

        System.out.println(rez);
    }

    private Double binomialOperation(Double left, String op, Double right) {
        Double equation = null;

        switch (op) {
            case "+":
                equation = left + right;
                break;
            case "-":
                equation = left - right;
                break;
            case "*":
                equation = left * right;
                break;
            case "/":
                equation = left / right;
                break;
        }

        return equation;
    }

}
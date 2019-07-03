package myapp;

import calcengine.Adder;
import calcengine.CalculateBase;
import calcengine.CalculateHelper;
import calcengine.Divider;
import calcengine.DynamicHelper;
import calcengine.InvalidStatementException;
import calcengine.MathEquation;
import calcengine.MathProcessing;
import calcengine.Multiplier;
import calcengine.PowerOf;
import calcengine.Subtracter;

public class Main {
    public static void main(String[] args) {
        // useMathEquation();
        // useCalculatorBase();
        // useCalcHelper()
        String[] statements = {
            "add 25.0 92.0",
            "power 5.0 2.0", // 5.0 ^ 2.0 = 25.0
 
        };
        
        DynamicHelper helper = new DynamicHelper(new MathProcessing[] {
            new Adder(),
            new PowerOf()
        });
        for (String statement: statements) {
            String output = helper.process(statement);
            System.out.println(output);

        }
    }

    static void useCalcHelper(){

        String[] statements = {
            "add 1.0", // Error: incorrect number of values
            "add xx 25.0", // Error: non numeric data
            "addX 0.0 0.0", // Error: invalid command 
            "divide 100.0 50.0",
            "add 25.0 92.0",
            "subtract 225.0 17.0",
            "multiply 11.0 3.0"
        };
        CalculateHelper helper = new CalculateHelper();
        for (String statement: statements) {
            try{ 
                helper.process(statement);
                System.out.println(helper);
            } catch (InvalidStatementException e) {
                System.out.println(e.getMessage());
                if (e.getCause() != null)
                    System.out.println("Original exception: " + e.getCause().getMessage());
            }
        }
    }

    static void useMathEquation() {
        MathEquation[] equations = new MathEquation[4];
        equations[0] = new MathEquation('d', 100.0d, 50.0d);
        equations[1] = new MathEquation('a', 25.0d, 92.0d);
        equations[2] = new MathEquation('s', 225.0d, 17.0d);
        equations[3] = new MathEquation('m', 11.0d, 3.0d);

        for (MathEquation equation : equations) {
            equation.execute();
            System.out.print("result = ");
            System.out.println(equation.getResult());
            }

        System.out.println();
        System.out.println("Using Overloads");

        // double leftDouble = 9.0d;
        // double rightDouble = 4.0d;
        int leftint = 9;
        int rightint = 4;

        MathEquation equationOverload = new MathEquation('d');

        equationOverload.execute(leftint, rightint);
        System.out.print("result=");
        System.out.println(equationOverload.getResult());

        equationOverload.execute((double)leftint, rightint);
        System.out.print("result=");
        System.out.println(equationOverload.getResult());
    }

    static void useCalculatorBase() {
        System.out.println();
        System.out.println("Using Inheritance");

        CalculateBase[] calculators = {
            new Divider(100.0d, 50.0d),
            new Adder(25.0d, 92.0d),
            new Subtracter(225.0d, 17.0d), 
            new Multiplier(11.0d, 3.0d)
        };

        for (CalculateBase calculator: calculators) {
            calculator.calculate();
            System.out.print("result=");
            System.out.println(calculator.getResult());
        }
    }
  
    public static MathEquation create (double leftVal, double rightVal, char opCode) {
        MathEquation equation = new MathEquation();
        equation.setLeftVal(leftVal);
        equation.setRightVal(rightVal);
        equation.setOpCode(opCode);

        return equation;
    }

}
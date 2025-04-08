package Homework;

import java.util.*;

public class Calculater {
    public static final String ERRORCONSTANT="88888888";
    private static final Float ERRORFLOAT=Float.parseFloat(ERRORCONSTANT);
    public ArrayList<Character> findOperator(String input)
    {
        ArrayList<Character> operators = new ArrayList<>();
        String[] output=input.split("[^-+*/%()]");
        Arrays.stream(output).forEach(x-> {
            if(!x.isEmpty())
            {
                for (int i = 0; i < x.length(); i++) {
                    operators.add(x.charAt(i));
                }
            }
        });
        return operators;
    }
    public ArrayList<Object> findNum(String input, HashMap<String,Number> Nums) throws VarUndefineException, VarUnassignedException {
        ArrayList<Object> nums = new ArrayList<>();
        String[] output=input.split("[-+*/%()=?]");
        for(String x:output)
        {
            if(x.isEmpty()) continue;
            if(x.contains(".")) {nums.add(Float.parseFloat(x));continue;}
            try{
                nums.add(Integer.parseInt(x));
            }
            catch(NumberFormatException e){
            if(Nums.containsKey(x))
            {
                if(ERRORCONSTANT.equals(Nums.get(x).toString())||ERRORFLOAT.toString().equals(Nums.get(x).toString())) throw new VarUnassignedException();
                nums.add(Nums.get(x));
            }
            else throw new VarUndefineException();}
        }
        return nums;
    }
    public void calculate(LinkedList<Object> nums, Character operator) throws ErrorExpressionException
    {
        if(nums.size()<2) throw new ErrorExpressionException();
        Object num1 = nums.removeFirst();
        Object num2 = nums.removeFirst();
        if(num1 instanceof Integer && num2 instanceof Integer)
        {
            nums.addFirst(calculateNum((Integer) num1,(Integer) num2,operator));
        }
        else
        {
            nums.addFirst(calculateNum(Float.parseFloat(num1.toString()),Float.parseFloat(num2.toString()),operator));
        }
    }
    public Integer calculateNum(Integer num2,Integer num1,Character operator) throws ErrorExpressionException
    {
        switch(operator){
            case '+':{return num1+num2;}
            case '-':{return num1-num2;}
            case '*':{return num1*num2;}
            case '/':{return num1/num2;}
            case '%':{return num1%num2;}
            default: {throw new ErrorExpressionException();}
        }
    }
    public Float calculateNum(Float num2,Float num1,Character operator) throws ErrorExpressionException
    {
        switch(operator){
            case '+':{return num1+num2;}
            case '-':{return num1-num2;}
            case '*':{return num1*num2;}
            case '/':{return num1/num2;}
            case '%':{return num1%num2;}
            default: {throw new ErrorExpressionException();}
        }

    }
    public Number calculate(String expression,HashMap<String,Number> varToNumber) throws ErrorExpressionException, VarUndefineException, VarUnassignedException {
        ArrayList<Object>Nums=findNum(expression,varToNumber);
        ArrayList<Character> operators = findOperator(expression);
        LinkedList<Object> nums = new LinkedList<>();
        LinkedList<Character> Operators=new LinkedList<>();
        int j=0;
        nums.addFirst(Nums.get(j++));
        try{
            for (int i = 0; i < operators.size(); i++) {
                if(operators.get(i)=='(') {Operators.addFirst('(');}
                else if(operators.get(i)==')') {
                    if(!Operators.isEmpty())
                    {
                        while(Operators.getFirst()!='('){
                            calculate(nums,Operators.removeFirst());
                        }
                    }
                    Operators.removeFirst();
                }
                else if(operators.get(i)=='+' ||operators.get(i)=='-')
                {
                    if(!Operators.isEmpty()){
                        while(!Operators.isEmpty()&&Operators.getFirst()!='('){
                            calculate(nums,Operators.removeFirst());
                        }
                    }
                    nums.addFirst(Nums.get(j++));
                    Operators.addFirst(operators.get(i));
                }
                else if(operators.get(i)=='*' ||operators.get(i)=='/'||operators.get(i)=='%')
                {
                    if(!Operators.isEmpty()){
                        while(!Operators.isEmpty()&&Operators.getFirst()!='('&&Operators.getFirst()!='+'&&Operators.getFirst()!='-'){
                            calculate(nums,Operators.removeFirst());
                        }
                    }
                    nums.addFirst(Nums.get(j++));
                    Operators.addFirst(operators.get(i));
                }
            }
        }catch(IndexOutOfBoundsException e){
            throw new ErrorExpressionException();
        }
        try{
            while(!Operators.isEmpty())
            {
                calculate(nums,Operators.removeFirst());
            }
        }catch(Exception e){
            throw new ErrorExpressionException();
        }
        if(nums.size()!=1) throw new ErrorExpressionException();
        return (Number)nums.get(0);
    }
}

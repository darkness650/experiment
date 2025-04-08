package Homework;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

@Slf4j
public class Main {

    public static HashMap<String,Number> handle(ArrayList<String> language) throws VarUndefineException
    {
        HashMap<String,Number> varToNums = new HashMap<>();
        for(String str:language)
        {
            String[] words = str.split("[ =;]");
            if(words[0].equals("int"))
            {
                if(words.length==3)
                    varToNums.put(words[1],Integer.parseInt(words[2]));
                else
                    varToNums.put(words[1],Integer.parseInt(Calculater.ERRORCONSTANT));
            }
            else if(words[0].equals("float")) {
                if(words.length==3)
                    varToNums.put(words[1], Float.parseFloat(words[2]));
                else varToNums.put(words[1],Float.parseFloat(Calculater.ERRORCONSTANT));
            }
            else
            {
                if(varToNums.containsKey(words[0]))
                {
                    if(varToNums.get(words[0]) instanceof Float)
                        varToNums.replace(words[0],Float.parseFloat(words[1]));
                    else
                        varToNums.replace(words[0],Integer.parseInt(words[1]));
                }
                else
                    throw new VarUndefineException();
            }
        }
        return varToNums;
    }
    public static void main(String[] args) {
        ArrayList<String> input = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String str;
        StringBuilder logInput= new StringBuilder();
        str = scanner.nextLine();
        try{
            while(!str.isEmpty())
            {
                logInput.append("\n");
                logInput.append(str);
                input.add(str);
                str = scanner.nextLine();
            }
        }
        catch(Exception e){}
        //log.info(logInput.toString());
        String expression=input.removeLast();
        Calculater calculater = new Calculater();
        HashMap<String,Number> varToNumber;
        try{
            varToNumber=handle(input);
        } catch (VarUndefineException e) {
            log.error(logInput.toString()+"\n"+e.getMessage());
            return;
        }
        try{
            Number consequence=calculater.calculate(expression,varToNumber);
            if(consequence instanceof Integer) log.info(logInput.toString()+"\n"+"consequence: "+consequence);
            else log.info(String.format(logInput.toString()+"\n"+"consequence: %.2f",consequence));
        }
        catch (ErrorExpressionException e) {
            log.error(logInput.toString()+"\n"+e.getMessage());
        } catch (VarUndefineException e) {
            log.error(logInput.toString()+"\n"+e.getMessage());
        } catch (VarUnassignedException e) {
            log.error(logInput.toString()+"\n"+e.getMessage());
        }
    }
}
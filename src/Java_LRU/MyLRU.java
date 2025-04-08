package Java_LRU;

import java.util.*;

public class MyLRU {
    private static HashMap<Integer, String> data;
    private static HashMap<Integer, Integer> sequence;
    private static LinkedList<Integer> queue;
    private static int maxSize;
    private static int maxClear=80;
    static{
        sequence = new HashMap<>();
        data = new HashMap<>();
        queue = new LinkedList<>();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int operationNum;
        String[] nums=sc.nextLine().split(" ");
        maxSize = Integer.parseInt(nums[0]);
        operationNum = Integer.parseInt(nums[1]);
        for (int i = 0; i < operationNum; i++) {
            String operation=sc.next();
            String data=sc.nextLine();
            switch (operation){
                case "put":put(data);break;
                case "get":get(data);break;
            }
        }
    }
    public static void put(String dataString){
        String[] str=dataString.trim().split(";");
        for(int i=0;i<str.length;i++){
            String[]temp=str[i].split(",");
            if(!data.containsKey(Integer.parseInt(temp[0]))){
                data.put(Integer.parseInt(temp[0]),temp[1]);
                sequence.put(Integer.parseInt(temp[0]),0);
                queue.add(Integer.parseInt(temp[0]));
            }
            else
            {
                Integer index=Integer.parseInt(temp[0]);
                sequence.replace(index,sequence.get(index)+1);
                data.replace(index,temp[1]);
                queue.add(Integer.parseInt(temp[0]));
                if(queue.size()>maxClear) clear();
            }
        }
        while(data.size()>maxSize){
            Integer index=queue.removeFirst();
            if(sequence.get(index)==0)
            {
                data.remove(index);
            }
            else
            {
                sequence.replace(index,sequence.get(index)-1);
            }
        }

    }
    public static void get(String dataString){
        ArrayList<String> consequence=new ArrayList<>();
        String[] str=dataString.trim().split(";");
        for(int i=0;i<str.length;i++){
            Integer index=Integer.parseInt(str[i]);
            if(data.containsKey(index)){
                consequence.add(data.get(index));
                sequence.replace(index,sequence.get(index)+1);
                queue.add(index);
                if(queue.size()>maxClear) clear();
            }
            else  consequence.add("null");
        }
        for(int i=0;i<consequence.size();i++){
            System.out.print(consequence.get(i));
            if(i!=consequence.size()-1) System.out.print(";");
            else System.out.println(";");
        }
    }
    public static void clear(){
        Iterator it= queue.iterator();
        while(it.hasNext()){
            Integer index=(Integer) it.next();
            if(sequence.get(index)>0)
            {
                it.remove();
                sequence.replace(index,sequence.get(index)-1);
            }
        }
    }
}

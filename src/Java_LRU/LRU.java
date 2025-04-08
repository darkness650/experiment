package Java_LRU;

import java.util.*;
import java.util.stream.Stream;

public class LRU {
    private static HashMap<Integer, DoubleLinkedList.ListNode> data;
    private static DoubleLinkedList sequence;
    private static int maxSize;
    static{
        sequence = new DoubleLinkedList();
        data = new HashMap<>();
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
                DoubleLinkedList.ListNode tem=new DoubleLinkedList.ListNode(Integer.parseInt(temp[0]),temp[1]);
                sequence.addEnd(tem);
                data.put(Integer.parseInt(temp[0]),tem);
            }
            else
            {
                DoubleLinkedList.ListNode tem=new DoubleLinkedList.ListNode(Integer.parseInt(temp[0]),temp[1]);
                DoubleLinkedList.ListNode delete=data.remove(Integer.parseInt(temp[0]));
                sequence.remove(delete);
                sequence.addEnd(tem);
                data.put(Integer.parseInt(temp[0]),tem);
            }
        }
        while(sequence.size>maxSize){
            DoubleLinkedList.ListNode temp=sequence.removeFirst();
            data.remove(temp.key);
        }
    }
    public static void get(String dataString){
        ArrayList<String> consequence=new ArrayList<>();
        String[] str=dataString.trim().split(";");
        for(int i=0;i<str.length;i++){
            Integer index=Integer.parseInt(str[i]);
            if(data.containsKey(index)){
                consequence.add(data.get(index).value);
                DoubleLinkedList.ListNode tem=new DoubleLinkedList.ListNode(index,data.get(index).value);
                DoubleLinkedList.ListNode delete=data.remove(index);
                data.put(index,tem);
                sequence.remove(delete);
                sequence.addEnd(tem);
            }
            else  consequence.add("null");
        }
        for(int i=0;i<consequence.size();i++){
            System.out.print(consequence.get(i));
            if(i!=consequence.size()-1) System.out.print(";");
            else System.out.println(";");
        }
    }

}
class DoubleLinkedList {
    private ListNode head;
    private ListNode tail;
    int size;

    public DoubleLinkedList() {
        this.head = new ListNode(0, "0");
        this.tail = new ListNode(0, "0");
        this.head.next = tail;
        this.tail.prev = head;
        this.size = 0;

    }

    public void remove(ListNode node) {
        if (node == head || node == tail) throw new RuntimeException("node is can't to be head or tail");
        ListNode prev = node.prev;
        ListNode next = node.next;
        prev.next = next;
        next.prev = prev;
        size--;
    }


    public ListNode removeFirst() {
        if (head.next != null) {
            ListNode deleteHead = head.next;
            remove(deleteHead);
            return deleteHead;
        }
        return null;
    }

    public void addEnd(ListNode node) {
        if (node == null) throw new RuntimeException("node is can't to be null");
        ListNode tailPrev = tail.prev;
        tailPrev.next = node;
        node.prev = tailPrev;
        node.next = tail;
        tail.prev = node;
        size++;
    }

    static class ListNode {
        int key;
        String value;
        ListNode prev;
        ListNode next;

        public ListNode(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}


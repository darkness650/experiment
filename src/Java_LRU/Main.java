package Java_LRU;

import java.util.*;
import java.lang.*;
class now
{
   public int select(ArrayList<Integer> nums, int numbers, int k)
    {
       if(numbers<=5)
       {
           nums.sort(Comparator.naturalOrder());
           return nums.get(k-1);
       }
       ArrayList<Integer> middles=new ArrayList<>();
       for(int i=0;i<numbers;i+=5)
       {
           int end=Math.min(i + 5, numbers-1);
           ArrayList<Integer> now=new ArrayList<>();
           for(int then=i;then<=end;then++)
           {
               now.add(nums.get(then));
           }
           now.sort(Comparator.naturalOrder());
           middles.add(now.get(now.size()/2));
       }
       int middle_number=select(middles, middles.size(), middles.size()/2);
       ArrayList<Integer> left=new ArrayList<>();
       ArrayList<Integer> right=new ArrayList<>();
       for(Integer nku:nums)
       {
           if(nku<middle_number)
           {
               left.add(nku);
           }
           else
           {
               right.add(nku);
           }
       }
       if(left.size()==k-1)
       {
           return middle_number;
       }
       else
       {
           if(left.size()>k-1)
           {
               return select(left,left.size(),k);
           }
           else
           {
               if(right.size()==numbers)//数组里只有一种数字
                   return right.getFirst();
               else
                   return select(right,right.size(),k-left.size());
           }
       }
    }
}
public class Main {
    public static void main(String[] arg)
    {
        Scanner sc=new Scanner(System.in);
        ArrayList<Integer> nu=new ArrayList<>();
        String[] nexts=sc.nextLine().split(" ");
        for(String i:nexts) {
            nu.add(Integer.parseInt(i));
        }
        int k=sc.nextInt();
        System.out.println(new now().select(nu,nu.size(), k));

    }
}

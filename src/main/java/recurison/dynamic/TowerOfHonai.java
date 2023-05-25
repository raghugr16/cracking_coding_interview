package recurison.dynamic;

import java.util.Arrays;
import java.util.Stack;

public class TowerOfHonai {

    public static void main(String[] args) {
        int n=3;
        Tower[] towers = new Tower[n];
        for(int i = 0; i< 3; i++){
            String name = null;
            if(i==0)
                name = "source";
            if(i==1)
                name = "dest";
            if(i==2)
                name = "buff";

            towers[i] = new Tower(i, name);
        }
        for(int i=4;i>=0;i--){
            towers[0].add(i);
        }
        towers[0].moveDisks(5,towers[2],towers[1]);
        Arrays.stream(towers).forEach(System.out::println);
    }

    static class Tower{
        private String name;
        private Stack<Integer> disks;
        private int index;

        public Tower(int i, String name){
            disks = new Stack<>();
            index = i;
            this.name = name;
        }


        @Override
        public String toString() {
            return "Tower{" +
                    "name='" + name + '\'' +
                    ", disks=" + disks +
                    ", index=" + index +
                    '}';
        }

        public int index(){
            return index;
        }

        public void add(int d){
            if(!disks.isEmpty() && disks.peek() <= d){
                System.out.println("Error placing the disk " + d);

            } else {
                disks.push(d);
            }
        }

        public void moveToTop(Tower t){
            int top = disks.pop();
            t.add(top);
        }

        public void moveDisks(int n, Tower destination, Tower buffer){
            if(n>0){
                moveDisks(n-1,buffer,destination);
                moveToTop(destination);
                buffer.moveDisks(n-1,destination, this);
            }
        }
    }
}

package recurison.dynamic;

import java.util.ArrayList;
import java.util.HashMap;

public class Printpermutation {

    public static void main(String[] args) {
        String str = "aabbbbc";

        ArrayList<String> result = new ArrayList<>();
        HashMap<Character,Integer> map = new HashMap<>();
        map = buildFrequencyTable(str);
        printPerms(map,"", str.length(),result);
        System.out.println(result.toString());
    }

    private static HashMap<Character, Integer> buildFrequencyTable(String str) {
        HashMap<Character,Integer> map = new HashMap<>();
        for(char c : str.toCharArray()){
            if(!map.containsKey(c)){
                map.put(c,0);
            }
            map.put(c,map.get(c) + 1);
        }
        return map;
    }

    private static void printPerms(HashMap<Character, Integer> map, String prefix, int remaining, ArrayList<String> result) {
        /* Base case permutation has completed */
        if(remaining == 0){
            result.add(prefix);
            return;
        }

        for(Character c : map.keySet()){
            int count = map.get(c);
            if(count > 0){
                map.put(c, count -1);
                printPerms(map, prefix + c, remaining -1, result);
                map.put(c,count);
            }
        }
    }
}

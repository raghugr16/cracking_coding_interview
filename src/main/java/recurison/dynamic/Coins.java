package recurison.dynamic;

public class Coins {

    public static void main(String[] args) {
        Coins coins = new Coins();
        System.out.println(coins.makeChange(100));
    }

    int makeChange(int n){
        int[] denoms = {25, 10, 5, 1};
        return makeChange(n, denoms, 0);
    }

    int makeChange(int amount, int[] denoms, int index){
        System.out.println("int amount = "+ amount + " index = "+ index);
        if(index >= denoms.length - 1){
            return 1;
        }
        int denomAmount = denoms[index];
        int ways = 0;
        for(int i=0;  i * denomAmount <= amount; i++){
            int amountRemaining = amount - i *denomAmount;
            ways += makeChange(amountRemaining, denoms, index + 1);
        }
        return ways;
    }
}

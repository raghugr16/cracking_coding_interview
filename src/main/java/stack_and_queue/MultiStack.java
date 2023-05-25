package stack_and_queue;

import java.util.EmptyStackException;

public class MultiStack {

    private StackInfo info[];
    private int values[];

    private class StackInfo{
        public int start, size, capacity;

        /*
            StackInfo is a simple class that holds a set of data about each stack.
            It does not hold the actual items in the stack. We could have done this with
            a just a actual bunch of individual variables, but that's messy and
            doesn't gain us much.
         */
        public StackInfo(int start, int capacity){
            this.capacity = capacity;
            this.start = start;
        }

        /*
            Check if an index on the full array is within the stack boundaries.
            The stack can wrap around to the start of the array.
         */
        public boolean isWithinStackCapacity(int index){
            /* If outside of the bounds of array, return false */
            if(index < 0 || index >= values.length){
                return false;
            }

            /* If index wraps around, adjust it */
            int contiguousIndex = index < start ? index + values.length : index;
            int end = start + capacity;
            return start  <= contiguousIndex && contiguousIndex <end;
        }

        public int lastCapacityIndex(){
            int index = start + capacity - 1;
            return adjustIndex(index);
        }

        public int lastElementIndex(){
            int index = start + size - 1;
            return adjustIndex(index);
        }

        public boolean isFull(){
            return size == capacity;
        }

        public boolean isEmpty(){
            return size == 0;
        }
    }

    public MultiStack(int numberOfStacks, int defaultSize){
        /* create metadata for all the stacks */
        info = new StackInfo[numberOfStacks];
        for(int i= 0; i < numberOfStacks; i++){
            info[i] =  new StackInfo(defaultSize * i, defaultSize);
        }
        values = new int[defaultSize * numberOfStacks];
    }

    /*
        Push value onto stack num, shifting/expanding the stacks if necessary.
        Throws exception if all the stacks are full.
     */
    public void push(int stackNum, int value) throws FullStackException{
        if(allStacksAreFull()){
            throw new FullStackException();
        }

        /* If this stack is Full. Expand it */
        StackInfo stack = info[stackNum];
        if(stack.isFull()){
            expand(stackNum);
        }

        /*
           Find the index of the top element in the array + 1,
           increment the stack pointer.
         */
        stack.size++;
        int index = stack.lastElementIndex();
        values[index] = value;
    }

    /*
        Get top element of the stack.
     */
    public int pop(int stackNum){
        StackInfo stack = info[stackNum];
        if(stack.isEmpty()){
            throw new EmptyStackException();
        }

        /* Remove last Element */
        int value = values[stack.lastElementIndex()];
        values[stack.lastElementIndex()] = 0; // Clear Item
        stack.size--;
        return value;
    }

    /* Get top element of the stack */
    public int peek(int stackNum){
        StackInfo stack = info[stackNum];
        return values[stack.lastElementIndex()];
    }


    /*
      Shift items in the stack over by one element. If we have available capacity. then
      we will end up shrinking the stack by one element.
      If we dont have available capacity, then we will need to shift the next stack
      over too.
     */
    private void shift(int stackNum){
        System.out.println("//// Shifting the stack " + stackNum);
        StackInfo stack =  info[stackNum];
        /*
           If this stack is at its full capacity, then you need to move the next
           stack over by one element. This stack can claim the freed index.
         */
        if(stack.size >= stack.capacity){
            int nextStack = (stackNum + 1) % info.length;
            shift(nextStack);
            stack.capacity++;
        }

        /* Shift all elements in stack over by one */
        int index = stack.lastCapacityIndex();
        while(stack.isWithinStackCapacity(index)){
            values[index] = values[previousIndex(index)];
            index = previousIndex(index);
        }

        /* Adjust Stack Data. */
        values[stack.start] = 0; // Clear Item
        stack.start = nextIndex(stack.start); // Move Start
        stack.capacity--;
    }

    /* Expand the stack by shifting over other stacks */
    private void expand(int stackNum){
        shift((stackNum + 1) % info.length);
        info[stackNum].capacity++;
    }

    /* Returns the number of items present in the stack */
    public int numberOfElements(){
        int size = 0;
        for (StackInfo stackInfo: info){
            size += stackInfo.size;
        }
        return size;
    }

    /* Returns true if All Stacks are full. */
    public boolean allStacksAreFull(){
        return numberOfElements() == values.length;
    }


    /*
       Adjust index to be within the range of 0 --> length -1
     */
    private  int adjustIndex(int index){
        /*
          Java's mod operator can return neg values, For Example, (-11 % 5 )
          will return -1, not 4. We actually want the value to be 4
          ( Since we are wrapping around the index).
         */
        int max = values.length;
        return ((index % max) + max) % max;
    }

    /* Get Index after this index, adjusted for wrap around */
    private int nextIndex(int index){
        return adjustIndex(index + 1);
    }

    /* Get Index before this index, adjusted for wrap around */
    private int previousIndex(int index){
        return adjustIndex(index - 1);
    }


    public static void main(String[] args) throws FullStackException {
        MultiStack multiStack = new MultiStack(3, 5);
        multiStack.push(0,13);
        multiStack.push(2,1);
        multiStack.push(2,2);
        multiStack.push(2,3);
        multiStack.push(2,4);
        multiStack.push(2,5);
        multiStack.push(2,6);


        multiStack.printArray();
        multiStack.printStackSize();

        multiStack.push(1,7);
        multiStack.push(1,8);
        multiStack.push(1,9);
        multiStack.push(1,10);
        multiStack.push(1,11);

        multiStack.printArray();
        multiStack.printStackSize();

        multiStack.push(1,12);

        multiStack.printArray();
        multiStack.printStackSize();


    }

    public void printArray(){
        for (int i =0;i<values.length; i++){
            System.out.println("index "+ i + ": "+ values[i]);
        }
    }

    public void printStackSize(){
        for(int i= 0; i < info.length; i++){
            System.out.println("Stack "+ i + " : size : "+ info[i].size);
        }
    }
}

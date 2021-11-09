// hw03 : java bytecode 
// Test.j -> Test.java

public class Test{
    public void Test(){
        return ; 
    }

    public static int add(int i_0, int i_1){
        int i_2 = i_0 + i_1;
        return i_2;
    }

    public static void main(String[] args){
        final int i_0 = 33;
        final int i_1 = 1;
        int i_2 = Test.add(i_0, i_1);
        System.out.println(i_2);
        return ;
    }
}


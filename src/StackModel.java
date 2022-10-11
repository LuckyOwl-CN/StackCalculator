import java.util.Stack;

public class StackModel {
    private Stack<Character> optr = new Stack<>();
    private Stack<Integer> opnd = new Stack<>();
    private String expression = null;
    private char curr;
    enum Priority{
        HIGH, EQUAL, LOW;
    }
    enum StackPosition{
        STACKIN,STACKOUT;
    }

    //清空栈、调用expression
    public void _init(){
        getExpressoion();
    }

    //获得用户输入
    private void getExpressoion(){

    }

    //获取字符并做出相应处理
    private char getCurr(){

    }

    //比较栈内外优先级 in>out：True
    private Priority comparePriority(char optr_in, char optr_out){

    }

    //输入操作符optr及栈位置，输出优先级表中对应优先级
    private int priorityTable(char optr, StackPosition stackPosition){

    }


}

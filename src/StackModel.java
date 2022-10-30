import java.util.Stack;

public class StackModel {
    private Stack<Character> optr = new Stack<>();
    private Stack<Double> opnd = new Stack<>();
    private String expression = null;
    private String curr;
    private String legaloptr = "+-*/%^&()=";
    private String legalopnd = "1234567890.";
    private int curr_opnd;
    private char curr_optr;
    private int exp_positon = 0;
    private boolean calError = false;
    private String sublegaloptr = "+-*/%^&=";

    enum Priority {
        HIGH, EQUAL, LOW;
    }

    enum StackPosition {
        STACKIN, STACKOUT;
    }

    //清空栈、调用expression
    public void _init() {
        System.out.println("Initing...");
        getExpressoion();
        preProcess();
        if(calError){
            System.out.println("CalError!");
        }
        else {
            double answer = executeCal();
            if(answer == 0 && calError){
                System.out.println("CalError!");
            }
            else System.out.println(answer);
        }
    }

    private void preProcess(){
        this.expression = this.expression.replace(" ","");
        int num = 0;
        for (int i = 0; i < expression.length(); i++) {
            if(!legaloptr.contains(expression.substring(i,i+1)) &&
                    !legalopnd.contains(expression.substring(i,i+1))){
                calError = true;
                return;
            }
            if(expression.charAt(i) == '('){
                num++;
            }
            else if(expression.charAt(i) == ')'){
                num--;
            }
            if(num < 0){
                calError = true;
                return;
            }
            if(i != 0 && i+1 < expression.length()){
                if(sublegaloptr.contains(expression.substring(i-1,i)) &&
                        expression.charAt(i) == '-' && !legalopnd.contains(expression.substring(i+1,i+2))){
                    calError = false;
                    continue;
                }
            }
            else if(i != 0 && i < expression.length()){
                if(sublegaloptr.contains(expression.substring(i-1,i)) &&
                        sublegaloptr.contains(expression.substring(i,i+1))){
                    calError = true;
                    return;
                }
            }
        }
        if(num > 0){
            calError = true;
            return;
        }
        System.out.println(expression);
    }

    //获得用户输入(调用UIModel端代码)
    private void getExpressoion() {
        this.expression = "5 + ( 7 - 9) / (3 - (2 *2)) * (4^2)=";
    }

    //获取字符或字符串
    private String getCurr(int curr) {
        String currstring = "";
        if(curr != 0 && curr+1 < expression.length() &&
                expression.charAt(curr) == '-' &&
                legalopnd.contains(expression.substring(curr+1,curr+2)) &&
                !legalopnd.contains(expression.substring(curr-1,curr))||
                legalopnd.contains(expression.substring(curr,curr+1))) {
            currstring += expression.substring(curr,curr+1);
            curr++;
            for (; curr < expression.length(); curr++) {
                if(legalopnd.contains(expression.substring(curr,curr+1))){
                    currstring += expression.substring(curr,curr+1);
                }
                else {
                    exp_positon = curr;
                    return currstring;
                }
            }
        }
        else {
            currstring += expression.substring(curr,curr+1);
            exp_positon = curr + 1;
            return currstring;
        }
        return currstring;
    }

    //比较栈内外优先级 in>out：True
    private Priority comparePriority(char optr_in, char optr_out) {
        if (optr_in < optr_out) {
            return Priority.LOW;
        } else if (optr_in > optr_out) {
            return Priority.HIGH;
        }
        return Priority.EQUAL;
    }

    private boolean isoptr(String curr){
        if(legaloptr.contains(curr)){
            return true;
        }
        return false;
    }

    //输入操作符optr及栈位置，输出优先级表中对应优先级
    private int priorityTable(char optr, StackPosition stackPosition) {
        if (stackPosition == StackPosition.STACKIN) {
            switch (optr) {
                case '=':
                    return 0;
                case '+':
                    return 3;
                case '-':
                    return 3;
                case '*':
                    return 5;
                case '/':
                    return 5;
                case '%':
                    return 5;
                case '(':
                    return 1;
                case ')':
                    return 8;
                case '^':
                    return 7;
                case '&':
                    return 7;
                default:
                    return -1;
            }
        } else if (stackPosition == StackPosition.STACKOUT) {
            switch (optr) {
                case '=':
                    return 0;
                case '+':
                    return 2;
                case '-':
                    return 2;
                case '*':
                    return 4;
                case '/':
                    return 4;
                case '%':
                    return 4;
                case '(':
                    return 8;
                case ')':
                    return 1;
                case '^':
                    return 6;
                case '&':
                    return 6;
                default:
                    return -1;
            }
        }
        return -1;
    }

    //表达式运算主要逻辑
    private double executeCal () {
        double answer = 0;
        for (;exp_positon < expression.length();) {
            curr = getCurr(exp_positon);
            if(curr == ""){
                break;
            }
            if(isoptr(curr)){
                //empty stack push directly
                if(optr.isEmpty()){
                    optr.push(curr.charAt(0));
                    continue;
                }
                else{
                    boolean ispush = false;
                    while(!(ispush || optr.isEmpty())) {
                        //no-empty stack compare priority
                        char stackout = curr.charAt(0);
                        char stackin = optr.pop();
                        optr.push(stackin);
                        if (priorityTable(stackin, StackPosition.STACKIN)
                                < priorityTable(stackout, StackPosition.STACKOUT)) {
                            optr.push(stackout);
                            ispush = true;
                        }
                        else if(priorityTable(stackin,StackPosition.STACKIN)
                                == priorityTable(stackout,StackPosition.STACKOUT)){
                            optr.pop();
                            break;
                        }
                        else if (priorityTable(stackin, StackPosition.STACKIN)
                                >= priorityTable(stackout, StackPosition.STACKOUT)) {
                            double opnd1 = opnd.pop();
                            double opnd2 = opnd.pop();
                            char optr_cul = optr.pop();
                            if(opnd1 == 0){
                                calError = true;
                                return 0;
                            }
                            CalModel calModel = new CalModel(opnd2,opnd1);
                            answer = calModel.CalSelect(optr_cul);
                            //answer = opnd1 + opnd2;
                            opnd.push(answer);
                            if(optr.isEmpty()){
                                optr.push(stackout);
                                ispush = true;
                            }
                        }
                    }
                }
                //System.out.println("Optr");
            }
            else {
                opnd.push(Double.parseDouble(curr));
                //System.out.println("Opnd");
            }
            //System.out.println(curr);
        }
        return answer;
    }
}

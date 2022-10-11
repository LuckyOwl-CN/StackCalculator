public class CalModel {
    private int cal_answer = 0;
    private int opnd1;
    private int opnd2;

    public void getOpnd(int opnd1,int opnd2){
        this.opnd1 = opnd1;
        this.opnd2 = opnd2;
    }

    //使用switch匹配操作符
    public void CalSelect(char optr){

    }

    //CalSelect需要调用的函数
    private int operation_Add(){
        return cal_answer;
    }

    private int operation_Sub(){
        return cal_answer;
    }

    private int operation_Mul(){
        return cal_answer;
    }

    private int operation_Div(){
        return cal_answer;
    }

    private int operation_Mod(){
        return cal_answer;
    }

    private int operation_Sqar(){
        return cal_answer;
    }

    private int operation_Equal(){
        return cal_answer;
    }

    private int operation_Exp(){
        return cal_answer;
    }
}

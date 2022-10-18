import java.math.BigDecimal;

public class CalModel {
    private BigDecimal cal_answer = new BigDecimal(0);
    private BigDecimal opnd1;
    private BigDecimal opnd2;

    CalModel (double opnd1, double opnd2) {
        this.opnd1 = new BigDecimal(opnd1);;
        this.opnd2 = new BigDecimal(opnd2);;
    }



    /**
     * 操作符判断并给出结果
     * @param optr
     * @return
     */
    public BigDecimal CalSelect(String optr){
        switch(optr) {
            case "+":
                this.cal_answer = this.opnd1.add(this.opnd2);
                break;
            case "-":
                this.cal_answer = this.opnd1.subtract(this.opnd2);
                break;
            case "*":
                this.cal_answer = this.opnd1.multiply(this.opnd2);
                break;
            case "/":
                this.cal_answer = this.opnd1.divide(this.opnd2);
                break;
            case "%":
                this.cal_answer = operation_Mod(this.opnd1, this.opnd2);
                break;
            case "sqrt":
                this.cal_answer = operation_Sqrt(this.opnd1);
                break;
            case "=":
                this.cal_answer = operation_Equal(this.opnd1, this.opnd2);
                break;
            case "^":
                this.cal_answer = operation_Exp(this.opnd1, this.opnd2);
                break;
        }
        return this.cal_answer;
    }

    // 需要自己构造的运算操作
    private BigDecimal operation_Mod(BigDecimal opnd1, BigDecimal opnd2){
        return opnd1 % opnd2;
    }

    private BigDecimal operation_Sqrt(BigDecimal opnd1){
        try {
            return Math.sqrt(opnd1);
        } catch (ArithmeticException e) {
            System.out.println("非法输入：请输入大于0的运算数");
            return -1;
        }
    }

    private BigDecimal operation_Equal(BigDecimal opnd1, BigDecimal opnd2){
        return this.cal_answer;
    }

    private BigDecimal operation_Exp(BigDecimal opnd1, BigDecimal opnd2){
        return Math.pow(opnd1, opnd2);
    }
}

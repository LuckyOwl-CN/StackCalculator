public class CalModel {
    private double cal_answer = 0;
    private double opnd1;
    private double opnd2;

    CalModel (double opnd1, double opnd2) {
        this.opnd1 = opnd1;
        this.opnd2 = opnd2;
    }



    /**
     * 操作符判断并给出结果
     * @param optr
     * @return
     */
    public double CalSelect(String optr){
        switch(optr) {
            case "+":
                this.cal_answer = this.opnd1 + this.opnd2;
                break;
            case "-":
                this.cal_answer = this.opnd1 - this.opnd2;
                break;
            case "*":
                this.cal_answer = this.opnd1 * this.opnd2;
                break;
            case "/":
                this.cal_answer = this.opnd1 / this.opnd2;
                break;
            case "%":
                this.cal_answer = this.opnd1 % this.opnd2;
                break;
            case "sqrt":
                this.cal_answer = Math.sqrt(this.opnd1);
                break;
            case "=":
                this.cal_answer = this.opnd1 == this.opnd2 ? 1 : 0;
                break;
            case "^":
                this.cal_answer = Math.pow(this.opnd1, this.opnd2);
                break;
        }
        // 改进精度问题，保留四位小数
        return Math.round((this.cal_answer)*10000)/10000.0;
    }

}

import java.util.Scanner;

public class UIModel {
    private static String expression;
    private static String judge;
    private static Scanner scanner = new Scanner(System.in);


    public void expressionLoad() {
        System.out.print("Please input expression:");
        expression = scanner.nextLine();
    }

    public static String getExpression(){
        return expression;
    }

    public static void main(String[] args) {
        boolean calculate = true;
        System.out.println("Welcome to use Calculator");
        UIModel uiModel = new UIModel();
        StackModel stackModel = new StackModel();
        //UI端初始化
        while (calculate) {
            uiModel.expressionLoad();
            stackModel._init();
            System.out.print("Do you want to calculate again?(Y/N):");
            judge = scanner.nextLine();
            while(!judge.equals("Y") && !judge.equals("N")){
                System.out.print("Do you want to calculate again?(Y/N):");
                judge = scanner.nextLine();
            }
            if(judge.equals("Y") ){
                calculate = true;
            }
            else{
                calculate = false;
            }
        }
    }
}

// 对CalModel进行简单测试
public class Test {
    /**
     * 判断结果是否正确
     * @param value1
     * @param value2
     */
    private static boolean isDoubleEquals (double value1, double value2) {
        System.out.println("正确结果=" + value1 + ", 实际计算结果=" + value2);
        return Math.abs(value1 - value2) <= 0.0001;
    }

    public static void main(String[] args) {
        // 几个测试数据
        CalModel test01 = new CalModel(-1, 2);
        CalModel test02 = new CalModel(121.35, 0.35);
        CalModel test03 = new CalModel(9, 102.67);
        CalModel test04 = new CalModel(1, 2);
        CalModel test05 = new CalModel(9, 8);
        CalModel test06 = new CalModel(16, 1);
        CalModel test07 = new CalModel(0.67, 2);
        System.out.println(isDoubleEquals(1, test01.CalSelect("+")));
        System.out.println(isDoubleEquals(121, test02.CalSelect("-")));
        System.out.println(isDoubleEquals(924.03, test03.CalSelect("*")));
        System.out.println(isDoubleEquals(0.5, test04.CalSelect("/")));
        System.out.println(isDoubleEquals(1, test05.CalSelect("%")));
        System.out.println(isDoubleEquals(4, test06.CalSelect("sqrt")));
        System.out.println(isDoubleEquals(0.4489, test07.CalSelect("^")));

        // 错误例：
        CalModel test08 = new CalModel(1, 0);
        System.out.println(test08.CalSelect("/"));
    }
}

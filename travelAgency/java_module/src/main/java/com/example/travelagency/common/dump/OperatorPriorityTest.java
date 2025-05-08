package com.example.travelagency.common.dump;

public class OperatorPriorityTest {
    public static void main(String[] args) {
        int a = 5;
        int b = 10;
        int c = 2;

        System.out.println("문제 : (a + b) * c << 1 > 30 && true ? 100 : 200;");
        System.out.println("");

        System.out.println("1. 괄호: (a + b)");
        int step1 = a + b; // 5 + 10
        System.out.println("   → " + step1); // 15

        System.out.println("2. 산술: step1 * c");
        int step2 = step1 * c; // 15 * 2
        System.out.println("   → " + step2); // 30

        System.out.println("3. 시프트: step2 << 1");
        int step3 = step2 << 1; // 30 << 1 = 60
        System.out.println("   → " + step3);

        System.out.println("4. 비교: step3 > 30");
        boolean step4 = step3 > 30; // 60 > 30
        System.out.println("   → " + step4);

        System.out.println("5. 논리: step4 && true");
        boolean step5 = step4 && true;
        System.out.println("   → " + step5);

        System.out.println("6. 삼항: step5 ? 100 : 200");
        int result = step5 ? 100 : 200;
        System.out.println("   → " + result);

        System.out.println("7. 대입: result = " + result);
    }
}

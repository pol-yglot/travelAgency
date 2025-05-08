package com.example.travelagency.common.dump;

public class BitOperatorTest {
    public static void main(String[] args) {
        final int LOOP = 100_000_000;
        int result = 0;

        // 곱셈 테스트
        long startMul = System.nanoTime();
        for (int i = 0; i < LOOP; i++) {
            result = i * 2;
        }
        long endMul = System.nanoTime();
        System.out.println("곱셈(* 2) 시간: " + (endMul - startMul) + "ns");

        // 시프트 테스트
        long startShift = System.nanoTime();
        for (int i = 0; i < LOOP; i++) {
            result = i << 1;
        }
        long endShift = System.nanoTime();
        System.out.println("시프트(<< 1) 시간: " + (endShift - startShift) + "ns");
    }
}

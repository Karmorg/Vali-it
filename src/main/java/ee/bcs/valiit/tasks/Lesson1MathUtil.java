package ee.bcs.valiit.tasks;

import java.util.Scanner;

public class Lesson1MathUtil {
    private String test;

    public Lesson1MathUtil(String test) {
        this.test = test;
    }

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        Lesson1MathUtil lesson1MathUtil1 = new Lesson1MathUtil("test");
        Lesson1MathUtil lesson1MathUtil2 = new Lesson1MathUtil("test2");
        lesson1MathUtil1.test();
        lesson1MathUtil2.test();

        int[][] twoArray = new int[3][4];
        System.out.println(max(2, 3, 4));
        System.out.println(max(-3, 1, 2));
        System.out.println(max(5, 4, 2));

        Scanner sc = new Scanner(System.in);
        System.out.println("Sisesta 2 täisarvu, et tagastatud saaks neist väiksem.");
        int intInput1 = sc.nextInt();
        int intInput2 = sc.nextInt();
        System.out.println(min(intInput1, intInput2));

        System.out.println("Sisesta meetodi nimi, mida soovid kasutada. Valida on: \n min(a,b); max(a,b); abs(a); isEven(a); min(a,b,c); max(a,b,c)");
        String method = sc.next();
        if (method.equals("min") || method.equals("max")) {
            System.out.println("Kas soovid sisestada kaks või kolm täisarvu?");
            intInput1 = sc.nextInt();
            //intInput2 = sc.nextInt();
            if (intInput1 == 3) {
                System.out.println("Sisesta kolm täisarvu eraldades need enteriga");
                intInput1 = sc.nextInt();
                intInput2 = sc.nextInt();
                int intInput3 = sc.nextInt();
                if (method.equals("min")) {
                    System.out.println(min(intInput1, intInput2, intInput3));
                } else {
                    System.out.println(max(intInput1, intInput2, intInput3));
                }
            } else if (intInput1 == 2) {
                System.out.println("Sisesta kaks täisarvu eraldades need enteriga");
                intInput1 = sc.nextInt();
                intInput2 = sc.nextInt();
                if (method.equals("min")) {
                    System.out.println(min(intInput1, intInput2));
                } else {
                    System.out.println(max(intInput1, intInput2));
                }
            }

        } else if (method.equals("abs")) {
            System.out.println("Sisesta täisarv ja vajuta enterit");
            intInput1 = sc.nextInt();
            System.out.println(abs(intInput1));
        } else if (method.equals("isEven")) {
            System.out.println("Sisesta täisarv ja vajuta enterit");
            intInput1 = sc.nextInt();
            System.out.println(isEven(intInput1));
        }
        System.out.println("Kasutati meetodit: " + method);
    }

    public void test() {
        System.out.println(test);
    }

    public static int min(int a, int b) {
        // TODO tagasta a ja b väikseim väärtus
        if (a < b) {
            return a;
        }
        return b;
    }

    public static int max(int a, int b) {
        // TODO tagasta a ja b suurim väärtus
        if (a > b) {
            return a;
        }
        return b;
    }

    public static int abs(int a) {
        // TODO tagasta a absoluut arv

        return Math.abs(a);
    }

    public static boolean isEven(int a) {
        // TODO tagasta true, kui a on paaris arv
        // tagasta false kui a on paaritu arv
        if (a % 2 == 0) {
            return true;
        }
        return false;
    }

    public static int min(int a, int b, int c) {
        // TODO tagasta a, b ja c väikseim väärtus
        if (a < b) {
            if (a < c) {
                return a;
            }
            return c;
        } else if (c < b) {
            return c;
        }
        return b;
    }

    public static int max(int a, int b, int c) {
        // TODO tagasta a, b ja c suurim väärtus
        if (a > b) {
            if (a > c) {
                return a;
            }
            return c;
        } else if (c > b) {
            return c;
        }
        return b;
        //return max(max(a,b),c);
    }

}

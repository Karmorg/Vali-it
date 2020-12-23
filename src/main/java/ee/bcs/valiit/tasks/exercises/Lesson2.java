package ee.bcs.valiit.tasks.exercises;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Lesson2 {

    public static void main(String[] args) {
        //exercise7();
        //exercise1();
        exercise2(3);
        //exercise3(10,10);
        //fibonacci(5);
        //exercise5();
        //exercise6();
        //exercise7();
        //exercise8();
//        exercise9();
        //test(8);
    }


    public static void exercise1() {
        // TODO loo 10 elemendile täisarvude massiv
        // TODO loe sisse konsoolist 10 täisarvu
        // TODO trüki arvud välja vastupidises järjekorras
        int intLeft = 0;
        int intarr[] = new int[10];
        for (int i = 0; i < intarr.length; i++) {
            intLeft = 10 - i;
            System.out.println("Sisesta veel " + intLeft + " täisarvu.");
            Scanner sc = new Scanner(System.in);
            intarr[i] = sc.nextInt();
        }
        for (int i = intarr.length - 1; i >= 0; i--) {
            System.out.println(intarr[i]);
        }
    }

    public static int[] exercise2(int x) {
        // TODO prindi välja x esimest paaris arvu
        // Näide:
        // Sisend 5
        // Väljund 2 4 6 8 10
        int[] ans = new int[x];
        for (int i = 1; i <= x; i++) {
            System.out.println(i * 2);
            ans[i-1]=i*2;
        }
        return ans;

    }

    public static void exercise3(int x, int y) {
        // TODO trüki välja korrutustabel mis on x ühikut lai ja y ühikut kõrge
        // TODO näiteks x = 3 y = 3
        // TODO väljund
        // 1 2 3
        // 2 4 6
        // 3 6 9
        int mTable[][] = new int[x][y];
        System.out.println("test");
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                mTable[i][j] = (i + 1) * (j + 1);
                if (mTable[i][j] < 10) {
                    System.out.print(mTable[i][j] + "  ");
                } else {
                    System.out.print(mTable[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static int fibonacci(int n) {
        // TODO
        // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
        // 0, 1, 1, 2, 3, 5, 8, 13, 21
        // Tagasta fibonacci jada n element
        int fib1 = 0;
        int fib2 = 1;
        int fib3 = 0;
        for (int i = 0; i < n; i++) {
            fib3 = fib1 + fib2;
            fib1 = fib2;
            fib2 = fib3;
        }
        System.out.println(fib3);
        return fib3;

    }

    public static void exercise5() {
        // https://onlinejudge.org/index.php?option=onlinejudge&Itemid=8&page=show_problem&problem=36
        int i = 900;
        int j = 1000;
        int count = 1;
        int maxCount = 0;
        int n = 0;

        for (int k = i; k <= j; k++) {
            n = k;
            while (n != 1) {
                if (n % 2 != 0) {
                    n = 3 * n + 1;
                    count++;
                } else {
                    n = n / 2;
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
            }
            System.out.println(count);
            count = 1;
        }
        System.out.println(i + " " + j + " " + maxCount);
    }

    public static void exercise6() {
        /*
            Kirjutada Java programm, mis loeb failist visits.txt sisse looduspargi külastajad erinevatel jaanuari päevadel ning
            a) sorteerib külastuspäevad külastajate arvu järgi kasvavalt ning prindib tulemuse konsoolile;
            b) prindib konsoolile päeva, mil külastajaid oli kõige rohkem.
            Faili asukoht tuleb programmile ette anda käsurea parameetrina.
         */
        //Hea variant
        String a = "2018-01-13, 436";
        String[] b = a.split(", ");
        Visit visit = new Visit();
        visit.setDate(b[0]);
        visit.setVisits(Integer.parseInt(b[1]));
        List<Visit> visits = new ArrayList<>();
        visits.add(visit);
        visits.sort(new Comparator<Visit>() {
            @Override
            public int compare(Visit o1, Visit o2) {
                return o1.getVisits() - o2.getVisits();
            }
        });

        //Esialgne variant
        String[][] kylastajad = new String[100][2];
        String[][] kylastajadSort = new String[100][2];
        String[] row = new String[2];

        File file = new File("C:\\Users\\karmo\\IdeaProjects\\vali-it3\\src\\main\\ressources\\test_data\\visits.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int i = 0;
        while (sc.hasNextLine()) {
            row = sc.nextLine().split(", ");
            //System.out.println(row[0]);
            //System.out.println(row[1]);
            kylastajad[i][0] = row[0];
            kylastajad[i][1] = row[1];
            i++;
        }

        int indexMax = 0;
        for (int j = 0; j < i; j++) {
            indexMax = j;
            for (int k = 0; k < i; k++) {
                if (Integer.parseInt(kylastajad[k][1]) > Integer.parseInt(kylastajad[indexMax][1])) {
                    indexMax = k;
                }
            }
            //System.out.println(indexMax);
            kylastajadSort[i - j - 1][0] = kylastajad[indexMax][0];
            kylastajadSort[i - j - 1][1] = kylastajad[indexMax][1];
            kylastajad[indexMax][1] = "0";

        }
        for (int l = 0; l < i; l++) {
            System.out.print(kylastajadSort[l][0] + " - ");
            System.out.println(kylastajadSort[l][1]);
        }

    }

    public static void exercise7() {
        // TODO arvuta kasutades BigDecimali 1.89 * ((394486820340 / 15 ) - 4 )
        BigDecimal a = new BigDecimal("1.89");
        BigDecimal b = new BigDecimal("394486820345");
        BigDecimal c = new BigDecimal("15");
        BigDecimal d = new BigDecimal("4");

        //System.out.println(b.divide(c, 4, RoundingMode.HALF_UP));
        //System.out.println(b*((b.divide(c)-d)));
        System.out.println(b.divide(c, RoundingMode.HALF_UP).subtract(d).multiply(a));
    }

    public static void exercise8() {
        /*
        Failis nums.txt on üksteise all 150 60-kohalist numbrit.

        Kirjuta programm, mis loeks antud numbrid failist sisse ja liidaks need arvud kokku ning kuvaks ekraanil summa.
        Faili nimi tuleb programmile ette anda käsurea parameetrina.

        VASTUS:
        Õige summa: 77378062799264987173249634924670947389130820063105651135266574
         */
        System.out.println("Sisesta numbritega faili nimi.");
        Scanner fileName = new Scanner(System.in);
        String fileString = fileName.next();
        //System.out.println(fileString);

        File file = new File("C:\\Users\\karmo\\IdeaProjects\\vali-it3\\src\\main\\ressources\\test_data\\" + fileString);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //BigDecimal [] numbers = new BigDecimal[150];
        BigDecimal numSum = new BigDecimal("0");
        int i = 0;
        while (sc.hasNextLine()) {
            //new BigDecimal
            //numbers [i] = new BigDecimal(sc.nextLine());
            numSum = numSum.add(new BigDecimal(sc.nextLine()));
        }
        System.out.println(numSum);
    }

    public static void exercise9() {
        /* TODO
        Sama mis eelmises ülesandes aga ära kasuta BigInt ega BigDecimal klassi
        Õige summa: 77378062799264987173249634924670947389130820063105651135266574
         */
        //System.out.println("Sisesta numbritega faili nimi.");
        //Scanner fileName = new Scanner(System.in);
        //String fileString = fileName.next();

        //File file = new File("C:\\Users\\karmo\\IdeaProjects\\vali-it3\\src\\main\\ressources\\test_data\\" + fileString);
        File file = new File("C:\\Users\\karmo\\IdeaProjects\\vali-it3\\src\\main\\ressources\\test_data\\nums.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] numbers = new String[151];
        int[] numSum = new int[70];

        int i = 0;
        while (sc.hasNextLine()) {
            numbers[i] = sc.nextLine();
            i++;
        }
        int toNext = 0;
        for (int j = 59; j >= 0; j--) {

            for (int k = 0; k < i; k++) {
                numSum[j + 10] += Integer.parseInt(String.valueOf(numbers[k].charAt(j)));
            }

            numSum[j + 10] += toNext;
            toNext = numSum[j + 10] / 10;
            numSum[j + 10] = numSum[j + 10] % 10;
            //System.out.println("summa " + numSum[j + 10]);
            //System.out.println("jääk" + toNext);

        }
        numSum[9] += toNext;
        //System.out.println(numbers[i]);
        //System.out.println(numSum[69]);

        for (int k = 9; k < numSum.length; k++) {
            System.out.print(numSum[k]);
        }
        System.out.println();


    }

    private static void test(int a) {
        int res = -2;
        System.out.print(res + " ");
        for (int i = 1; i < a; i++) {
            if (i % 2 == 0) {
                res = (Math.abs(res) + 2) * -1;
            } else {
                res = Math.abs(res) + 2;
            }
            System.out.print(res + " ");
        }
        System.out.println();
    }

}

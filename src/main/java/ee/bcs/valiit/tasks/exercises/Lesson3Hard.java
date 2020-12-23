package ee.bcs.valiit.tasks.exercises;

import java.util.Random;
import java.util.Scanner;

// Enne kui seda tegema hakkad tee ära Lesson 2 (välja arvatud ülesanded 6, 8, 9)
public class Lesson3Hard {
    public static void main(String[] args) {

        //System.out.println(evenFibonacci(5));
        //randomGame();
        System.out.println(morseCode("Siisoo"));
    }

    public static int evenFibonacci(int x) {
        // TODO liida kokku kõik paaris fibonacci arvud kuni numbrini x
        // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
        // 0, 1, 1, 2, 3, 5, 8, 13, 21
        int fib1 = 0;
        int fib2 = 1;
        int fibN = 0;
        int fibSum = 0;
        for (int i = 1; i <= x; i++) {
            fibN = fib1 + fib2;
            if (fibN % 2 == 0) {
                fibSum += fibN;
            }
            fib1 = fib2;
            fib2 = fibN;
        }
        return fibSum;
    }

    public static void randomGame() {
        // TODO kirjuta mäng mis võtab suvalise numbri 0-100, mille kasutaja peab ära arvama
        // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
        // ja kasutaja peab saama uuesti arvata
        // numbri ära aramise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks
        Random random = new Random();
        int i = random.nextInt(100);
        System.out.println(i);

        int noOfGuess = 0;
        int guessNo = 0;

        System.out.println("Arvuti genereeris arvu nullist sajani. Arvu selgitamiseks sisesta üks arv.");

        Scanner sc = new Scanner(System.in);
        while (i != guessNo) {
            guessNo = sc.nextInt();
            noOfGuess++;
            if (guessNo < i) {
                System.out.println("Sisestatud arv on väiksem arvuti genereeritust.");
            } else {
                System.out.println("Sisestatud arv on suurem arvuti genereeritust.");
            }
        }
        System.out.println("Arvasid õigesti peale " + noOfGuess + " katset.");

    }

    public static String morseCode(String text) {
        // TODO kirjuta programm, mis tagastab sisestatud teksti morse koodis (https://en.wikipedia.org/wiki/Morse_code)
        // Kasuta sümboleid . ja -

        // Setting up the code registry
        String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----", ".--.-", "-..-.-"};
        int a = 0;
        String[][] registry = new String[40][2];
        for (char c = 'a'; c <= 'z'; c++) {
            System.out.println(c);
            a = Character.getNumericValue(c)-10;
            System.out.println(a);
            registry[a][0] = String.valueOf(c);
            registry[a][1] = morse[a];
        }

        //Translating string
        String outputMorse = "";
        for (int i = 0; i<text.length();i++){
            for (int j = 0; j<=26;j++){
                if (String.valueOf(text.charAt(i)).equalsIgnoreCase(registry [j][0])){
                    System.out.println("Great success");
                    outputMorse += " ";
                    outputMorse += registry[j][1];
                }
            }
        }

        return outputMorse;
    }
    }

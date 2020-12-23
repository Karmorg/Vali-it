package ee.bcs.valiit.tasks.exercises;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BouncyNumbers {


    public static void main(String[] args) {

        BigDecimal proportion = BigDecimal.ZERO;
        int currentNo = 99;
        String noString;
        int increasing;
        int decreasing;
        int notBouncy = 0;

        while (proportion.compareTo(new BigDecimal("0.99")) < 0) {
            currentNo++;
            noString = String.valueOf(currentNo);
            increasing = 0;
            decreasing = 0;
            for (int i = 0; i < noString.length() - 1; i++) {
                if (Integer.parseInt(noString.substring(i, i + 1)) < Integer.parseInt(noString.substring(i + 1, i + 2))) {
                    increasing++;

                } else if (Integer.parseInt(noString.substring(i, i + 1)) > Integer.parseInt(noString.substring(i + 1, i + 2))) {
                    decreasing++;
                } else if (Integer.parseInt(noString.substring(i, i + 1)) == Integer.parseInt(noString.substring(i + 1, i + 2))) {
                    decreasing++;
                    increasing++;
                }
            }
            if (increasing + 1 == noString.length() || decreasing + 1 == noString.length()) {
                notBouncy++;
            }

            BigDecimal currentNumberDec = new BigDecimal(currentNo);
            BigDecimal notBouncyDec = new BigDecimal(notBouncy);
            BigDecimal magicNumber = new BigDecimal("99");
            BigDecimal a = currentNumberDec.subtract(notBouncyDec).subtract(magicNumber);
            proportion = a.divide(currentNumberDec, 10, RoundingMode.HALF_UP);
        }

        System.out.println(proportion + " arv " + currentNo);
    }


}

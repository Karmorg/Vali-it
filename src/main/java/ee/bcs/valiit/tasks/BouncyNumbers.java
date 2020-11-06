package ee.bcs.valiit.tasks;

public class BouncyNumbers {


    public static void main(String[] args) {

        float proportion = 0;
        int currentNo = 100;
        String noString;
        int lastNo;
        int curNo;
        boolean isBouncy;
        int increasing;
        int decreasing;

        while (proportion < 0.99) {
            noString = String.valueOf(currentNo);
            isBouncy=true;
            increasing = 0;
            decreasing = 0;
            for (int i = 0; i < noString.length()-1; i++) {
                if (Integer.parseInt(noString.substring(i, i+1))<Integer.parseInt(noString.substring(i+1, i+2))){
                    increasing ++;

                } else if (Integer.parseInt(noString.substring(i, i+1))>Integer.parseInt(noString.substring(i+1, i+2))){
                    decreasing ++;
                }
            }
            if (increasing == noString.length() || decreasing == noString.length()){

            }
            proportion += 0.21;
            currentNo++;
            System.out.println(proportion);
        }
    }


}

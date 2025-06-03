package func;

import java.util.Random;

public final class MakeDiffArr {
    public static final int[] makeDiffArr(){
        int[] ret = new int[16];
        for (int i = 0; i < 16; i++) {
            ret[i] = i;
        }

        Random r = new Random();
        for (int i = 0; i < 16; i++) {
            int index = r.nextInt(16);

            int temp = ret[index];
            ret[index] = ret[i];
            ret[i] = temp;
        }

        return ret;
    }
}

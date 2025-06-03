package func;

import java.util.Random;

public final class CodeUtil {
    public static String getCode(int length, int digitCnt){
        if(length <= 0 || digitCnt < 0 || digitCnt > length) {
            throw new IllegalArgumentException("Invalid input parameters.");
        }
        Random r = new Random();

        char[] ret = new char[length];
        boolean[] used = new boolean[length];

        for (int i = 0; i < length; i++) {
            if(r.nextBoolean()) {
                ret[i] = (char)('a' + r.nextInt(26));
            } else{
                ret[i] = (char)('A' + r.nextInt(26));
            }
        }
        for (int i = 0; i < digitCnt;) {
            int index;
            if(!used[(index = r.nextInt(length))]){
                ret[index] = (char)(r.nextInt(10) + '0');
                used[index] = true;
                i++;
            }
        }

        return new String(ret);
    }
}

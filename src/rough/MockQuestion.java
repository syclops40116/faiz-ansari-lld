package rough;

public class MockQuestion {
    private static int getMinSwaps(int[] numbers) {
        int n = numbers.length;
        int left = 0;

        int windowLength = 0;

        for(int num: numbers) {
            if(num == 1) {
                windowLength++;
            }
        }

        if(windowLength == 0) return -1;

        int res = Integer.MAX_VALUE;
        int oneCount = 0, zeroCount = 0;

        for(int right = 0; right < n; right++) {

            if(numbers[right] == 1) {
                oneCount++;
            } else {
                zeroCount++;
            }

            if(right - left + 1 == windowLength) {
                res = Math.min(res, Math.min(zeroCount, oneCount));

                if(numbers[left] == 1) {
                    oneCount--;
                } else {
                    zeroCount--;
                }
                left++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1, 0, 0, 0, 0};

        System.out.println(getMinSwaps(numbers));
    }
}

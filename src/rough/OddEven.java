package rough;

public class OddEven {
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    private void solve(int[] nums) {
        int oddPtr = 0;
        int evenPtr = 0;

        while(evenPtr < nums.length) {
            if(nums[evenPtr] % 2 == 1) {
                swap(nums, oddPtr, evenPtr);
                oddPtr++;
            } else {
                evenPtr++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 7, 8, 9, 10};

        OddEven obj = new OddEven();

        obj.solve(nums);

        for (int num: nums) {
            System.out.print(num + " ");
        }
    }
}

package unit1to2.code;

/**
 * 计算最大子序列和的四种方法，时间复杂度分别为N的立方，N的平方，NlogN，N
 */

public class MaxSubsequenceSum {
    public static int maxSubSum1(int[] a) {//T(n)=O(N^3)
        int maxSum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j <= i; j++) {
                int thisSum = 0;
                for (int k = j; k <= i; k++)
                    thisSum += a[k];

                if (thisSum > maxSum)
                    maxSum = thisSum;
            }
        }
        return maxSum;
    }

    public static int maxSubSum2(int[] a) {//T(N)=O(N^2)
        int maxSum = 0;
        for (int i = 0; i < a.length; i++) {
            int thisSum = 0;
            for (int j = i; j < a.length; j++) {
                thisSum += a[j];
                if (thisSum > maxSum)
                    maxSum = thisSum;
            }
        }
        return maxSum;
    }

    /**
     * 分治法求最大子序列和，最大子序列和的来源有三种可能：
     * 1：来自于数组的中间元素mid的左边
     * 2：来自于数组的中间元素mid的左边加上右边
     * 3：来自于数组的中间元素mid的右边
     *
     * @param a  想要计算最大子序列和的数组
     * @param lo 数组下界
     * @param hi 数组上界
     * @return 最大子序列和
     */
    public static int maxSubSum3(int[] a, int lo, int hi) {//分治算法(divide and conquer)----T(N)=O(NlogN)
        if (lo == hi) //基准情形
            if (a[lo] > 0) return a[lo];
            else return 0;//负数的最大子序列和假设为0

        int mid = (lo + hi) / 2;

        //计算从中间开始最大子序列和
        int rightSum = 0, leftSum = 0, thisSum, rightSumTemp = 0, leftSumTemp = 0;
        for (int i = mid; i >= lo; i--) {
            rightSumTemp += a[i];

            if (rightSumTemp > rightSum)
                rightSum = rightSumTemp;
        }

        for (int i = mid + 1; i <= hi; i++) {
            leftSumTemp += a[i];

            if (leftSumTemp > leftSum)
                leftSum = leftSumTemp;
        }
        thisSum = rightSum + leftSum;

        //递归求两边的子序列的和
        rightSum = maxSubSum3(a, lo, mid);
        leftSum = maxSubSum3(a, mid + 1, hi);

        //返回最大子序列的和（中间和两边）
        int temp = (rightSum > leftSum ? rightSum : leftSum);
        return temp > thisSum ? temp : thisSum;
    }

    public static int maxSubSum4(int[] a) {
        int maxSum = 0, thisSum = 0;
        for (int i = 0; i < a.length; i++) {
            thisSum += a[i];

            if (thisSum > maxSum)
                maxSum = thisSum;
            else if (thisSum < 0)
                    thisSum = 0;
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 11, -40, 06, -50, 200};

        System.out.println(maxSubSum1(a));//对结果进行验证，如果三个方法输出都一样，就是正确的。
        System.out.println(maxSubSum2(a));
        System.out.println(maxSubSum3(a, 0, a.length - 1));
        System.out.println(maxSubSum4(a));
    }
}

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] nums = {9, 8, 9};
        int[][] mat = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        //System.out.println(generate(5));
        //System.out.println(addBinary("1010", "1011"));
        System.out.println(strStr("leetcode", "leeto"));
    }

    public static int strStr(String haystack, String needle) {
        int result  = -1;
        if(haystack.contains(needle)){

            return haystack.indexOf(needle);
        }
        return result;
    }

    public static int[] findDiagonalOrder(int[][] mat) {
        int i = mat[0].length;
        int j = mat.length;
        int[] result = new int[i * j];
        int index = 0;
        ArrayList<Integer> intermediate = new ArrayList<>();
        int row = 0;
        int column = 0;
        // print out all the heads (0st row, last column
        for (int k = 0; k < i + j; k++) {
            // print first row
            if (k < j) {
                row = 0;
                column = k;
            } else {
                row = k - j + 1;
                column = j - 1;
            }
            intermediate.clear();
            while (row < j && column > -1) {
                intermediate.add(mat[row][column]);
                row++;
                column--;
            }

            if (k % 2 == 0) {
                Collections.reverse(intermediate);
            }
            for (int num : intermediate) {
                result[index++] = num;
            }
        }
        return result;
    }

    public static String addBinary(String a, String b) {
        StringBuilder str = new StringBuilder();
        int carry = 0;
        // if char exists add it if not add 0
        int n = Math.max(a.length(),b.length());
        int aLast =  a.length()-1;
        int bLast = b.length()-1;
        for (int i = n-1; i > -1; i--) {
            int aBinary = aLast<0 ? 0: Character.getNumericValue(a.charAt(aLast--));
            int bBinary = bLast<0 ? 0 : Character.getNumericValue(b.charAt(bLast--));

            int result = aBinary + bBinary + carry;
            carry = result > 1? 1 : 0;
            result = result % 2;
            str.append(result);
        }
        if(carry>0) str.append(carry);
        str.reverse();
        return str.toString();
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        //traverse every row and column
        ArrayList<Integer> spiral = new ArrayList<>();
        //traverse row -> matrix[row++][column]
        //traverse column -> matrix[row][column++]
        int R = matrix[0].length;
        int C = matrix.length;
        // if i < row.length then we are traversing a row and column++
        // if i > row.length we are traversing a column and row ++
        int right = R;
        int bottom = C;
        int top = 0;
        int left = 0;
        while (left < bottom) {
            if (spiral.size() == R * C) {
                break;
            }
            //right
            for (int i = left; i < right; i++) {
                spiral.add(matrix[top][i]);
            }
            top++;
            //down
            for (int i = top; i < bottom; i++) {
                spiral.add(matrix[i][right - 1]);
            }
            if (left >= right && top >= bottom) {
                break;
            }
            right--;
            //left
            for (int i = right - 1; i > left - 1; i--) {
                spiral.add(matrix[bottom - 1][i]);
            }
            bottom--;
            //up
            for (int i = bottom - 1; i > top - 1; i--) {
                spiral.add(matrix[i][left]);
            }
            left++;
            //System.out.println(rowMIN);
            // i<R -> 0,0 0,1 0,2
            // i>R -> 0,2 1,2 2,2
            //        2,2 2,1 2,0 if row
            //        2,0 1,0 0,0
            //either increasing from 0-2 or decreasing while the other variable is either 0 or 2
            // if row == 0 && column == 0 { column++ } -> going right
            // if row == 0 && column == 2 { row++ } -> going down
            // if row == 2 && column == 2 { column-- } -> going left
            // if row == 2 && column == 0 { row-- } -> going up
        }
        return spiral;
    }

    public static List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> triangle = new ArrayList<>();
        //populating tringle
        for (int i = 1; i < numRows + 1; i++) {
            ArrayList<Integer> intermediate = new ArrayList<>();
            //populating intermediate
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i - 1) {
                    intermediate.add(1);
                } else {
                    if(i>2) {
                        int x = triangle.get(i-2).get(j - 1);
                        int y = triangle.get(i-2).get(j);
                        intermediate.add(x + y);
                    }
                }
            }
            //System.out.println(intermediate);
            triangle.add(intermediate);
            //System.out.println(triangle);
        }
        return triangle;
    }


    public static boolean validMountainArray(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[max]) {
                max = i;
            }
        }
        if (arr.length < 3 || max == arr.length - 1) {
            return false;
        }
        for (int i = 0; i < max; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        for (int i = max; i < arr.length; i++) {
            if (arr[i] <= arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void moveZeroes(int[] nums) {
        int numsZeroPointer = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[numsZeroPointer] = nums[i];
                numsZeroPointer++;
            }
        }
        for (int i = numsZeroPointer; i < nums.length; i++) {
            nums[i] = 0;
        }
        printArray(nums);
    }

    //[3,1,2,4]
    public static int[] sortArrayByParity(int[] nums) {
        int beg = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                int temp = nums[beg];
                nums[beg] = nums[i];
                nums[i] = temp;
                beg++;
            }
            printArray(nums);
        }
        return nums;
    }

    public static int removeElement3(int[] nums, int val) {
        int beg = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[beg] = nums[i];
                beg++;
            }
        }

        return beg;
    }

    public int heightChecker(int[] heights) {
        int counter = 0;
        int[] expected = heights.clone();
        Arrays.sort(expected);
        for (int i = 0; i < heights.length; i++) {
            if (expected[i] != heights[i]) {
                counter++;
            }
        }
        return counter;
    }

    //[2,2,3,1]
    public static int thirdMax(int[] nums) {
        int count = 1;
        Arrays.sort(nums);
        int max = nums[nums.length - 1];
        //[1,2,2,3]
        for (int i = nums.length - 1; i > 0; i--) {
            if (count == 3) {
                return max;
            }
            if (nums[i - 1] != nums[i]) {
                max = nums[i - 1];
                count++;
            }
        }
        if (count < 3) {
            return nums[nums.length - 1];
        }
        return max;
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        //for each value in the array set the value at i-1 to a negative value
        //iterate through the array adding every index+1 that contains a positive value to the arraylist
        for (int num : nums) {
            int i = Math.abs(num) - 1;
            nums[i] = Math.abs(nums[i]) * -1;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }

    public static int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                nums[i] *= -1;
            }
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    public static int pivotIndex(int[] nums) {
        //calculate the sum of the array
        //iterate through the array trying each index as pivot index
        //leftsum = leftsum+index
        //rightsum = sum-leftsum-index
        int sum = 0;
        for (int num :
                nums) {
            sum += num;
        }
        int leftSum = 0;
        int rightSum = sum - leftSum;
        for (int i = 0; i < nums.length; i++) {
            System.out.println(leftSum + " " + rightSum);
            System.out.println(nums[i]);
            rightSum -= nums[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    public static int dominantIndex(int[] nums) {
        //iterate through the array using two pointers
        //compare left and right  if right>left left++, left>right right--
        //if previous left>right second max = prevLeft
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[max]) {
                max = i;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= nums[max] * 2) {
                return -1;
            }
        }
        return max;
    }

    public static int[] plusOne(int[] digits) {
        int n = digits.length - 1;
        if (digits[n] == 9) {
            for (int i = n; i > -1; i--) {
                if (digits[i] != 9) {
                    digits[i] += 1;
                    break;
                } else {
                    digits[i] = 0;
                }
            }
            if (digits[0] == 0) {
                int[] result = new int[digits.length + 1];
                result[0] = 1;
                for (int i = 1; i < digits.length + 1; i++) {
                    result[i] = digits[i - 1];
                }
                return result;
            }
        } else {
            digits[digits.length - 1] += 1;
        }
        return digits;
    }

    public static int[] removeDuplicates2(int[] nums) {
        int beg = 0;
        if (nums == null || nums.length == 0) {
            return nums;
        }
        // [0,0,1,1,1,2,2,3,3,4]
        // [0,1,2,3,4,5,6,7,8]
        //if number is the same replace beg with it
        // if number is not the same beg++
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                beg++;
            }
            if (nums[i] == nums[i - 1]) {
                nums[beg] = nums[i];
            }

        }
        return nums;
    }

    public int[] replaceElements(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }
        int max;
        for (int i = 0; i < arr.length; i++) {
            max = 0;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > max) {
                    max = arr[j];
                }
            }
            arr[i] = max;
        }
        arr[arr.length - 1] = -1;

        return arr;
    }

    //nums = [0,0,1,1,1,2,2,3,3,4]
    public static int removeDuplicates(int[] nums) {
        int insertIndex = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[insertIndex] = nums[i];
                insertIndex++;
            }
        }
        for (int n : nums) {
            System.out.print(n);
        }
        return insertIndex;
    }

    public static void rotate(int[] nums, int k) {
        //put the rotating nums in a separate array
        k++;
        int[] ints = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ints[i] = nums[k];
            k = (k + 1) % nums.length;
        }
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashmap = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            if (hashmap.get(nums[i]) == null) {
                hashmap.put(target - nums[i], nums[i]);
            } else {
                result = new int[]{nums[i], hashmap.get(nums[i])};
                return result;
            }
        }
        return result;
    }

    //returns index of the next non-duplicate
    public static int findNextNonDuplicate(int n, int[] nums) {
        for (int i = n; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                return i + 1;
            }
        }
        return -1;
    }

    public static boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }


    public static void findTriple(int[] nums) {
        List<int[]> ans = new ArrayList<>();
//        int[] arr2 = arr.clone();
//
//        int[] arr3 = arr.clone();
//        int i = 0;
//        int j = 1;
//        int k = 2;
//        while (i<arr.length-1){
//            while(j<arr.length-1){
//
//            }
//        }
//        return ans;
        Arrays.sort(nums);
        int len = nums.length;
        int l;
        int r;
        List<Integer> sum = new ArrayList<Integer>();
        for (int i = 0; i < len - 2; i++) {
            l = i + 1;
            r = len - 1;
            while (l < r) {
                if (i + l + r == 0) {
                    ans.add(new int[]{i, l, r});
                }
            }
        }
    }

    public int removeElement(int[] nums, int val) {
        //
        int count = 0;
        int end = nums.length - 1;
        while (nums[end] == val) {
            end--;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                //swap end value and i value
                int temp = nums[i];
                nums[i] = nums[end];
                nums[end] = temp;
                //
                end--;
            }
        }
        return nums.length - count;
    }

    public static int removeElement2(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        while (nums[right] == val) {
            right--;
        }
        while (left < right) {
            printArray(nums);

            if (nums[left] == val) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                right--;
                printArray(nums);
            }
            left++;
        }
        printArray(nums);
        return left;
    }

    public static boolean checkIfExist(int[] arr) {
        HashMap<Integer, Integer> hashmap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (hashmap.get(arr[i] * 2) != null && hashmap.get(arr[i] / 2) != null) {
                return true;
            } else {
                hashmap.put(arr[i], i);
            }
        }
        return false;
    }

    //determine if a string has all unique charcaters
    //1. use a hashmap or an array to lookup if a character like this has appeared before
    //
    public static boolean isUnique(String str) {
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] arr) {
        List<Integer> list = Arrays.stream(arr).boxed().toList();
        System.out.println(list);
    }

    public static List<Integer> combine(int[] arr1, int[] arr2) {
        List<Integer> ans = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                ans.add(arr1[i]);
                i++;
            } else {
                ans.add(arr2[j]);
                j++;
            }
        }
        while (i < arr1.length) {
            ans.add(arr1[i]);
            i++;
        }

        while (j < arr2.length) {
            ans.add(arr2[j]);
            j++;
        }

        return ans;
    }

    public static boolean isPalindrome(String string) {
        char[] arr = string.toCharArray();
        String string2 = "";
        for (char ch : arr) {
            if (Character.isLetterOrDigit(ch)) {
                string2 += Character.toLowerCase(ch);
            }
        }
        int beg = 0;
        int end = string2.length() - 1;
        while (beg < end) {
            if (string2.charAt(beg) != string2.charAt(end)) {
                return false;
            }
            beg++;
            end--;
        }
        return true;
    }

    public static int findKitty(String[] kittyArray) {
        int result = 0;

        for (String str : kittyArray) {
            if (str.contains("kitty")) {
                result++;
            }
        }

        return result;
    }
}
package restaurant.search;

/**
 * This program provides an implementation of the linear search algorithm
 * and demonstrates it.
 * @author cjgonz21
 * @version 1
 */
public class LinearSearch {

    public static boolean searchLinear(int target, int[] pool) {
        for (int i = 0; i < pool.length; i++) if (pool[i] == target) return true;

        return false;
    }

    public static boolean searchBinary(int target, int[] pool) {
        int lo = 0;
        int hi = pool.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (target < pool[mid]) hi = mid - 1;
            else if (target > pool[mid]) lo = mid + 1;
            else return true;
        }
        return false;
    }
}

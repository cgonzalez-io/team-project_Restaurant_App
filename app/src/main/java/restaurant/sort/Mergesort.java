package restaurant.sort;

import java.util.Arrays;

/**
 * A sample merge sort implementation using a topdown approach.
 *
 * @author cjgonz21
 * @version 1.0
 */

public class Mergesort {

  public static void sort(Comparable[] a) {
    Comparable[] aux = new Comparable[a.length];
    sort(a, aux, 0, a.length - 1);
    System.out.println("Final state of array a: " + Arrays.toString(a));
    assert isSorted(a);
  }

  private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
    if (hi <= lo) {
      System.out.println("Array a is already sorted: " + Arrays.toString(a));
      return;
    }
    int mid = lo + (hi - lo) / 2;
    sort(a, aux, lo, mid);
    sort(a, aux, mid + 1, hi);
    merge(a, aux, lo, mid, hi);
  }

  public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid,
                           int hi) {
    assert isSorted(a, lo, mid);
    assert isSorted(a, mid + 1, hi);

    int i = lo, j = mid + 1;

    for (int k = lo; k <= hi; k++)
      aux[k] = a[k];
    System.out.println("State of array aux after copying: " +
                       Arrays.toString(aux));

    // merge back to a[]
    for (int k = lo; k <= hi; k++) {
      if (i > mid)
        a[k] = aux[j++];
      else if (j > hi)
        a[k] = aux[i++];
      else if (less(aux[j], aux[i]))
        a[k] = aux[j++];
      else
        a[k] = aux[i++];
    }
    System.out.println("State of array a after merging: " + Arrays.toString(a));

    assert isSorted(a, lo, hi);
  }

  // helper
  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  private static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++)
      System.out.print(a[i] + " ");
    System.out.println();
  }

  public static boolean isSorted(Comparable[] a) {
    for (int i = 1; i < a.length; i++)
      if (less(a[i], a[i - 1]))
        return false;

    return true;
  }

  private static boolean isSorted(Comparable[] a, int lo, int hi) {
    for (int i = lo + 1; i <= hi; i++)
      if (less(a[i], a[i - 1]))
        return false;
    return true;
  }
}
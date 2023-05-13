package Test;

import com.company.DZ5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DZ5Tests {
    @Test
    public void d() {
        int[] arr = new int[] {55, 38, 11};
        DZ5.Quicksort(arr);
        Assertions.assertEquals(true, e_(new int[]{11, 38, 55}, arr));
        Assertions.assertEquals(1, DZ5.BinarySearch(arr, 38));
        DZ5.Node node = new DZ5.Node(
                new DZ5.Node(11),
                new DZ5.Node(55),
                38
        );
//        node.print();
        Assertions.assertEquals(104, node.sum);
    }

    boolean e_(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length)
            return false;
        boolean b = true;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] == arr2[i])
                b = true;
            else return false;
        }
        return b;
    }

}

package Other.CollectionFramework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTests {

    @Test
    public void someTest() {
        Main<Integer> main = new Main<>();
        Assertions.assertEquals(true, main.getLinkedList(1, 2, 3).equals(main.getArrList(1, 2, 3)));
    }

}

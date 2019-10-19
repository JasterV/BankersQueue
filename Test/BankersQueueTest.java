import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class BankersQueueTest {

    @Test
    void test_add_and_remove(){
        BankersQueue<Integer> bq = new BankersQueue<>();
        assertTrue(bq.isEmpty());
        assertEquals(bq.size(), 0);
        bq.add(3);
        bq.add(4);
        bq.add(43);
        bq.add(70);
        assertFalse(bq.isEmpty());
        assertEquals(bq.size(), 4);
        bq.remove();
        bq.add(7);
        assertFalse(bq.isEmpty());
        bq.remove();
        assertEquals(bq.size(), 3);
    }

    @Test
    void test_element(){
        BankersQueue<Integer> bq = new BankersQueue<>();
        bq.add(3);
        bq.add(4);
        bq.add(6);
        assertEquals(bq.element(), 3);
        bq.remove();
        assertEquals(bq.element(), 4);
    }

    @Test
    void test_iterator(){
        BankersQueue<Integer> bq = new BankersQueue<>();
        bq.add(3);
        bq.add(4);
        bq.element();
        bq.add(54);

        Iterator<Integer> it = bq.iterator();
        assertEquals(it.next(), 3);
        assertTrue(it.hasNext());
        assertEquals(it.next(), 4);
        assertTrue(it.hasNext());
        assertEquals(it.next(), 54);
        assertFalse(it.hasNext());
    }

}
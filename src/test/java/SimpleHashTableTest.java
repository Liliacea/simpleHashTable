import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;


public class SimpleHashTableTest {
    SimpleHashTable<Integer, String> t;


    /**
     * initialize
     */
    @Before
    public void setUp() {
        t = new SimpleHashTable<>();

        t.insert(1, "в");
        t.insert(3, "г");
        t.insert(5, "д");
        t.insert(6, "hh");

    }

    @Test
    public void getSize() {
        assertThat(t.getSize(), is(4));
    }

    @Test
    public void putAndGrow() {
        t.insert(1, "а");
        t.insert(2, "б");
        t.insert(3, "в");
        t.insert(4, "г");
        t.insert(5, "д");
        t.insert(6, "е");
        t.insert(7, "ё");
        t.insert(8, "ж");
        t.insert(9, "з");
        t.insert(10, "и");
        t.insert(11, "й");
        t.insert(12, "к");
        t.insert(13, "л");
        t.insert(14, "м");
        t.insert(15, "н");
        t.insert(16, "о");
        t.insert(17, "п");
        t.insert(18, "р");
        t.insert(19, "с");
        t.insert(20, "т");
        t.insert(21, "у");
        t.insert(22, "ф");
        t.insert(23, "х");
        t.insert(24, "ц");
        t.insert(25, "ч");
        t.insert(26, "ш");
        t.insert(27, "щ");
        t.insert(28, "э");
        t.insert(29, "ъ");
        t.insert(30, "ю");
        t.insert(31, "я");
        assertThat(t.getSize(), is(31));

    }

    @Test
    public void get() {
        assertThat(t.get(3), is("г"));
    }

    @Test
    public void remove() {
        t.delete(3);
        assertNull(t.get(3));
        assertThat(t.getSize(), is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void iteratorHasNextNextSequentialInvocation() {

        Iterator<Integer> it = t.keyIterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        it.next();

    }

    /**
     * Wrong order of invocation.
     */
    @Test(expected = NoSuchElementException.class)
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        Iterator<Integer> it = t.keyIterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        it.next();

    }

    /**
     * Multiply invocation of hasNext return the same values
     */
    @Test
    public void hasNextReturnTheSameValues() {
        Iterator<Integer> it = t.keyIterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }
}


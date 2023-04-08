import java.util.*;

public class SimpleHashTable<K, V> {
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    private float loadFactor = 0.75f;
    private float treshold;


    /**
     * These nodes are stored in an array.
     *
     * @param <K> the type of keys maintained by this map.
     * @param <V> the type of mapped values.
     */
    private class Node<K, V> {
        private final K key;
        private V value;
        private int hash;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;

        }


        @Override
        public int hashCode() {
            int hash = 31;
            hash = hash * 17 + key.hashCode();
            hash = hash * 17 + value.hashCode();
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof SimpleHashTable.Node) {
                Node<K, V> node = (Node) obj;
                return (Objects.equals(key, node.key) &&
                        Objects.equals(value, node.value) ||
                        Objects.equals(hash, node.hashCode()));
            }


            return false;
        }

        private K getKey() {
            return key;
        }
    }


    /**
     * Key iterator.
     *
     * @return key iterator.
     */
    public Iterator<K> keyIterator() {
        return new Iterator<K>() {
            int currentNodeNumber = 1;
            int currentArrayIndex = 0;
            Node<K, V> currentNode;

            @Override
            public boolean hasNext() {


                return currentNodeNumber <= size;
            }

            @Override
            public K next() {
                if (hasNext()) {
                    currentNode = table[currentArrayIndex];
                    while (currentNode == null) {
                        currentArrayIndex++;
                        currentNode = table[currentArrayIndex];
                    }


                        currentNodeNumber++;
                        currentArrayIndex++;

                        K key = currentNode.getKey();

                        return key;


                } else {
                    throw new NoSuchElementException();
                }


            }





        };

            }


    private Node<K, V>[] table;

    private int size;


    public SimpleHashTable() {

        table = new Node[DEFAULT_INITIAL_CAPACITY];
        treshold = table.length * loadFactor;
    }


    public int getSize() {
        return size;
    }

    public boolean insert(final K key, final V value) {
        if (size + 1 >= treshold) {
            treshold *= 2;
            growUp();
        }
        Node<K, V> newNode = new Node<>(key, value);
        int index = key.hashCode() % table.length;


        if (table[index] == null) {
            size++;
        }
        return add(index, newNode);


    }

    private void growUp() {
        Node<K, V>[] oldTable = table;
        table = new Node[oldTable.length * 2];
        size = 0;
        for (Node<K, V> node : oldTable) {
            if (node != null) {

                insert(node.key, node.value);
            }
        }


    }


    private boolean add(int index, Node<K, V> newNode) {
        table[index] = new Node<>(newNode.key, newNode.value);
        return true;
    }


    public V get(K key) {

        int index = key.hashCode() % table.length;
        if (table[index] == null) {
            return null;
        }
        return table[index].value;
    }


    public boolean delete(final K key) {
        int index = key.hashCode() % table.length;
        table[index] = null;
        size--;
        return false;
    }
}




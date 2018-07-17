public interface Map<K extends Comparable<K>, V> {
    void add(K  key, V value);

    boolean contains(K key);

    V  get(K k);

    void set(K k, V v) throws IllegalAccessException;

    V remove(K k);
}

package smartadapter.datatype;

import java.util.Map;

/**
 * Created by Manne Öhlund on 31/05/17.
 * Copyright © 2017 All rights reserved.
 */

public class KeyValue<K, V> implements Map.Entry<K, V> {
    private K key;
    private V value;

    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public K setKey(K key) {
        return this.key = key;
    }

    public V setValue(V value) {
        return this.value = value;
    }
}

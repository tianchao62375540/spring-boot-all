package com.tc.map;

/**
 * @Auther: tianchao
 * @Date: 2020/1/31 22:45
 * @Description:
 */
public class TcHashMap<K,V> implements TcMap<K,V> {

    private Entry<K,V>[] table = null;

    private int size = 0;

    private static int defaultLenth = 1<<4;

    public TcHashMap() {
        table = new Entry[defaultLenth];
    }

    @Override
    public V put(K key, V value) {
        int index = hash(key);
        Entry<K,V> entry = table[index];
        if (null == entry){
            table[index] = new Entry<>(key, value, null, index);
        }else{
            table[index] = new Entry<>(key, value, entry, index);
        }
        size ++;
        return table[index].getValue();
    }

    @Override
    public V get(K key) {
        if (size==0){
            return null;
        }
        int index = hash(key);
        Entry<K, V> kvEntry = table[index];
        if (kvEntry == null){
            return null;
        }
        /*while (kvEntry != null){
            if (kvEntry.getKey().equals(key)){
                return kvEntry.getValue();
            }
            kvEntry = kvEntry.next;
        }*/
        for (Entry<K,V> entry = table[index];entry!=null;entry = entry.next){
            if (entry.hash == index && (key==entry.getKey()||key.equals(entry.getKey()))){
                return entry.getValue();
            }
        }
        Entry<K, V> entry = getEntry(key, index);
        return entry == null?null:entry.getValue();
    }
    private Entry<K,V> getEntry(K key,int index){
        for (Entry<K,V> entry = table[index];entry!=null;entry = entry.next){
            if (entry.hash == index && (key==entry.getKey()||key.equals(entry.getKey()))){
                return entry;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    private int hash(K k){
        int index = k.hashCode()%(defaultLenth);
        return Math.abs(index);
    }

    class Entry<K,V> implements TcMap.Entry<K,V>{
        K k;
        V v;
        Entry<K,V> next;
        int hash;

        public Entry(K k, V v, Entry<K, V> next, int hash) {
            this.k = k;
            this.v = v;
            this.next = next;
            this.hash = hash;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }

    public static void main(String[] args) {
        TcMap<String,String> map = new TcHashMap<>();
        map.put("a", "b");
        System.out.println(map.get("a"));
        System.out.println(map.get("c"));
        for (int i = 0; i < 50000000; i++) {
            map.put("tianchao"+i, "田超-"+i);
            System.out.println(map.get("tianchao"+i));
        }
    }
}

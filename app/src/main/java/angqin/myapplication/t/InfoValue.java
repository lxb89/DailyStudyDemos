package angqin.myapplication.t;

/**
 * 作者：${lixuebin} on 2018/1/30 16:12
 * 邮箱：2072301410@qq.com
 */
public class InfoValue<T,K> {
    private T key;
    private K value;

    public InfoValue(T key, K value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public K getValue() {
        return value;
    }

    public void setValue(K value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "InfoValue{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}

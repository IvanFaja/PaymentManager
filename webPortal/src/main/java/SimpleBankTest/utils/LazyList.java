package SimpleBankTest.utils;

import java.util.AbstractList;

/**
 * Created by ifAJARD on 30/10/14.
 */
public class LazyList extends AbstractList {
    private int size =0;

    public LazyList(int size) {
        this.size = size;
    }

    @Override
    public Integer  get(int index) {
        return Integer.valueOf(index);
    }

    @Override
    public int size() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

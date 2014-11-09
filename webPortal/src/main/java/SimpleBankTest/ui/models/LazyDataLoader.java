package SimpleBankTest.ui.models;

import java.util.List;


/**
 * Created by ifAJARD on 29/10/2014.
 */
public interface LazyDataLoader<T>{
    public int size();
    List<T> get(int offset, int i);
}

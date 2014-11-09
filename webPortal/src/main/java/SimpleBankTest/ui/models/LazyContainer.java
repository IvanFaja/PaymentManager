package SimpleBankTest.ui.models;

import SimpleBankTest.utils.LRUMap;
import SimpleBankTest.utils.LazyList;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;

import java.util.*;

public class LazyContainer implements Container {
    private LazyDataLoader query;

    private int offset = 0;
    private int batchSize =200;
    private int MAX_SIZE =500;
    private Map<Integer,Item> items;
    private int size;
    private List<Integer> ids;
    private BeanItem itemData;
    public LazyContainer(LazyDataLoader query,Object o) {
        this.query = query;
        size = query.size();
        items = new LRUMap(batchSize<size?batchSize:size,MAX_SIZE);
        ids = new LazyList(size);
        itemData = new BeanItem(o);
        update();
    }

    private void update() {
        List elements = query.get(offset,offset+batchSize);
        for(int i=0,j=elements.size();i<elements.size();i++){
            int index = i + offset;
            items.put(index,new BeanItem(elements.get(i)));
        }
    }


    @Override
    public Item getItem(Object id) {
        if(!items.containsKey(id)){
           int index =  (Integer)id-batchSize/2;
           offset = index < 0?0:index;
           update();
        }
        return items.get(id);
    }

    @Override
    public Collection<?> getContainerPropertyIds() {
        return itemData.getItemPropertyIds();
    }

    @Override
    public Collection<?> getItemIds() {
        return ids;
    }

    @Override
    public Property getContainerProperty(Object itemId, Object propertyId) {
        return getItem(itemId).getItemProperty(propertyId);
    }

    @Override
    public Class<?> getType(Object propertyId) {
        return itemData.getItemProperty(propertyId).getType();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsId(Object id) {
        return items.containsKey(id);
    }

    @Override
    public Item addItem(Object o) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object addItem() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeItem(Object o) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addContainerProperty(Object o, Class<?> aClass, Object o2) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeContainerProperty(Object o) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAllItems() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public void resetCache() {
        items.clear();
        size = query.size();
        ids = new LazyList(size);
    }
}

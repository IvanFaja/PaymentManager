package SimpleBankTest.ui.customLayouts;

import com.vaadin.data.Item;
import com.vaadin.ui.*;

/**
 * Created by ifAJARD on 7/11/14.
 */
public class ItemLayout extends VerticalLayout {
   public void addItem(Item item) {
        for (Object id : item.getItemPropertyIds()) {
            setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
            GridLayout gridLayout = new GridLayout(2,1);
            gridLayout.setSpacing(true);
            gridLayout.setMargin(true);
            String stringValue = item.getItemProperty(id).getValue().toString();
            String stringId = id.toString();
            Label label = new Label(stringId);
            Label value = new Label(stringValue);
            gridLayout.addComponents(label,value);
            addComponent(gridLayout);
        }
    }
}

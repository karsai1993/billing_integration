package laszlo.karsai.billing.integration.solution;

import com.flexionmobile.codingchallenge.integration.Purchase;

public class PurchaseImpl implements Purchase {

    private String id;
    private boolean isConsumed;
    private String itemId;

    public PurchaseImpl(String idInit, boolean isConsumedInit, String itemIdInit) {
        this.id = idInit;
        this.isConsumed = isConsumedInit;
        this.itemId = itemIdInit;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean getConsumed() {
        return isConsumed;
    }

    @Override
    public String getItemId() {
        return itemId;
    }

    public void setId(String idInit) {
        this.id = idInit;
    }

    public void setIsConsumed(boolean isConsumedInit) {
        this.isConsumed = isConsumedInit;
    }

    public void setItemId(String itemIdInit) {
        this.itemId = itemIdInit;
    }
}

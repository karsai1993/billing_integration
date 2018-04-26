package laszlo.karsai.billing.integration.solution;

import com.flexionmobile.codingchallenge.integration.Purchase;

public class PurchaseImpl implements Purchase {

    private String mId;
    private boolean mIsConsumed;
    private String mItemId;

    public PurchaseImpl(String id, boolean isConsumed, String itemId) {
        this.mId = id;
        this.mIsConsumed = isConsumed;
        this.mItemId = itemId;
    }

    @Override
    public String getId() {
        return mId;
    }

    @Override
    public boolean getConsumed() {
        return mIsConsumed;
    }

    @Override
    public String getItemId() {
        return mItemId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public void setIsConsumed(boolean isConsumed) {
        this.mIsConsumed = isConsumed;
    }

    public void setItemId(String itemId) {
        this.mItemId = itemId;
    }
}

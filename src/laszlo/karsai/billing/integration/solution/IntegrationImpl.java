package laszlo.karsai.billing.integration.solution;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;

import java.util.List;

public class IntegrationImpl implements Integration {
    @Override
    public Purchase buy(String itemId) {
        return null;
    }

    @Override
    public List<Purchase> getPurchases() {
        return null;
    }

    @Override
    public void consume(Purchase purchase) {

    }
}

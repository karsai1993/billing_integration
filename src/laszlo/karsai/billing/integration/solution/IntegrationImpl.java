package laszlo.karsai.billing.integration.solution;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class IntegrationImpl implements Integration {

    private static final String BASE_URL = 
    		"http://sandbox.flexionmobile.com/javachallenge/rest/developer/laszlo_karsai/";
    private static final String BUY_URL_PART = "buy/";
    private static final String ALL_PURCHASED_URL_ENDING = "all";
    private static final String CONSUME_URL_PART = "consume/";
    private static final String POST_REQUEST_METHOD = "POST";
    private static final String GET_REQUEST_METHOD = "GET";
    private static final int ERROR_RESPONSE_CODE = 404;
    private static final String ERROR_MESSAGE = 
    		"The following request resulted in error with response code 404: ";
    private static final String CONSUMED_KEY = "consumed";
    private static final String ID_KEY = "id";
    private static final String ITEM_ID_KEY = "itemId";
    private static final String PURCHASES_KEY = "purchases";
    
    /**
     * This is the function to call the purchase of an item, and to
     * initiate the bought item as a Purchase object.
     * @param itemId
     * @return Purchase object
     */
    @Override
    public Purchase buy(String itemId) {
    	String buyResponse = performRequest(
    			BUY_URL_PART + itemId,
    			POST_REQUEST_METHOD
    	);
    	if (buyResponse == null) return null;
        return getPurchase(buyResponse);
    }

    /**
     * This is the function to get all the purchased items,
     * initiate each of them as a Purchase object, 
     * and to put them into a list.
     * @return List of Purchase objects
     */
    @Override
    public List<Purchase> getPurchases() {
    	String allPurchasedResponse = performRequest(
    			ALL_PURCHASED_URL_ENDING,
    			GET_REQUEST_METHOD
    	);
    	if (allPurchasedResponse == null) return null;
    	return getPurchasedItemList(allPurchasedResponse);
    }

    /**
     * This is the function to call the consumed status change of a Purchase object.
     * @param purchase
     */
    @Override
    public void consume(Purchase purchase) {
    	performRequest(
    			CONSUME_URL_PART + purchase.getId(),
    			POST_REQUEST_METHOD
    	);
    }
    
    /**
     * Function to get a Purchase object based on buy request.
     * @param buyResponse
     * @return Purchase object
     */
    private Purchase getPurchase(String buyResponse) {
    	JSONObject responseJSON = new JSONObject(buyResponse);
    	return parsePurchaseFromJSON(responseJSON);
    }
    
    /**
     * Function to get a List of Purchase objects based on all request.
     * @param allPurchasedResponse
     * @return List of Purchase objects
     */
    private List<Purchase> getPurchasedItemList(String allPurchasedResponse) {
    	JSONObject responseJSON = new JSONObject(allPurchasedResponse);
    	JSONArray purchasedJSONArray = responseJSON.getJSONArray(PURCHASES_KEY);
    	List<Purchase> purchaseList = new ArrayList<>();
    	for (int i = 0; i < purchasedJSONArray.length(); i++) {
    		JSONObject purchasedItemJSON = purchasedJSONArray.getJSONObject(i);
    		purchaseList.add( parsePurchaseFromJSON(purchasedItemJSON) );
    	}
    	return purchaseList;
    }
    
    /**
     * Function to parse the JSON values to a Purchase object.
     * @param jsonObject
     * @return Purchase object
     */
    private Purchase parsePurchaseFromJSON(JSONObject jsonObject) {
    	boolean isConsumed = jsonObject.optBoolean(CONSUMED_KEY);
    	String id = jsonObject.optString(ID_KEY);
    	String itemId = jsonObject.optString(ITEM_ID_KEY);
    	return new PurchaseImpl(id, isConsumed, itemId);
    }
    
    /**
     * Function to perform the request and get the JSON response from server.
     * @param urlEnding
     * @param requestMethod
     * @return JSON text
     */
    private String performRequest(String urlEnding, String requestMethod) {
    	String urlPath = BASE_URL + urlEnding;
    	URL url = null;
    	HttpURLConnection httpConnection = null;
    	try {
			url = new URL(urlPath);
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod(requestMethod);
			int responseCode = httpConnection.getResponseCode();
			if (responseCode == ERROR_RESPONSE_CODE) {
				throw new Exception(ERROR_MESSAGE + urlPath);
			} else {
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(httpConnection.getInputStream())
				);
				String line;
				StringBuffer stringBuffer = new StringBuffer();
				while((line = bufferedReader.readLine()) != null) {
					stringBuffer.append(line);
				}
				bufferedReader.close();
				return stringBuffer.toString();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
}

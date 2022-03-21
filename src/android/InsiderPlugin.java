package insider.cordova.insider;

import com.useinsider.insider.Insider;
import com.useinsider.insider.InsiderCallback;
import com.useinsider.insider.InsiderCallbackType;
import com.useinsider.insider.ContentOptimizerDataType;

import com.google.firebase.iid.FirebaseInstanceId;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CordovaPreferences;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;

public class InsiderPlugin extends CordovaPlugin {
    private String partnerName = "";

    @Override
    protected void pluginInitialize() {
        Log.d("Insider Cordova Plugin", "Initialized");

        Insider.Instance.init(
            this.cordova.getActivity(),
            partnerName
        );
    }

    @Override
    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) {
        if (args == null || args.length() == 0) {
            return false;
        }

        try {
            if (action.equals("init")) {
                Log.d("Insider Cordova Plugin Partner Name", partnerName);
            } else if (action.equals("setGDPRConsent")) {
                Insider.Instance.setGDPRConsent(Boolean.parseBoolean(args.getString(0)));
            } else if (action.equals("enableIDFACollection")) {
                Insider.Instance.enableIDFACollection(Boolean.parseBoolean(args.getString(0)));
            } else if (action.equals("startTrackingGeofence")) {
                Insider.Instance.startTrackingGeofence();
            } else if (action.equals("tagEvent")) {
                Insider.Instance.tagEvent(args.getString(0)).build();
            } else if (action.equals("getContentStringWithName")) {
                ContentOptimizerDataType stringVariableDataType = getDataType(args.getString(2));

                String optimizedString = Insider.Instance.getContentStringWithName(args.getString(0), args.getString(1), stringVariableDataType);

                if (optimizedString != null && optimizedString.length() > 0) {
                    callbackSuccess(callbackContext, optimizedString);
                }
            } else if (action.equals("getContentIntWithName")) {
                ContentOptimizerDataType intVariableDataType = getDataType(args.getString(2));

                int optimizedInteger = Insider.Instance.getContentIntWithName(args.getString(0), args.getInt(1), intVariableDataType);

                callbackSuccess(callbackContext, optimizedInteger);
            } else if (action.equals("getContentBoolWithName")) {
                ContentOptimizerDataType boolVariableDataType = getDataType(args.getString(2));

                boolean optimizedBoolean = Insider.Instance.getContentBoolWithName(args.getString(0), args.getBoolean(1), boolVariableDataType);

                callbackSuccess(callbackContext, optimizedBoolean);
            } else if (action.equals("removeInapp")) {
                Insider.Instance.removeInapp(this.cordova.getActivity());
            } else {
                return false;
            }

            return true;
        } catch (Exception e) {
            Insider.Instance.putException(e);
        }

        return false;  
    }
             
    private static ContentOptimizerDataType getDataType(String dataType){
        if (dataType.equals("Content")) {
            return ContentOptimizerDataType.CONTENT;
        } else {
            return ContentOptimizerDataType.ELEMENT;
        }
    }

    private static void callbackSuccess(CallbackContext callbackContext, String callbackValue) {
        try {
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, callbackValue);

            pluginResult.setKeepCallback(true);

            callbackContext.sendPluginResult(pluginResult);
        } catch (Exception e) {
            Insider.Instance.putException(e);
        }
    }

    private static void callbackSuccess(CallbackContext callbackContext, int callbackValue) {
        try {
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, callbackValue);

            pluginResult.setKeepCallback(true);

            callbackContext.sendPluginResult(pluginResult);
        } catch (Exception e) {
            Insider.Instance.putException(e);
        }
    }

    private static void callbackSuccess(CallbackContext callbackContext, boolean callbackValue) {
        try {
            PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, callbackValue);

            pluginResult.setKeepCallback(true);

            callbackContext.sendPluginResult(pluginResult);
        } catch (Exception e) {
            Insider.Instance.putException(e);
        }
    }
}
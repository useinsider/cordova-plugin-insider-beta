let exec = require('cordova/exec');
let insider = {};

// You must use your partner name on your Inone panel.
insider.init = (partnerName) => {
    exec(null, null, 'insider', 'init', [partnerName]);
};

// You can send boolean data to enable or disable GDPR. It is disabled by default.
insider.setGDPRConsent = (booleanValue) => {
    exec(null, null, 'insider', 'setGDPRConsent', [booleanValue.toString().toLowerCase()]);
};

// You can send boolean data to enable or disable IDFA Collection. It is disabled by default.
insider.enableIDFACollection = (booleanValue) => {
    exec(null, null, 'insider', 'enableIDFACollection', [booleanValue.toString().toLowerCase()]);
};

// You can use this function to activate geofence.
insider.startTrackingGeofence = () => {
    exec(null, null, 'insider', 'startTrackingGeofence', []);
};

// You can use this function to trigger your events that you will use in your campaigns.
insider.tagEvent = (eventName) => {
    exec(null, null, 'insider', 'tagEvent', [eventName]);
};

// With this method, you can uninstall inApp applications on the screen.
insider.removeInapp = () => {
    exec(null, null, 'insider', 'removeInapp', []);
};

// Only iOS Platform for push notification permission.
insider.registerWithQuietPermission = (booleanValue) => {
    exec(null, null, 'insider', 'registerWithQuietPermission', [booleanValue.toString().toLowerCase()]);
};

module.exports = insider;

window.Insider = insider;

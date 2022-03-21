# Insider Cordova Plugin - integration DOC

## Cordova Integration

This plugin enables Cordova projects to use the Insider SDK in their projects.

:information_source:	Cordova SDK is compatible with SDK 10.0.0

## Start

To add the plugin to your Cordova Project, you can run this code in the project directory by referring to the code example below.

Add this code to your config.xml file to pull from GitHub.

```
<plugin name="cordova-plugin-insider" spec="https://github.com/useinsider/cordova-plugin-insider-beta.git"/>
```

And run the code below

```
cordova plugin add cordova-plugin-insider
```

### Add Android and iOS platforms to your project.

```
cordova platform add android
cordova platform add ios
```

## Android Integration

- Append the following lines to `android/build.gradle` (Project Level Gradle):

```
buildscript{
    dependencies{
        classpath 'com.google.gms:google-services:4.2.0'
    }
}

apply plugin: 'com.google.gms:google-services'
```

- Replace the following lines to `platforms/android/app/cordova-plugin-insider/cordova-insider-internal-build.gradle` (Module Level Gradle):

```
// Replace the placeholder partnerName with your actual partner name.

android{
    defaultConfig{
        manifestPlaceholders = [ partner : 'partnerName']
    }
}
```

- Replace the following lines to `plugins/cordova-plugin-insider/src/android/InsiderPlugin.java` (Module Level Gradle):

```
// Replace the partnerName with your actual partner name.

private String partnerName = "";
```

## iOS Integration

- Replace the following lines to `plugins/cordova-plugin-insider/src/ios/insider.m` (Module Level Gradle):

```
// Replace the app group name.

static NSString *APP_GROUP = @"group.com.useinsider.plugin";
```

## Cordova SDK Integration

Thanks to the codes in the plugin in  insider.js, you can init the SDK with the Insider variable under the window and use other auxiliary functions.

Please open the component which you registered to the AppRegistry.

```
window.Insider.init('partnerName');
```

## Android Push Notification Integration

Push notifications are out-of-app alerts that appear directly on a user’s screen. Push notifications are a valuable way to provide your users with time-sensitive and relevant content or to re-engage them with your app.

In addition, you need to download the ```google-services.json``` file from the Google Firebase panel and include it in your project.

androidX and enableJetifier properties must be true. You can have a look at the GradlePropertiesParser.js file for this.

You can run the code below to get build via terminal.

```
cordova build android
```

## iOS Push Notification Integration
Before moving onto code integration, make sure that you have Apple Push Notification Service Certificate. Please note that, we do not accept Encrypted certificates.

In order to use push notification service from Apple, first you need to enable Push Notification. Select your project under Targets and go to Capabilities. Find Push Notifications from the list and enable it. Then find Background Modes and check Remote Notifications and Background Fetch.

Detailed information about integration, you can reach the documentation after logging in to the panel with the URL address below.

https://academy.useinsider.com/docs/ios-integration

It is enough to do the first 4 steps at this address. Other documentation advanced methods in the URL may not be up to date for Cordova.

## iOS and Android Custom Events and Attributes

Events and attributes are crucial for personalizing campaigns. Insider tracks some events and attributes automatically. These are:

- Events:
  * app_started_android (Android session Start)
  * push_session (session Start from push)
  * item_purchased (tracked by default if TrackSales method integrated)
- Attributes:
  * Platform
  * First Time Open
  * Last Time Open
  * App Version
  * Device Model
  * Device Language
  * Carrier
  * UDID
  * Advertising ID
  * Insider SDK Version
  * Device Software Version

### FORMATTING
The standard naming convention for tag events and attributes is as follows:

- Only English characters (ISO basic Latin alphabet)
- 100 characters max.
- Lowercase
- No spaces, use underscores
- No special characters (e.g. &^+%!)

(Example: product_detail_visit, first_name)

## Tracking Events

Events are the actions a user makes inside your app. Following how often your users trigger these pre-defined events will give you insight on how your users are using your application. Custom events are also used for segmenting users by their actions in the application.

To track events you need to use tagEvent method:

```
window.Insider.tagEvent("eventName");
```

## Products that you can use up to date with the plugin

- App Templates (In Apps)
- Notification Push
- App Templates From Push
- App Survey
- Event Attribute (Feature)

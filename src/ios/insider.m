#import <Cordova/CDV.h>
#import "Insider.h"

@interface insider : CDVPlugin {

}

- (void) init:(CDVInvokedUrlCommand *)command;
- (void) registerWithQuietPermission:(CDVInvokedUrlCommand *)command;
- (void) enableIDFACollection:(CDVInvokedUrlCommand *)command;
- (void) setGDPRConsent:(CDVInvokedUrlCommand *)command;
- (void) startTrackingGeofence:(CDVInvokedUrlCommand *)command;
- (void) tagEvent:(CDVInvokedUrlCommand *)command;
@end

@implementation insider

// The variable for your app group name that you will use to init the SDK.
static NSString *APP_GROUP = @"group.com.useinsider.plugin";

- (void) pluginInitialize {
    NSLog(@"Insider Cordova Plugin: Initialized");
}

- (void) init:(CDVInvokedUrlCommand *)command {
    @try {
        NSString* partnerName = [[command arguments] objectAtIndex:0];
        
        [Insider initWithLaunchOptions:nil partnerName:partnerName appGroup:APP_GROUP];
    } @catch (NSException *exception) {
        [Insider sendError:exception desc:@"insider.m - init"];
    }
}

- (void) registerWithQuietPermission:(CDVInvokedUrlCommand *)command {
    @try {
        NSString* booleanValueByString = [[command arguments] objectAtIndex:0];
        
        [Insider registerWithQuietPermission:[booleanValueByString isEqualToString: @"true"]];
    } @catch (NSException *exception) {
        [Insider sendError:exception desc:@"insider.m - registerWithQuietPermission"];
    }
}

- (void) enableIDFACollection:(CDVInvokedUrlCommand *)command {
    @try {
        NSString* booleanValueByString = [[command arguments] objectAtIndex:0];
        
        [Insider enableIDFACollection:[booleanValueByString isEqualToString: @"true"]];
    } @catch (NSException *exception) {
        [Insider sendError:exception desc:@"insider.m - enableIDFACollection"];
    }
}

- (void) setGDPRConsent:(CDVInvokedUrlCommand *)command {
    @try {
        NSString* booleanValueByString = [[command arguments] objectAtIndex:0];
        
        [Insider setGDPRConsent:[booleanValueByString isEqualToString: @"true"]];
    } @catch (NSException *exception) {
        [Insider sendError:exception desc:@"insider.m - setGDPRConsent"];
    }
}

- (void) startTrackingGeofence:(CDVInvokedUrlCommand *)command {
    @try {
        [Insider startTrackingGeofence];
    } @catch (NSException *exception) {
        [Insider sendError:exception desc:@"insider.m - startTrackingGeofence"];
    }
}

- (void) tagEvent:(CDVInvokedUrlCommand *)command {
    @try {
        NSString* eventName = [[command arguments] objectAtIndex:0];
        
        [[Insider tagEvent:eventName] build];
    } @catch (NSException *exception) {
        [Insider sendError:exception desc:@"insider.m - tagEvent"];
    }
}

- (void) removeInapp:(CDVInvokedUrlCommand *)command {
    @try {
        [Insider removeInapp];
    } @catch (NSException *exception) {
        [Insider sendError:exception desc:@"insider.m - tagEvent"];
    }
}

@end

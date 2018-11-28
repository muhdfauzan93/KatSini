# KatSini - Cordova plugin location for android

Simple plugin to get current user location using Google Play Service.

## Using

Create a new Cordova Project

    $ cordova create hello com.example.helloapp Hello

Install the plugin

    $ cd hello
    $ cordova plugin add https://github.com/muhdfauzan93/KatSini.git

Edit `www/js/index.js` and add the following code inside `onDeviceReady`

```js
var success = function(location) {
  alert(
    'Latitude: ' + location.latitude + '||' + 'Longitude:' + location.longitude
  );
};

var failure = function() {
  alert('Error calling KatSini Plugin');
};

katsini.currentLocation(success, failure);

katsini.stoptLocation();
```

Install iOS or Android platform

    cordova platform add ios
    cordova platform add android

Run the code

    cordova run

## More Info

For more information on setting up Cordova see [the documentation](http://cordova.apache.org/docs/en/latest/guide/cli/index.html)

For more info on plugins see the [Plugin Development Guide](http://cordova.apache.org/docs/en/latest/guide/hybrid/plugins/index.html)

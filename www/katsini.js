/*global cordova, module*/

module.exports = {
  currentLocation: function(successCallback, errorCallback) {
    cordova.exec(
      successCallback,
      errorCallback,
      'KatSini',
      'currentLocation',
      []
    );
  }
};

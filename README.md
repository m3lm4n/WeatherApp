# Small weather widget app

# Design
There are 2 parts to the app. First one is a widget, that displays current weather and a configuration activity, currently only used to grant the location permission.

For `ConfigurationActivity` I used a typical `Activity <- ViewModel` stack. With `StateFlow` used for communication between components.

For widget the necessity of using `RemoteViews` and `BroadcastReciever`(for updates) caused me to use different approach. 
Most of the used components are held by the `BroadcastReciever` which I think would be good to escape given more time.

# Caveats

I decided to skip any automated tests, since their utility for such small projects are very limited.

In some places I skipped error handling in the interest of time. All of such cases should be commented.

The `ConfigurationActivity`becomes empty after permission is granted and doesn't immediately update the widget.

# Used libraries
Retrofit, Gson for api communication 
Koin for dependency injection

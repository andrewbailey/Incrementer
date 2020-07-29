# Incrementer
Incrementer is an extremely basic counter app designed to demonstrate various correct and incorrect ways that developers may decide to implement state management in their Android apps.
I have seen every single one of these incorrect implementations in production apps — apps that have between tens of thousands and millions of downloads!
My goal is to educate more developers on how to avoid these mistakes and build apps without subtle bugs that result from these incorrect implementations.

This app is featured in my talk called Falsehoods Android Developers Believe about Lifecycles, which you can find more information about at [andrewbailey.dev/falsehoods](https://andrewbailey.dev/falsehoods).

Here's an overview of how each implementation behaves:

| Implementation | Supports config changes? | Supports process death? |
|-------------------------------|:---:|:---:|
| `savedInstanceState` Bundle   | ✅ | ✅ |
| ViewModel + SavedState        | ✅ | ✅ |
| ViewModel only                | ✅ | ❌ |
| Portrait-locked               | ❌ | ❌ |
| Portrait and not-resizable    | ❌ | ❌ |
| `android:configChanges`       | ❌ | ❌ |

## Recommended Implementations

### `savedInstanceState` Bundle
A classic implementation of `onSaveInstanceState(Bundle)`.
This handles all Activity recreation situations.

### ViewModel + SavedState
This implementation uses the androidx ViewModel library in addition to the SavedState library.
It's a good idea to implement SavedState in your app so that your app state can be fully restored when Android kills your app's process.
You can also accomplish the same thing as the SavedState library by manually copying data from your ViewModel into your `savedInstanceState` bundle, if you prefer.

## Incomplete Implementations

### Portrait-locked Activity
This is an Activity that has been locked to portrait.
It avoids the most common cause for your activity to be restarted, but it barely scratches the surface of events that can cause your activity to restart.

You can do any of these actions to cause state loss:
 - Trigger a more rare configuration change
   - Change the text scaling size
   - Change the display scaling size (API 24 and higher)
   - Change the device language
   - If running on a foldable phone, fold or unfold the phone
 - Enter Multi-Window
 - Evict the process from memory (see the section on ViewModel for more info)

### Portrait-locked and not-resizable Activity
This is simply a portrait-locked Activity without support for Multi-Window.
It also causes your app to use a compatibility mode on foldable phones, which is a sub-optimal user experience.

All of the actions that cause state loss for the regular portrait-locked activity will also cause state loss for the not-resizable activity.

### `android:configChanges`
This is an Activity that avoids activity recreations for certain kinds of configuration changes.
The documentation strongly advises against using this field in general.

Many of the actions that trigger state loss for the portrait-locked activity will also cause the `android:configChanges` activity to lose state:
 - Trigger a more rare configuration change
   - Change the text scaling size
   - Change the display scaling size (API 24 and higher)
   - Change the device language
 - Evict the process from memory (see the section on ViewModel for more info)

### ViewModel only
This is a much more modern and growingly common way of handling Activity state.
ViewModel by itself can handle every configuration change scenario, but there's an extra mile you should consider going if you want to provide the best possible user experience in all cases.
If you're using a ViewModel, you should also be storing user inputs and other states in the `savedInstanceState` bundle either manually or by using the SavedState library, mentioned above.

If you're using just a ViewModel, you can trigger state loss by causing your app process to be evicted from memory.
There are a few ways to do this:
 - **Option 1:** Use Android Studio
   - Launch the app with Android Studio
   - On the phone, press the home button to leave the app
   - In the "Run" panel of Android Studio, press the stop button
   - Restart the app either from the launcher or from the recent apps page
 - **Option 2:** Wait
   - Launch the app and place it into the background
   - Open other memory-intensive apps
   - Eventually, if there's enough memory pressure, Android will kill the app to reclaim memory
 - **Option 3:** Revoke a permission
   - There isn't a mechanism in place for apps to listen for when the user revokes a permission. Instead, Android will kill your app process when a permission is revoked.
   - Incrementer doesn't have any permissions, but if your app does then you can revoke a permission to trigger process death.
   - This behaves exactly the same as using Android Studio or placing the app in the background for long enough

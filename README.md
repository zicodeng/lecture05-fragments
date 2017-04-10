# 04-12 Lab: Dual Pane Layouts
For this lab, you will practice working with a ___two-pane fragment layout___, similar to what you will have to do for your homework. **Your task** is to add a ___landscape mode___ display for our Movie application so that, when in "landscape" orientation, it shows the `MoviesFragment` on the left side of the screen and the `DetailFragment` (if a movie is selected) on the right side.

- IMPORTANT NOTE: you should **not** modify the fragments for this! Fragments are self-contained and don't care where they are positioned. All you should do is adjust the resources and the `MainActivity`.

### 1. Support the landscape configuration
You will need to add an environment-specific layout resource to your application, one that applies when the phone is in __landscape orientation__. You can use Android Studio to add this layout resource file (be sure and specify a _qualifier_), or simply create a new `res/layout` folder with the appropriate config tag (e.g., the `-something` in the name).

- Note that you can switch the emulator into _landscape orientation_ by hitting `ctrl-F11`, You can also specify that you want the emulator to launch in landscape mode by editing the virtual device configuration (`Tools > Android > AVD Manager` in Android Studio)

This resource should look a lot like the `activity_main` resource used by default, except it should have _two_ containers (e.g., `id/container_left` and `id/container_right`).


### 2. Respond to different configurations
Inside `MoviesActivity`, you'll need to determine what configuration you're in so you know where to put the fragments. The recommended way to do this is to specify an **instance variable** in your `onCreate()` method (since an orientation change causes the Activity to be recreated) that determines whether the current view is `dualPane` or not.

You can determine if the current layout is dual-pane by checking whether the second _container_ view is being shown. Use `findViewById()` to look up that View, and if it exists and <a href="http://developer.android.com/reference/android/view/View.html#getVisibility()">is visible</a>, then you know you're in "dual-pane" mode.

- There are also ways to [get information about the configuration](http://developer.android.com/reference/android/content/res/Configuration.html) directly, which you could use to check for a landscape orientation. However, checking for a visible View is more flexible--it allows our `Activity` to work _whenever_ there is the second container available (such as if we decided to split the view for large tablets, for example). Let the Android system handle configurations, and have your code just react to what resources it chooses to use!


### 3. Place the fragments
Now you can choose where to put the fragments. **Whenever** you add a Fragment to the Activity (and this may happen a lot!), you should check for whether you are in dual-pane mode. If you are, then you should put the Fragment in its appropriate container. If you're not, then you can... still put the Fragment in its appropriate container (which is probably a different container)!

Now you should be able to switch between orientations and have your app still work, and respond with the correct layout and behavior!

- Note that I've included some pieces to remember the search term used across rotations, so you should be able to get a pretty smooth interaction.

### 4. Other UI Fixes
It's possible some other UI issues have popped up in the process of adding this extra functionality. If you find any problems with the user experience, spend some time looking up how to solve it! It's the best way to learn the ins-and-outs of the system.


#### References
- [Implementing Adaptive UI Flows](http://developer.android.com/training/multiscreen/adaptui.html)
- [Handling Runtime Changes](http://developer.android.com/guide/topics/resources/runtime-changes.html)

# MathmaticFanatic
_A swen-anigans project_

Beta Release
MathematicFanatic_0.5.2

## Purpose
Mathmatic fanatic is an Android Application developed to help child students who are either
struggling to learn their multiplication tables or want additional practice. This application
is not meant to replace school teachings, merely to suppliment it. 

## Running the Beta Release
The Beta Release is meant to run on Android 6.0 or higher.
Download the APK file onto your Android device. Navigate to the APK file in your device's file explorer. Tap the MathematicFanatic_0.5.2.apk file
and install the application. Once application is downloaded, tap "Open". 

## Known Issues
- The formatting and alignment doesn't always appear properly when running on older versions of Android.
- Timer for the Quiz function is not fully functional.
- Working with the emulator, clicks are responded to slowly, and functionality appears broken.
- Some of our designs for interaction weren't possible to implement in Android within a reasonable amount of time, for the beta prototype:
  (range picker, button sizes/margins)
- Settings aren't yet reflected in the functionality:
  ("range" is limited to 3's and 8's, Videos not yet based off of interests, Concept Learning is limited to Visualization)
- Videos aren't related to the reward. They are only sample video content.
- Back button from Help activity returns user to start of Classroom functionality instead of back to the place where the user left off.
- Classroom's "Check" button doesn't yet display correct/incorrect.

## Getting Started:
1. Since this repository only contains the app folder of the android application, the first thing we need to do is create the android project correctly
  1. Open Android studio and click "create new project"
  2. In "Application name" type in "MathematicFanatic" and in "Company Domain" type in "swen_anigains"
  3. Check off "Phone and Tablet" and select "API 23: Android 6.0(Marshallow)" in the Minimum SDK dropdown.
  4. Select "Add No Activity" and click finish.
2. Then with your favorite git client (I used git bash), add this repo as a remote to your project then pull 'origin master'. With git commandline I did:
   ```
      git remote add origin https://github.com/sklei2/MathematicFanatic.git
      git fetch --all
      git reset --hard origin/master
   ```
   
   and it seemed ok.
   
   You may be able to clone, but it would probably involve moving folders around so your cloned app folder is in the android application.
   
3. After you get everything make sure to create an emulator that is running the API Above. 
  1. Tools->Android->AVD Manager
  2. Create Virtual Device
  3. Select a phone device
  4. Make sure you choose the API from above.
  5. Give it a name and finish.
4. Try to run the application and see if the "MathematicFanatic" Application Appears.
5. If it does you're ready to go!!!
     
  

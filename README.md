AsyncFragment
=============

A simple wrapper for Android Fragments that shows a loading animation until you are ready to show content that has loaded asynchronously.

Usage
=====

Add this file to your Android project and inherit AsyncFragment instead of Fragment.  

Do not override onCreateView; instead, inflate your view in onStart and pass it to setView.

Once you are finished populating the view, call setContentShown to remove the loading animation and show your content.

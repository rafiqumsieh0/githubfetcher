# Android Github Commit-Fetching App

This app is a single-activity app that fetches commits from a certain Github repository (specified in the strings resources), and displays them in a List. The app is built using the recommended MVVM architecture pattern along with Room, Retrofit, RxJava, and Dagger.

## Dependencies

All additional dependencies can be found in the gradle file for the app module.

## API Level

This App Works on Min SDK level 21, and targets SDK Level 30.

## Bugs

Currently, there is a bug when loading locally-saved data from Room in offline mode (no internet connectivity). It saves twice, and profile picture urls need to be saved too.

## Needed Improvements

- More Code Refactoring
- Adding Infinite Scrolling
- Better UI
- Adding a Detail Activity

## Changing Repositories

You can change to any repository that you want by editing the following string resources (Repository must be public):
- **github_repository_owner** : Repository's owner username
- **github_repository_name** : Repository's name


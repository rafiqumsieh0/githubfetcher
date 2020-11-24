# Android Github Commit-Fetching App

This app is a single-activity app that fetches commits from a certain Github repository, and displays them in a List. The app is built using the recommended MVVM architecture pattern along with Room, Retrofit, RxJava, and Dagger.

## Dependencies

All additional dependencies can be found in the gradle file for the app module.

## API Level

This App Works on Min SDK level 21, and targets SDK Level 30.

## Bugs

Currently, there is a bug when loading data from Room in offline mode (no internet connectivity).

## Changing Repositories

You can change to any repository that you want by editing the following string resources (Repository must be public):
- ** github_repository_owner ** : Repository's owner username
- ** github_repository_name ** : Repository's name


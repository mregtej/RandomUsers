# Random Users

## Goals achieved 
 - Downloaded a list of non duplicated random users from http://randomuser.me/ API and stored 
   into DB.
 - Show a list of random users with this information:
    * User name and surname.
    * User email
    * User picture
    * User phone
 - Retrieved more non-duplicated users when scroll-view reaches the bottom, updated the list and 
   stored new uses into DB.
    * TODO: Improve the UI while fetching new users (retain scroll-view position and display 
      loading as a list-view item)
 - When the user clicks on the cell the user detailed information is displayed:
    * User gender.
    * User name and surname.
    * User location: street, city and state.
    * Registered date.
    * User email.
    * User picture.
 - The user information is persistent across application sessions (stored into DB).
 - Unit tests implemented for network layer, repository class, mappers and view-models 
    * TODO Database unit testing still missing 

## TODO goals not yet implemented
 - Add a button or any similar infinite scroll mechanism to retrieve more users and add them to 
   your current users list.
 - Add a button to each cell or a similar interaction to delete users. If you press that button 
   your user will not be shown anymore in your user list. Even if the user is part of a new server 
   side response.

## App Architecture

- The app has 4 diferent modules:
  * App module (core module)
  * Network module (feature module) (network service layer - Retrofit)
  * Database module (feature module) (persistent storage - Room)
  * Architecture module (library - shared resources)
    
- The app has been designed using the MVVM (Model-View-ViewModel) pattern, which allows a better
  separation of concerns by adding the viewModel as a translator/middleware between data and view
  layers. https://developer.android.com/jetpack/guide?hl=de#recommended-app-arch

- The service layer has a repository class, which is responsible of retrieving users from the
  database or from the network depending on the specific use case.

- All service layer classes (Singletons), ViewModelFactories and FragmentFactories have been 
  injected using dagger2. It simplifies the written code and provide an adaptive environment that's 
  useful for testing and other configuration changes.

- Kotlin coroutines have been used on the app. They are the recommended solution for asynchronous
  programming on Android. Some of the advantages are:

    - Lightweight: You can run many coroutines on a single thread due to support for suspension,
      which doesn't block the thread where the coroutine is running. Suspending saves memory over
      blocking while supporting many concurrent operations.
    - Fewer memory leaks: Use structured concurrency to run operations within a scope.
    - Built-in cancellation support: Cancellation is propagated automatically through the running
      coroutine hierarchy.
    - Jetpack integration: Many Jetpack libraries include extensions that provide full coroutines
      support. Some libraries also provide their own coroutine scope that you can use for structured
      concurrency.

- Some unit tests have been added to improve the quality and measure the stability of the app.

## Potential improvements

- 2 planning goals are still missing

- Improving the UI experience when new users are fetched (when scroll-view reaches the bottom)

- Unit tests for WebService (Retrofit) and DAO (Room) still missing

- Improve Repository logic (use NetworkBoundResource)

- UI animations, design improvements

- UI and Integration tests still missing (TODO use FragmentScenario and Page Pattern)

- Improve UI look & feel (usability, landscape mode, tablet designs)

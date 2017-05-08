# Babbel Kombat

Libraries and tools included:

- Support libraries
- RecyclerViews and CardViews
- [RxJava](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [Dagger 2](http://google.github.io/dagger/)
- Functional tests with [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/index.html)
- [Mockito](http://mockito.org/)
- [Kotson](https://github.com/SalomonBrys/Kotson)
- [Parceler](https://github.com/johncarl81/parceler)

## Architecture
This project follows Android architecture guidelines that are based on [MVP (Model View Presenter)](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter).


## Notes
Total duration: ~5h.
- Paper prototyping: ~15'
- Search for assets online: ~15'
- Setup project: ~15'
- Dependency Injection graph: ~20'
- Model creation: ~5'
- Base classes: ~10'
- Custom views: ~10'
- StartActivity UI: ~15-20'
- StartActivity/Presenter: ~25'
- Player UI Component: ~10'
- GameActivity UI: ~20'
- Game Mechanism: ~40'
- GameActivity: ~20'
- RankingDialog: ~20'
- PlayersDialog: ~10'
- Espresso Tests: ~20'
- UnitTests: ~15'

Decision made because of restricted time/improvements with more time:
- Kotlin instead of Java to spare some coding time
- I chose to use the "words.json" inside the project to retrieve the translations
instead of an implementation of the networking layer to get data from the URL provided.
 In that case I would have used Retrofit in combination with Dagger2 (NetworkModule)
- Portrait mode only. It would take much more time to implement the configuration changes especially
  in the GameActivity with the TranslationAnimation to retain.
- Incomplete PlayersDialog: it would have been interesting to have a form to let the players
choose their own name for the game
- better UI Tests and UnitTests
- A GameManager implementation using an RxJava Subject to update the Game properties and notify the changes
to the subscribers


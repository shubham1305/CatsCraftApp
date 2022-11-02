
# CatsCraftApp :cat: 

The aim of this app is to replicate the high level functionality of https://thecatapi.com/ and showcase an android app out of it. 
It connects with [Cat API](https://api.thecatapi.com) to give you list of all cat breeds along with their discription.
CatsCraftApp consists of 2 pieces of UI right now:
1. Home page with list of Cat Breeds
2. Detail page of the cat breed

This app is under development. :construction_worker: :hammer_and_wrench:


## Demo and Screenshots
<p align="center">
<img src="/screenshots/CatBreedList.jpg" width="320"/>
<img src="/screenshots/CatBreedDetail.jpg" width="320"/>
<img src="/preview/preview.gif" width="360"/>
</p>


## Android Development and Architecture

* The entire codebase is in [Kotlin](https://kotlinlang.org/)
* Uses Kotlin [Coroutines](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html).
* Uses MVVM Architecture by [Architecture Components](https://developer.android.com/topic/libraries/architecture/). Retrofit, ViewModel, Paging
* Uses [Hilt Android](https://developer.android.com/training/dependency-injection/hilt-android) with [Dagger](https://dagger.dev/) for dependency injection
* Uses [Flow](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/flow.html) and mutableState as a replacement over LiveData as a state-holder observable

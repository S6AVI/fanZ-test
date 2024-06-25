# FanZ Challenge

## Android Studio Version
- **Koala**

## Emulator Used for Testing
- **Pixel XL - API 28**

## App Architecture
- **MVVM (Model-View-ViewModel)** for separation of concerns
- **Feature-First** approach for organizing files in folders

## Approach
- **Fragment-based** with a single Activity
- **XML views** to build screens

## Folder Structure
### `data`
- Contains models and the repository that handles Firestore operations.

### `util`
- Holds utility files, such as extension functions.

### `fanz_test/views`
- Holds Fragments and their corresponding ViewModels, as well as other related files, organized by feature.

## Main Libraries and SDKs Used
- **Firebase Firestore**: For storing raw data
- **Firebase Storage**: For storing images
- **Firebase Remote Config**: For remote configuration of the app
- **Glide**: For loading images into views
- **Navigation Component**: For handling destinations, passing arguments, and deep links

## Details about Requirements

### Selecting a Player
- Assumed that selecting a player means clicking on a player from the lineup to see their details, rather than choosing a player to add to the roster.

### Image Rotation
- Focused on creating views to enable the screen to be scrolled, so that the image could be rotated 360 degrees.

### Firebase Integration
- Considered only fetching data from the `players` collection, without any additional conditions, as the collection only contains the required number of players. This assumption was made for simplicity, to demonstrate the ability to work with Firebase.
- In the first launch, pre-made data will be uploaded to Firestore, to be retrieved afterwards. This function of uploading will be commented out.

### Dynamic Links
- **Note**: The Dynamic Links service is deprecated, and it cannot be used for new projects. 
see docs: https://firebase.google.com/support/dynamic-links-faq?hl=en&authuser=0&_gl=1*1wf6gs8*_ga*MTU5MTk2ODQzNy4xNzE5MDcwNTY5*_ga_CW55HF8NVT*MTcxOTMxMzc5My4xNC4xLjE3MTkzMTQ4NDguNjAuMC4w

- Instead, Navigation Deep Links were considered.
- The URI that should link to the details screen is: `https://fanz_test.com/details/{id}`
    - For Ronaldo: `https://fanz_test.com/details/qGH0D6FPuR8rsx2U9J7s`
    - For Salah: `https://fanz_test.com/details/Yn1o0hdDaAgyFoKJ55Cs`

### Firebase Remote Config
- Successfully integrated and used in the RecyclerView with its manager to display players as specified.
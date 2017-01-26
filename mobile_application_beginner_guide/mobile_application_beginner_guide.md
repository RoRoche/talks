# Mobile application: the beginner guide

## Mobile constraints

- limited bandwidth
- network availability
- limited memory size and storage capacity
- processor speed
- battery life
- variable sizes of screen
- multiple sizes of screen
- interruption (phone calls for example)

## Common components of mobile applications

- UI (appropriate for mobile devices)
    - graphical resources
- share data with remote server(s) (REST)
- local storage (files, user preferences, database)
    - offline mode
- push notification
- native interaction (dialling, emailing, Bluetooth, camera, NFC, etc.)
    - mapping, location
    - social networking
    - sensors
- specific API (Drive, Facebook, Twitter, etc.)
- app components lifecycle (AppDelegate, UIViewController, Activity, Fragment, etc.)
- authentication
- cache policy
- security
- target device families (phones, tablets)
- determine the targeted OS version(s)

## Most specific

- MDM
- monetising systems
- embedded web views

## Typical pitfalls

- reusable cells in lists/grids
- database conflicts when upgrading versions
- network availability
- data not up-to-date
- UI screen not refreshed when an event occurs
- configuration changes (screen rotations for example)

## Unbreakable rules

- never block UI thread
- ever perform specific computation (REST call, database loading) on a dedicated thread
- release memory
- persist all data you need to be usable when network is unreachable
- set up a consistent synchronization mechanism when recovering network (retry policy)
- if available, ever prefer communication with native apps than re-implementing them (contacts picker, mail composer, etc.)
- follow the SOLID principles
- think POO, not procedural
- test on devices (emulators/simulators are not enough)
- testing (unit tests, integration tests, e2e tests, mutation tests)
- disable logging in production
- set up a crash reporter
- manage user permissions (Android-M, etc.)
- anticipate validation process
- manage the project as software development project (SVN/git, CI, static analytics tools, etc.)

## Relevant patterns

- MVC/MVP/MVVM
- DTO
- DAO
- Dependency injection

## Specific concepts

|                             | iOS                       | Android                   | Xamarin                           | ionic (AngularJS) |
---                           | ---                       | ---                       | ---                               | ---
|IDE                          | Xcode                     | Android Studio            | Xamarin Studio / Visual Studio    | Visual Studio Code |
|approach to app architecture | VIPER                     | MVP (with mosby)          | MVVM (with MvvmCross)?            | MVC |
|database                     | CoreData with CoreStore   | OrmLite / requery         | Linq / SQLite.NET?                | persistence.js |
|REST client                  | Alamofire                 | retrofit                  | RestSharp / Refit?                | native |
|JSON parsing                 | ObjectMapper              | Jackson / LoganSquare     | Newtonsoft.Json (Json.NET)        | native |
|dependency management        | CocoaPods                 | Gradle                    | NuGet?                             | bower |
|async                        | SwiftTask                 | RxJava / ProrityJobQueue  | Rx.NET?                           | promises |
|dependency injection         | (Swinject?)               | Dagger2                   | AutoFac?                          | native |
|event bus                    | Kugel                     | EventBus                  | MessageBus?                       | native |
|async image loading          | Kingfisher                | Picasso                   | native vs. 3rd-party library?     | native |

## Bibliography

- <http://mashable.com/2013/08/28/mobile-app-strategy/#8XtNt1HZakqV>
- <https://msdn.microsoft.com/en-us/library/ee658108.aspx>
- <https://infinum.co/the-capsized-eight/top-10-ios-swift-libraries-every-ios-developer-should-know-about>
- <https://medium.com/app-coder-io/33-ios-open-source-libraries-that-will-dominate-2017-4762cf3ce449#.omvg0okqk>
- <http://fr.slideshare.net/IshaiHachlili1/build-cross-platform-mobile-apps-for-ios-android-with-xamarin-mvvmcross>

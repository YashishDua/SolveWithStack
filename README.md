# SolveWithStack

<strong>This library will help you to reach out to best possible answer for your bug/error on stackoverflow from your Android Studio Console only.</strong>


# Including in your project

+ SolveWithStack is available in the Maven. Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

+ Start using by simply adding it as a dependency
```gradle
implementation 'com.github.yashishdua:SolveWithStack:v1.0.2'
```


# Getting Started

+ Create a class MyApplication.java extending Application
```java
pubic class MyApplication extends Application {

  @Override
  public void onCreate(){
    super.onCreate();
    
  }
}
```
+ Make sure you refer MyApplication class context.
+ Add Internet permission in Manifest.
+ Include Service in Manifest
```xml
    <uses-permission android:name="android.permission.INTERNET"/>
    <application
            android:name=".MyApplication"
            ...>
            <activity...>
	
	    <service
	    	android:name="com.yashish.library.solvewithstack.services.MyService">
	    </service>
    </application>        
```

+ To initialize SolveWithStack under your onCreate() in the MyApplication.java, call
```java
pubic class MyApplication extends Application {

  @Override
  public void onCreate(){
    super.onCreate();
    SolveWithStack.apply(getApplicationContext());
  }
}

```
### Support
If you found this library useful and want to thank me, show some :heart: and star the project!

[![GitHub stars](https://img.shields.io/github/stars/yashishdua/SolveWithStack.svg?style=social&label=Star)](https://github.com/yashishdua/SolveWithStack)


### Created & Maintained By
[Yashish Dua](https://github.com/yashishdua) 
([@duayashish](https://www.twitter.com/@duayashish))


### Credits
+ SolveWithStack uses [Retrofit](http://square.github.io/retrofit/) to connect with StackOverFlow API.
+ To create this README.md, I reffered to [Nishant's](https://github.com/nisrulz) Library, Sensey.


License
=======

    Copyright 2017 Yashish Dua

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

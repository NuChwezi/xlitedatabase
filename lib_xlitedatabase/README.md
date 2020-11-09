# xlitedatabase

A Dictionary Database Interface over SQlite, for a simpler DAL in Android apps

-----------------

Basic Usage
============


To get started, simply add the following to your root project gradle file:

     allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  And in your app's gradle file, add the following to the dependencies..
  
      dependencies {
          // your other deps...
          
          // Add XliteDatabase Library
	        implementation 'com.github.NuChwezi:xlitedatabase:TAG'
	}
  
Please check [JitPack](https://jitpack.io/#NuChwezi/xlitedatabase/) for the latest release of this library, so you **replace** `TAG` in the above snippet with the right release name. 

## Invoking XliteDatabase from Android Code


Here is a basic example of how an android activity can invoke the XLiteDatabase (XLDB) with a demonstration of the basic CRUD operations. 

[Click to view Gist](https://gist.github.com/mcnemesis/912d307a16588a34cb30281bf7e40fd8)

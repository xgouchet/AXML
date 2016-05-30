AXML
====

AXML is a library designed to parse binary Android XML files (ie : XML files compressed by the Android AAPT tool). This library was first designed to be used inside the Axel app, available on [Google Play](https://play.google.com/store/apps/details?id=fr.xgouchet.xmleditor) and [GitHub](https://github.com/xgouchet/Axel). 

[![Release](https://jitpack.io/v/xgouchet/AXML.svg)](https://jitpack.io/#xgouchet/AXML)

LINK
=====

To add this library to your build, add the following line to your project's gradle : 

    allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
	
Then you can just add the dependency like that : 

	dependencies {
		compile 'com.github.xgouchet:AXML:v1.0'
	}

USAGE
===== 

You can use the parser either as Sax or Dom. 

The following code requires an implementation of the CompressedXmlParserListener interface (which is quite similar to a SaxListener interface) : 

```java
File file = ... 
CompressedXmlParserListener listener = ...
new CompressedXmlParser().parse(new FileInputStream(file), listener);
```

The following code will return the root of a DOM tree : 

```java
File file = ... 
Document doc = new CompressedXmlParser().parseDOM(new FileInputStream(file));
```



LICENCE
=======

Copyright (C) 2012 by Xavier GOUCHET (http://xgouchet.fr, android@xgouchet.fr)
MIT Licence / Expat

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS ( XAVIER GOUCHET ) BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
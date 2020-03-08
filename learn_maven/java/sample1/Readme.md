# How to run executable Java Jar files

Here we have a sample1 project that shows how to build and execute java programs as a Jar file.

```.
├─ com
   └── vlv
       ├── demosample
       │   └── GetProps.java
       └── sample
           └── SampleGreet.java
```
Above tree structure show the java source and class objects in their package structure.
```
$ javac com/vlv/demosample/GetProps.java
```
Run the above command to compile the java code and the structure is like follows:

```
├─ com
   └── vlv
       ├── demosample
       │   ├── GetProps.class
       │   └── GetProps.java
       └── sample
           ├── SampleGreet.class
           └── SampleGreet.java
```

Now create a manifest file that would specify the Main-Class, which will make the jar an executable one.

Please refer the contents of the mainfest.txt file : [manifest.txt](https://github.com/vlvanchin/testbed/blob/master/java/sample1/manifest.txt)

Now create an executable jar file with the below command:
```
$ jar -cvfm showproperties.jar manifest.txt *
```

Here is the command to run the *showproperties.jar* 

```
$ java -jar showproperties.jar
```



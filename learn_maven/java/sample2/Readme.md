# This is a sample to show how to compile and run java programs

This is the initial sturcture of this project, where there are 3 main folders bin/, lib/ and src/. In the src folder, we have our java source code. At present, the bin folder is the place to have our compiled class objects and lib is not left empty, as we do not have any external libraies used.

```
.
├── bin
├── lib
└── src
    └── com
        └── vlv
            └── subproj
                └── Greeting.java
```

## Compilation step:

use the following command to compile the Greeting.java that is in the package com.vlv.subproj, which is in the src folder. so the -sourcepath specific the folder that contains the java source files. The -d option tells to put the compiled class in the destination folder bin.

```
$ javac -d bin -sourcepath src src/com/vlv/subproj/Greeting.java
```
the folder structure contents after the compilation is as shown below:

```
.
├── bin
│   └── com
│       └── vlv
│           └── subproj
│               └── Greeting.class
├── lib
├── Readme.md
└── src
    └── com
        └── vlv
            └── subproj
                └── Greeting.java
```

## Execution step:
In order to execute the Greeting class, we need to use the -cp (or CLASSPATH) that includes the current and bin folders for Java to execute.

```
$ java -cp .:bin com.vlv.subproj.Greeting
```



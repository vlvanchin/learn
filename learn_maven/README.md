# This is a repo to have Maven test projects

## Simple Maven commands 

### Maven command to create a simple Java application

Try the following command in console. This command will use the archetype **'generate'** to get the settings for the project.

```
$ mvn archetype:generate -DgroupId=com.my.app -DartifactId=simpleapp -Dversion=0.1-SNAPSHOT
```

This will get into interactive mode, where maven will ask few questions were you need to give input, or just press enter to take default values.

You can also use **-DinteractiveMode=false** for not bothering you. :)




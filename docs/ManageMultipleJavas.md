# Manage Multiple Java Versions 

There are countless ways to manage multiple different versions of JREs locally. We include 2 relatively simpler options here. 

# Option 1: Use jEnv
[http://www.jenv.be/](http://www.jenv.be/)

# Option 2: Use Local References
### Step 1: Install the needed versions of Java

### Step 2: Add the following code to your .bashrc
```
# Note: Change the path and version of the java runtimes according to your local setup. 

export JAVA_8_HOME=$(/usr/libexec/java_home -v1.8)
export JAVA_11_HOME=$(/usr/libexec/java_home -v11)

alias java8='export JAVA_HOME=$JAVA_8_HOME'
alias java11='export JAVA_HOME=$JAVA_11_HOME'

#default java8
export JAVA_HOME=$JAVA_8_HOME
```

### Usages
By default, every shell will start with default java runtime. It's java 8 in the example above. 
If you would like to switch to a different version of java, simple type _javaXX_ where the __XX__ is the version of java in your config. For example, type _java11_ if you want to switch to java 11 in the example above. 

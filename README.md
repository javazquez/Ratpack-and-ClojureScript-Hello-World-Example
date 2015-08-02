# Ratpack and ClojureScript Hello World Example

This is an example of using gradle to get ratpack up and running with [ClojureScript](https://github.com/clojure/clojurescript). I used ClojureScript's [Quick Start Guide](https://github.com/clojure/clojurescript/wiki/Quick-Start) as my reference for incorporating it into this gradle build.

## TLDR

you can clone the project and run
```sh
./gradlew run
```
then navigate to [http://localhost:5050/](http://localhost:5050/)

 you should see text that says "**ratpack example app**" followed by "**hello world from ClojureScript**" with the current time appended


## build.gradle
Below are the additional tasks I added to compile the ClojureScript files with Gradle

```groovy
//download the cljs jar to compile our clojure sources if it is not already in our libs directory
task downloadClj <<{
  def cljsjar = new File("libs/cljs.jar")
  if(!cljsjar.exists()){
    ant.get(src: 'https://github.com/clojure/clojurescript/releases/download/r1.7.28/cljs.jar',
    dest: 'libs',
    skipexisting: 'true')
  }
 }

//we need to compile the cljs sources using the configurations in build.clj
task compileCljs(type: Exec){
  dependsOn downloadClj
  executable "sh"
  args "-c", "java -cp libs/cljs.jar:src/main/clojurescript clojure.main build.clj"

}
//lets hook the compilation of cljs into the java compilation by running it before compileJava
compileJava.dependsOn(compileCljs)
```
## build.clj
**used by Gradle task compileCljs**
```clj  
(cljs.build.api/build "src/main/clojurescript"
{ :main 'clojurescript.core
  :asset-path "assets/js/out"
  :output-dir "src/ratpack/public/js/out"
  :output-to "src/ratpack/public/js/cljs.js"})
```
**:asset-path**  will be what is written in the js file that Ratpack will
                  serve in handlers DSL( see Ratpack.groovy )

**:output-dir** is physical location of where to put the generated files

**:output-to** will put the compiled cljs sources into this generated file





### *Pull requests welcome to make the code and build more idiomatic (Gradle|ClojureScript|Ratpack)

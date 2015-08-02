(require 'cljs.build.api)

;;;   :asset-path -> this will be what is written in the js file that Ratpack will 
;;;                  serve in handlers DSL
;;;   :output-dir -> this is physical location of files
;;;   :output-to ->  compiled cljs sources will out putted to this file
(cljs.build.api/build "src/main/clojurescript"
{ :main 'clojurescript.core
  :asset-path "assets/js/out"
  :output-dir "src/ratpack/public/js/out"
  :output-to "src/ratpack/public/js/cljs.js"})

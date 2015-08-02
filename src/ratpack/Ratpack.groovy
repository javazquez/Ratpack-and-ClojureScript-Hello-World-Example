import static ratpack.groovy.Groovy.ratpack

ratpack {
    handlers {
      get {
            render file("public/pages/index.html")
        }

        prefix("assets"){
          files{
            dir 'public'
          }
        }

    }
}

(defproject seg "0.1.0-SNAPSHOT"
  :description "chinese,uyghur segmentation"
  :url "http://github.com/xperian/seg"
  :min-lein-version "2.0.0"
  :dependencies [
                 [org.clojure/clojure "1.7.0-alpha4"]
                 [compojure "1.3.1"]
                 [ring-mock "0.1.5"]
                 [ring/ring-defaults "0.1.2"]
                 [ring "1.3.2"]
                 [ring/ring-json "0.3.1"]
                 [com.guokr/stan-cn-nlp "0.0.4"]]
  :plugins [[lein-ring "0.8.13"]
            [cider/cider-nrepl "0.9.0-SNAPSHOT"]]


  :ring {:handler seg.handler/app
         :port 4000}
  :profiles
  {:dev {:dependencies [
                        [javax.servlet/servlet-api "2.5"]
                        [com.guokr/stan-cn-nlp "0.0.4"]
                        [ring-mock "0.1.5"]]}})

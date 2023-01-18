(defproject api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [com.layerware/hugsql "0.5.3"]
                 [org.postgresql/postgresql "42.3.1"]
                 [cheshire "5.11.0"]
                 [ring/ring-json "0.5.1" :exclusions [commons-codec]]
                 [buddy/buddy-auth "3.0.323" :exclusions [commons-codec]]
                 [buddy/buddy-hashers "1.8.158" :exclusions [commons-codec]]]
  :repl-options {:init-ns api.core}
  :plugins [[lein-ring "0.12.6"]]
  :ring {:handler api.core/app
         :nrepl {:start? true :port 40283}}
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring/ring-mock "0.3.2"]]}})

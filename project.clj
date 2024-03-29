(defproject pension "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [
                  [com.oracle/ojdbc6 "11.2.0.3"]
                  [org.clojure/clojure "1.6.0"]
                  [lib-noir "0.8.1"]
                  [compojure "1.1.6"];Compojure for routing
                  [ring-server "0.3.1"];Ring for the foundational request and response plumbing
                  [selmer "0.6.4"]
                  [com.taoensso/timbre "3.1.6"]
                  [com.taoensso/tower "2.0.2"]
                  [markdown-clj "0.9.41"]
                  [environ "0.4.0"];
                  [korma "0.3.0-RC6"]
                  ]

  :repl-options {:init-ns pension.repl}
  :plugins [[lein-ring "0.8.10"]
            [lein-environ "0.4.0"]]
  :ring {:handler pension.handler/app
         :init    pension.handler/init
         :destroy pension.handler/destroy}
  :profiles
  {:uberjar {:aot :all}
   :production {:ring {:open-browser? false
                       :stacktraces?  false
                       :auto-reload?  false}}
   :dev {:dependencies [[ring-mock "0.1.5"]
                        [ring/ring-devel "1.2.2"]]
         :env {:dev true}}}
  :repositories [
                  ["java.net" "http://download.java.net/maven/2"]
                  ["nexus" "https://code.lds.org/nexus/content/groups/main-repo"]
                  ["sonatype" {:url "http://oss.sonatype.org/content/repositories/releases"
                               ;; If a repository contains releases only setting
                               ;; :snapshots to false will speed up dependencies.
                               :snapshots false
                               ;; Disable signing releases deployed to this repo.
                               ;; (Not recommended.)
                               :sign-releases false
                               ;; You can also set the policies for how to handle
                               ;; :checksum failures to :fail, :warn, or :ignore.
                               :checksum :fail
                               ;; How often should this repository be checked for
                               ;; snapshot updates? (:daily, :always, or :never)
                               :update :always
                               ;; You can also apply them to releases only:
                               :releases {:checksum :fail :update :always}}]
                  ;; Repositories named "snapshots" and "releases" automatically
                  ;; have their :snapshots and :releases disabled as appropriate.
                  ;; Credentials for repositories should *not* be stored
                  ;; in project.clj but in ~/.lein/credentials.clj.gpg instead,
                  ;; see `lein help deploying` under "Authentication".
                  ]
  :min-lein-version "2.0.0")
(ns pension.routes.home
  (:use compojure.core)
  (:require [pension.views.layout :as layout]

            [pension.util :as util]))

(defn home-page [] (layout/render "home.html" {:content (util/md->html "/md/docs.md")}))
(defn about-page [] (layout/render "about.html"))
(defn login-page [] (layout/render "login.html"))
(defn index-page [] (layout/render "index.html"))

(defroutes home-routes
  (GET "/home" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/login" [] (login-page))
  (GET "/" [] (index-page))
  )

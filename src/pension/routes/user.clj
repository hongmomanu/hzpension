(ns pension.routes.user
  (:use compojure.core)
  (:require [pension.controller.user :as user]
            [noir.response :as resp]
            )

  )


(defroutes user-routes

  (GET "/users" []
    (user/userlist)
    )
  )



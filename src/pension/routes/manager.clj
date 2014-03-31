(ns pension.routes.manager
  (:use compojure.core)
  (:require [pension.controller.manager :as manager]
            [noir.response :as resp]
            )

  )


(defroutes manager-routes

  (POST "/userlogin" [loginname password] (manager/login loginname password))
  (GET "/logout" [] (manager/logoutCurrentUser))
  (GET "/businessmenu" [id] (manager/getMenu id))
  (POST "/businessmenu" [id] (manager/getMenu id))
  (GET "/managers" [] (manager/managerlist))
  (GET "/test" [] (manager/test))
  (GET "/test2" [] (manager/test))
  ;(GET "/" [] (manager/getCurrentUser))

)



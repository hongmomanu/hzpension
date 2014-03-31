(ns pension.controller.user
  (:use compojure.core)
  (:require [pension.models.db :as db]
            [noir.response :as resp]
            )
  )



(defn userlist []
  (resp/json (db/user-list))
  ;(resp/json {:msg "用户名密码错误"})
  )

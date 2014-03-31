(ns pension.controller.manager
  (:use compojure.core)
  (:require [pension.models.manager :as m]
            [noir.response :as resp]
            [noir.session :as session]
            )
  )

(defn log
  [msg]
  (print "\n*********************************************\n")
  (print msg)
  (print "\n*********************************************\n"))

(defn set-user [id]
  (session/put! :user id)
  (session/get :user))

(defn remove-user []
  (session/remove! :user)
  (session/get :user))

(defn set-user-if-nil [id]
  (session/get :user id))


(defn clear-session []
  (session/clear!))


(defn managerlist []
  (resp/json (m/common-function-list))
  ;(resp/json {:msg "用户名密码错误"})
)

(defn login [loginname passwd]
  (log (session/get :user))
  (let [results (m/has-user  loginname passwd)]
    ( if (> (count results) 0)

        (do
          (set-user (first results))
          (first results))
      (resp/json {:success false :msg "用户名密码错误" :loginname loginname})
      )
    )
  )
(defn getMenu [id]

  (log
    (let [{regionid :regionid userid :userid} (session/get :user)]
      (str regionid "\t" userid)))
  (if (nil? (session/get :user))
    (log "用户未登录")
    (let [{userid :userid} (session/get :user)]
      (resp/json
        (if
          (nil? id)
          (m/functionTree "businessmenu" userid)
          (m/functionTree id userid)))
      )
    )
  )



(defn hello [name]
  (str "hello, " name))
(defn nine-times "九九表" []
  (for [i (range 1 10)
        j (range 1 (+ i 1))]

    (if (= i j) (str j '* i '= (* i j) (char 10))
      (str j '* i '= (* i j))
      )
    ))

(defn test []
  (resp/json
    (do
      (print "weipan*************************")
      (hello "魏攀")))
  ;(resp/yaml (nine-times));
  )
(defn getCurrentUser
  []
  (log (session/get :user))
  #_(resp/json
    (session/get :user)))
(defn logoutCurrentUser
  []
  (clear-session))
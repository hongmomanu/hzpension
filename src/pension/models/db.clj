(ns pension.models.db
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [pension.models.schema :as schema]))

(defdb db schema/db-oracle)

(defentity users)

(defn create-user [user]
  (insert users
          (values user)))

(defn update-user [id first-name last-name email]
  (update users
  (set-fields {:first_name first-name
               :last_name last-name
               :email email})
  (where {:id id})))

(defn user-list []
  (select users
    (fields :username :telnum :password :admin)

    )
  )
(defn get-user [id]
  (first (select users
                 (where {:id id})
                 (limit 1))))

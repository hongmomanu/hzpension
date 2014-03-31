(ns pension.models.schema
  (:require [clojure.java.jdbc :as sql]
            [noir.io :as io]))


(declare create-dutymission-table create-dutymissionhistory-table
  create-servers-table create-systemwatchlog-table create-dutylog-table create-stations-table)



(def db-oracle  {:classname "oracle.jdbc.OracleDriver"
                 :subprotocol "oracle"
                 :subname "thin:@192.168.2.142:1521:orcl"
                 :user "PENSION_SYSTEM"
                 :password "hvit"
                 :naming {:keys clojure.string/lower-case :fields clojure.string/upper-case}})

(defn initialized?
  "checks to see if the database schema is present"
  []
  ;;(.exists (new java.io.File (str (io/resource-path) db-store ".h2.db")))
  ;;(create-stations-table)
  ;(.exists (new java.io.File (str datapath db-store-sqlite "")))
  )


(defn create-users-table
  []
  (sql/with-connection
    (sql/create-table
      :users
      [:id "integer primary key autoincrement"]  ;;
      [:username "varchar(30)"]
      [:displayname "varchar(30)"]
      [:telnum "varchar(30)"]
      [:departments "varchar(30)"]
      [:email "varchar(30)"]
      [:admin "BOOLEAN DEFAULT 0"]
      [:last_login "DATE"]
      [:time "DATE"]
      [:is_active "BOOLEAN DEFAULT 0"]
      [:password "varchar(100)"])))
(defn create-xt_function-table
  []
  (sql/with-connection
    (sql/create-table
      :xt_function
      [:FUNCTIONID   "VARCHAR2(32) not null"]
      [:LOCATION     "VARCHAR2(256)"]
      [:TITLE        "VARCHAR2(100)"]
      [:PARENT       "VARCHAR2(32)"]
      [:ORDERNO      "NUMBER(4) not null"]
      [:NODETYPE     "VARCHAR2(1) not null"]
      [:TYPE         "VARCHAR2(1) not null"]
      [:DESCRIPTION  "VARCHAR2(256) not null"]
      [:LOG          "VARCHAR2(1)"]
      [:DEVELOPER    "VARCHAR2(50)"]
      [:ACTIVE       "VARCHAR2(3)"]
      [:FUNCTIONDESC "VARCHAR2(500)"]
      [:AUFLAG       "CHAR(1)"]
      [:RBFLAG       "CHAR(1) default '1' not null"]
      [:PARAM1       "VARCHAR2(200)"]
      [:PARAM2       "VARCHAR2(200)"]
      [:CREATEDATE   "DATE"]
      [:OWNER        "VARCHAR2(32)"])))
(defn create-xt_user-table
  []
  (sql/with-connection
    (sql/create-table
      :xt_user
      [:USERID      "VARCHAR2(32) not null"]
      [:PASSWD      "VARCHAR2(64)"]
      [:LOGINNAME   "VARCHAR2(50)"]
      [:DEPT        "VARCHAR2(32)"]
      [:DESCRIPTION "VARCHAR2(500)"]
      [:USEFUL      "VARCHAR2(1) default 1 not null"]
      [:REGIONID    "VARCHAR2(32)"]
      [:USERNAME    "VARCHAR2(32)"]
      [:OWNER       "VARCHAR2(32)"]
      [:RATE        "VARCHAR2(8)"]
      [:OTHERINFO   "VARCHAR2(2000)"]
      [:CREATEDATE  "DATE"])))

(defn create-tables
  "creates the database tables used by the application"
  []
  (create-users-table)
  (create-xt_function-table)
  (create-xt_user-table)

  )

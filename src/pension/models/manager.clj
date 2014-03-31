(ns pension.models.manager
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [pension.models.schema :as schema]))

(defdb db schema/db-oracle)
(defentity xt_function)
(defentity xt_user)

(defn common-function-list []
  (select xt_function
    )
  )
(defn has-user [loginname passwd]
  (select xt_user
    ))
(defn functionTree [node userid]
  (print (str node "\t," userid))
  (if (nil? userid)
    (exec-raw ["SELECT t.*,t.functionid id,t.title text,t.location value,
  decode(nodetype,'1','false','true') leaf,
  decode(nodetype,'1','closed','open') state
   FROM xt_function t WHERE t.parent =?" [node]] :results)
    (exec-raw ["SELECT t.*,t.functionid id,t.title text,t.location value,
  decode(nodetype,'1','false','true') leaf,
  decode(nodetype,'1','closed','open') state
   FROM xt_function t WHERE t.parent =?
   and t.nodetype <> '2' and t.functionid in (select rf.functionid from xt_roleuser ru,xt_rolefunc rf where ru.userid=?
    and ru.roleid=rf.roleid)" [node userid]] :results))
  )


(ns api.db
  (:require [hugsql.core :as hugsql :refer [def-db-fns]]))

;; jbdc db connection obj
(def dbc {:dbtype "postgresql"
          :dbname (or (System/getenv "DB_NAME") "postgres")
          ;; default to default postgres credentials 
          :host (or (System/getenv "DB_URL") "localhost")
          :port 5432
          :user (or (System/getenv "DB_USER") "postgres")
          :password (or (System/getenv "DB_PASS") "Postgres1234")})

(dorun (map #(->> % (format "sql/%s.sql") def-db-fns)
            ["users"]))

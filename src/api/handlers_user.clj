(ns api.handlers-user (:require [api.db :as db :refer [dbc]]
                                [api.util.webdata :refer [unauthorized]]
                                [buddy.hashers :refer [encrypt]]
                                [clojure.edn :as edn]
                                [compojure.core :refer [defroutes GET POST]]
                                [ring.util.response :refer [response]]))

(defn user-from [uid-str] (-> uid-str
                              edn/read-string
                              (as-> id (db/user-by-id dbc {:id id}))))

(defroutes user-routes
  (GET ["/user/:uid" :uid #"[0-9]+"] [uid]
    (-> uid
        user-from
        response))

  (POST ["/user/:uid/change-email" :uid #"[0-9]+"] [uid]
    (-> uid
        user-from
        (select-keys [:email])
        (as-> email (do (db/update-user-email dbc {:email email})
                        (response {:email email})))))

  ;; currently untested
  (POST ["/user/:uid/change-password" :uid #"[0-9]+"] [uid pw]
    (-> uid
        user-from
        (select-keys [:pw_hash])
        (as-> pw-hash (let [hash-new (encrypt pw)]
                        (if (= hash-new pw-hash) 
                          (do (db/update-user-pw dbc {:pw_hash hash-new})
                              (response "changed"))
                          (unauthorized)))))))

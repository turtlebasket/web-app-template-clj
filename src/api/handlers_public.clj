(ns api.handlers-public (:require [api.db :as db :refer [dbc]]
                                  [buddy.hashers :refer [encrypt]]
                                  [compojure.core :refer [defroutes GET POST]]
                                  [ring.util.response :refer [response]]))

(def mxbean (java.lang.management.ManagementFactory/getRuntimeMXBean))

(defroutes public-routes
  (GET "/" [] "<h1>Colinear API</h1>")

  (GET "/stats" [] (response {:users (get (db/count-users dbc) :count)
                              :uptime (-> mxbean .getUptime)}))

  (POST "/signup" request
    (let [result (-> request
                     (get :body)
                     (select-keys [:first_name :last_name :email :pw])
                     (as-> x (assoc x :pw_hash (encrypt (:pw x))))
                     (dissoc :pw))]
      (db/add-user dbc result)
      (response (select-keys result [:email])))))

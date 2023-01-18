(ns api.core (:require [api.handlers-public :refer [public-routes]]
                       [api.handlers-user :refer [user-routes]]
                       [api.util.webdata :refer [not-found]]
                       [compojure.core :refer [routes]]
                       [compojure.route :as route]
                       [ring.middleware.defaults :refer [api-defaults
                                                         wrap-defaults]]
                       [ring.middleware.json :refer [wrap-json-body
                                                     wrap-json-response]]
                       [ring.middleware.params :refer [wrap-params]]))

(def app
  (-> (routes (-> public-routes
                  (wrap-defaults api-defaults))
              user-routes
              (route/not-found not-found))
      wrap-json-response
      (wrap-json-body {:keywords? true})))

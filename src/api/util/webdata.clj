(ns api.util.webdata (:require [cheshire.core]
                               [ring.util.response :as response :refer [response content-type status]]
                               [ring.util.request :refer [body-string]]))

;; response

(def unauthorized (-> "Unauthorized"
                      response
                      (status 401)))

(def not-found (response/not-found "Not Found"))

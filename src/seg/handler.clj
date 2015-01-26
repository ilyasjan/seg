(ns seg.handler
  (:use [ring.middleware.json :only [wrap-json-response]]
        [ring.util.response :only [response redirect content-type]])
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure.string :as str]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]])
  (:import com.guokr.nlp.seg.__SEG__))

(defn cn-seg [text]
  "Chinese segmentation. A lot work could have been done."
  (.segment com.guokr.nlp.seg.__SEG__/INSTANCE text))

(defn chinese? [text ]
  "This will check if it is Chinese"
  (some #(or (<=  0x4E00 (int %) 0x9FFF)) text))

(defroutes app-routes
  (GET "/" [] "you can post data to cn-seg .")
  (GET "/test" [] "awesome")
  (GET "/cn" [] "please use post")
  (POST "/cn" [] (fn [req]
                      (let [src (get-in req [:params :src])]
                        (println req)
                        (if (or  (str/blank? src) (not (chinese? src)))
                          (response  {:text ""})
                          (response {:text (cn-seg src)})
                          ))))
  (ANY "*" []
       (redirect "/")))

(def app
  (-> app-routes
      wrap-json-response
      (wrap-defaults (assoc-in  site-defaults [:security :anti-forgery] false))))

(ns flyer.storage
  "includes functions for storing window information"
  (:require [flyer.utils :as utils]))


(def storage (utils/get-main-parent))

(def window-list-key "flyer_WindowReferences")

(defn get-window-refs []
  (aget storage window-list-key))
  
(defn insert-window-ref! [window]
  (aset storage window-list-key
        (conj (get-window-refs) window)))

(defn remove-window-ref! [window]
  (aset storage window-list-key
        (disj (get-window-refs) window)))

;;if i'm the parent window, intiialize ref variable
(when (= (utils/get-main-parent) js/window)
  (.log js/console "Flyer is initializing External Window Reference")
  (aset storage window-list-key #{}))

(ns hello-clojure.namespace-jframe
  (:import (java.text NumberFormat) (javax.swing JFrame JLabel)))

(defn -main []
  ; Display a window
  (doto (JFrame. "Hello")
    (.add (JLabel. "Hello, World!"))
    (.pack)
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setVisible true)))

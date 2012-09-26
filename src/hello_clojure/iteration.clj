(dotimes [i 3]
  (print "yay" i "; ")) ; -> yay 0 ; yay 1 ; yay 2 ;

(dotimes [i 3]
  (print "yay" (inc i) "; ")) ; -> yay 1 ; yay 2 ; yay 3 ;
  ; NOTE: how the i is rebound on each iteration


;; here's an example with a WHILE loop
(defn my-fn [ms]
  (println "entered my-fn")
  (Thread/sleep ms)
  (println "leaving my-fn"))

(let [thread (Thread. #(my-fn 1))]
  (.start thread)
  (println "started thread")
  (while (.isAlive thread)
    (print ".")
    ;(flush)
    )
  (println "thread stopped"))

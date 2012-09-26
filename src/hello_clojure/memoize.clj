


(defn f
  [x]
  (if (<= x 1)
    1
    (* x (f (- x 1)))))

(def memo-f (memoize f))

(println "priming call")
(time (f 20))

(println "without memoization")
; Note the use of an underscore for the binding that isn't used
(dotimes [_ 3] (time (f 20)))

(println "with memoization")
(dotimes [_ 3] (time (memo-f 20)))

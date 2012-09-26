(defn multiply
  ; The "&" denotes an optional number of args.
  ; These optional args are passed in as an ArraySeq.
  [x & more-args]
  (reduce * x more-args))

(multiply 3 4 5 6)



(defn multi-args-for-no-reason
  "pointless function"
  [x & more-args]
  (println (str "x is: " x ", more-args is: " more-args))
  (println (str "type of more-args is: " (type more-args)))
  )

(multi-args-for-no-reason 3 4 5 6)
; -> returns x is: 3, more-args is: (4 5 6)
;            type of more-args is: class clojure.lang.ArraySeq



(defn power [base & exponents]
  ; using java.lang.Math static method pow
  (reduce #(Math/pow %1 %2) base exponents))

(power 2 3) ; -> returns 2 ^ 3 = 8
(power 2 3 2) ; -> returns 2 ^ 3 ^ 2 = 64



(defn more-power
  [& base-and-exponents]
  (reduce #(Math/pow %1 %2) base-and-exponents)
  )

(more-power 2 3 4)

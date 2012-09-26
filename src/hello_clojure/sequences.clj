; Sequences are logical views of collections. Many things can be treated as sequences. These include Java collections, Clojure-specific collections, strings, streams, directory structures and XML trees.

(def x (map #(println %) [1 2 3]))         ; ->

(def y (doall (map #(println %) [1 2 3]))) ; -> doall forces side effects to be evaluated
                                           ; -> similarly with dorun, doseq



; Lazy sequences make it possible to create infinite sequences since all the items don't need to be evaluated. For example:
(defn f
  "square the argument and divide by 2"
  [x]
  (println "calculating f of" x)
  (/ (* x x) 2.0))

; Create an infinite sequence of results from the function f
; for the values 0 through infinity.
; Note that the head of this sequence is being held in the binding "f-seq".
; This will cause the values of all evaluated items to be cached.
(def f-seq (map f (iterate inc 0)))

; Force evaluation of the first item in the infinite sequence, (f 0).
(println "first is" (first f-seq)) ; -> 0.0

; Force evaluation of the first three items in the infinite sequence.
; Since the (f 0) has already been evaluated,
; only (f 1) and (f 2) will be evaluated.
(doall (take 3 f-seq))

(println (nth f-seq 2)) ; uses cached result -> 2.0

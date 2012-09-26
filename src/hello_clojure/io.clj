
; By default System.out is assign to *out*
; But you can assign it some other value,
; for example a FileWriter below:
(binding [*out* (java.io.FileWriter. "my.log")]
  (println "This goes to the file my.log")
  (flush))

; pr and prn are useful for serializing Clojure data:
(let [obj1 "foo"
      obj2 {:letter \a :number (Math/PI)}] ; a map
  (println "Output from print:")
  (print obj1 obj2)

  (println "Output from println:")
  (println obj1 obj2)

  (println "Output from pr:")
  (pr obj1 obj2)

  (println "Output from prn:")
  (prn obj1 obj2))


;;; READ from file
(use '[clojure.java.io :only (reader)])

(defn print-if-contains [line word]
  (when (.contains line word) (println line)))

(let [file "story.txt"
      word "fur"]

  ; with-open will close the reader after
  ; evaluating all the expressions in its body.
  (with-open [rdr (reader file)]
    (doseq [line (line-seq rdr)] (print-if-contains line word))))

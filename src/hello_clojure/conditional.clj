; If more than one expression is needed for the then or else part, use the do special form to wrap them in a single expression. For example:
(import '(java.util Calendar GregorianCalendar))
(let [gc (GregorianCalendar.)
      day-of-week (.get gc Calendar/DAY_OF_WEEK)
      is-weekend (or (= day-of-week Calendar/SATURDAY) (= day-of-week Calendar/SUNDAY))]
  (if is-weekend
    (println "play")
    (do (println "work")
        (println "sleep"))))

; The when and when-not macros provide alternatives to if when only one branch is needed. Any number of body expressions can be supplied without wrapping them in a do. For example:
(def is-weekend false)
(when is-weekend (println "play"))
(when-not is-weekend (println "work") (println "sleep"))


; The if-let macro binds a value to a single binding and chooses an expression to evaluate based on whether the value is logically true or false (explained in the "Predicates" section). The following code prints the name of the first person waiting in line or prints "no waiting" if the line is empty.
(defn process-next [waiting-line]
  (if-let [name (first waiting-line)]
    (println name "is next")
    (println "no waiting")))

(process-next '("Jeremy" "Amanda" "Tami")) ; -> Jeremy is next
(process-next '()) ; -> no waiting


; condp is like a case statement, but it takes 2 args:
; 1) a 2-param predicate (often = or instance?)
; 2) an expression to act as the second argument
; It can take any number of these pairs.
(print "Enter a number: ") (flush) ; stays in a buffer otherwise
(let [reader (java.io.BufferedReader. *in*) ; stdin
      line (.readLine reader)
      value (try
              (Integer/parseInt line)
              (catch NumberFormatException e line))] ; use string value if not integer
  (println
    (condp = value
      1 "one"         ; so here, the condp will eval (= 1 value)
      2 "two"
      3 "three"
      (str "unexpected value, \"" value \")))
  (println
    (condp instance? value
      Number (* value 2)
      String (* (count value) 2))))


; The cond macro takes any number of predicate/result expression pairs. It evaluates the predicates in order until one evaluates to true and then returns the corresponding result. If none evaluate to true then an IllegalArgumentException is thrown. Often the predicate in the last pair is simply true to handle all remaining cases.
(print "\n\nEnter water temperature in Celsius: ") (flush)
(let [reader (java.io.BufferedReader. *in*)
      line (.readLine reader)
      temperature (try
        (Float/parseFloat line)
        (catch NumberFormatException e line))] ; use string value if not float
  (println
    (cond
     (instance? String temperature) "invalid temperature"
      (<= temperature 0) "freezing"
      (>= temperature 100) "boiling"
      true "neither")))

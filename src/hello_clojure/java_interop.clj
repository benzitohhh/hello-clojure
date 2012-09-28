(import
 '(java.util Calendar GregorianCalendar)
 '(javax.swing JFrame JLabel))

(. java.util.Calendar APRIL) ; -> 3
(. Calendar APRIL) ; -> 3 (works if Calendar was imported)

(. Math pow 2 4) ; -> 16
(Math/pow 2 4)   ; -> 16

;:: To create an instance
(def calendar (new GregorianCalendar 2008 Calendar/APRIL 16)) ; April 16th 2008
(def caldenar (GregorianCalendar. 2008 Calendar/APRIL 16))    ; April 16th 2008

;;; To call a method
(. calendar add Calendar/MONTH 2)
(. calendar get Calendar/MONTH)  ; -> 5

(.add calendar Calendar/MONTH 2)
(.get calendar Calendar/MONTH)   ; -> 7

; The option in the examples above where the method name appears first is generally preferred. The option where the object appears first is easier to use inside macro definitions because syntax quoting can be used instead of string concatenation. This statement will make more sense after reading the "Macros" section ahead.

(. (. calendar getTimeZone) getDisplayName) ; the long way
(.. calendar getTimeZone getDisplayName)    ; using the .. macro

; The doto macro is used to invoke many methods on the same object.
(doto calendar
  (.set Calendar/YEAR 1981)
  (.set Calendar/MONTH Calendar/AUGUST)
  (.set Calendar/DATE 1))
(def formatter (java.text.DateFormat/getDateInstance))
(.format formatter (.getTime calendar)) ; -> "Aug 1, 1981"

; The memfn macro expands to code that allows a Java method to be treated as a first class function.
; When using memfn to invoke Java methods that take arguments, a name for each argument must be specified.
(println (map #(.substring %1 %2)
              ["Moe" "Larry" "Curly"] [1 2 3])) ; -> (oe rry ly)
(println (map (memfn substring beginIndex)
              ["Moe" "Larry" "Curly"] [1 2 3])) ; -> the same


; All Clojure functions implement both the java.lang.Runnable interface and the java.util.concurrent.Callable interface. This makes it easy to execute them in new Java threads. For example:
(defn delayed-print [ms text]
  (Thread/sleep ms)
  (println text))

; Pass an anonymous function that invokes delayed-print
; to the Thread constructor so the delayed-print function
; executes inside the Thread instead of
; while the Thread object is being created.
(.start (Thread. #(delayed-print 3000 ", World!"))) ; prints 2nd
(print "Hello") ; prints 1st
; output is "Hello, World!"




; All exceptions thrown by Clojure code are runtime exceptions. Java methods invoked from Clojure code can still throw checked exceptions. The try, catch, finally and throw special forms provide functionality similar to their Java counterparts. For example:

(defn collection? [obj]
  (println "obj is a" (class obj))
  ; Clojure collections implement clojure.lang.IPersistentCollection.
  (or (coll? obj) ; Clojure collection?
      (instance? java.util.Collection obj))) ; Java collection?

(defn average [coll]
  (when-not (collection? coll)
    (throw (IllegalArgumentException. "expected a collection")))
  (when (empty? coll)
    (throw (IllegalArgumentException. "collection is empty")))
  ; Apply the + function to all the items in coll,
  ; then divide by the number of items in it.
  (let [sum (apply + coll)]
    (/ sum (count coll))))

(try
  (println "list average =" (average '(2 3))) ; result is a clojure.lang.Ratio object
  (println "vector average =" (average [2 3])) ; same
  (println "set average =" (average #{2 3})) ; same
  (let [al (java.util.ArrayList.)]
    (doto al (.add 2) (.add 3))
    (println "ArrayList average =" (average al))) ; same
  (println "string average =" (average "1 2 3 4")) ; illegal argument
  (catch IllegalArgumentException e
    (println e)
    (.printStackTrace e) ; if a stack trace is desired
  )
  (finally
    (println "in finally")))

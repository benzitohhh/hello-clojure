; The for and doseq macros perform list comprehension. They support iterating through multiple collections (rightmost collection fastest) and optional filtering using :when and :while expressions. The for macro takes a single expression body and returns a lazy sequence of the results. The doseq macro takes a body containing any number of expressions, executes them for their side effects, and returns nil.

(def cols "ABCD")
(def rows (range 1 4)) ; purposely larger than needed to demonstrate :while

; Note how the dorun function, described later in the "Sequences" section, is used to force evaluation of the lazy sequence returned by the for macro.
(println "for demo")
(dorun
 (for [col cols :when (not= col \B)
       row rows :while (< row 3)]
   (println (str col row))))

(println "\ndoseq demo")
(doseq [col cols :when (not= col \B)
        row rows :while (< row 3)]
  (println (str col row)))


; Recursion

; Recursion occurs when a function invokes itself either directly or indirectly through another function that it calls. Common ways in which recursion is terminated include checking for a collection of items to become empty or checking for a number to reach a specific value such as zero. The former case is often implemented by successively using the next function to process all but the first item. The latter case is often implemented by decrementing a number with the dec function.

; Recursive calls can result in running out of memory if the call stack becomes too deep. Some programming languages address this by supporting "tail call optimization" (TCO). Java doesn't currently support TCO and neither does Clojure. One way to avoid this issue in Clojure is to use the loop and recur special forms. Another way is to use the trampoline function.

; The loop/recur idiom turns what looks like a recursive call into a loop that doesn't consume stack space. The loop special form is like the let special form in that they both establish local bindings, but it also establishes a recursion point that is the target of calls to recur. The bindings specified by loop provide initial values for the local bindings. Calls to recur cause control to return to the loop and assign new values to its local bindings. The number of arguments passed to recur must match the number of bindings in the loop. Also, recur can only appear as the last call in the loop.
(defn factorial-self-calling [n]
  "explicit self-calling recursion - this is discouraged!"
  (if (zero? n)
    1
    (* n (factorial-self-calling (- n 1)))))
(factorial-self-calling 5)

; n= 5 , so call f(4)
;   n= 4 , so call f(3)
;     n= 3 , so call f(2)
;       n= 2 , so call f(1)
;         n= 1 , so call f(0)
;           n= 0 , return 1
;         n= 1 , return 1 * 1
;       n= 2 , return 2 * (1*1)
;     n= 3 , return 3 * (2*1*1)
;   n= 4 , return 4 * (3*2*1*1)
; n= 5 , return 5 * (4*3*2*1*1)


; Uing the loop/recur idiom turns what looks like a recursive call into a loop, avoiding unnecessary stack memory consumption

(defn factorial-1 [n]
  "computes factorial in a way that doesn't consume stack space"
  (loop [i n acc 1]
    (if (zero? i)
      acc
      (recur (dec i) (* acc i)))))
(factorial-1 5)

; this is the essence of the construct... (with n=5 hardcoded, and some debug)
(loop [i 5 acc 1]
    (if (zero? i)
      (do (println "i=" i ", acc=" acc) acc)
      (do (println "i=" i ", acc=" acc) (recur (dec i) (* acc i)))))

; i= 5 , acc= 1
; i= 4 , acc= 5
; i= 3 , acc= 20
; i= 2 , acc= 60
; i= 1 , acc= 120
; i= 0 , acc= 120


; A more functional, although in this case slightly less efficient, way is to use "reduce":
(defn factorial-2 [n]
  "also computes factorial in a way that doesn't consume stack space,
   alhough the range call yields a LazySequence of length n-1"
  (reduce * (range 2 (inc n))))
(factorial-2 5)

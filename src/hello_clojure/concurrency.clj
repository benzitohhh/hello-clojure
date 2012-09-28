(defn- polynomial
  "computes the value of a polynomial
   with the given coefficients for a given value x"
  [coefs x]
  ; For example, if coes contains 3 values then exponents is (2 1 0).
  (let [exponents (reverse (range (count coefs)))]
    ; Multiply each coefficient by x raised to the corresponding exponent
    ; and sum those results.
    ; coefs go into %1 and exponents go into %2
    (apply + (map #(* %1 (Math/pow x %2)) coefs exponents))))

(defn- derivative
  "computes the value of the derivative of a polynomial
   with the given coefficients for a given value x"
  [coefs x]
  (let [exponents (reverse (range (count coefs)))
        derivative-coefs (map #(* %1 %2) (butlast coefs) exponents)]
    (polynomial derivative-coefs x)))

(def f-prime
  ; (f-prime x) -> evaluates derivate of 2x^2 + x + 3
  (partial derivative [2 1 3]))





; To demonstrate using a future, a println was added to the derivative  function described in polynomial.clj . It helps identify when the function is eecuted. Note the order of the output from the code below.

(println "creating future")
(def my-future (future (f-prime 2))) ; f-prime is called in another thread
(println "created future")
(println "result is" @my-future)
(shutdown-agents)

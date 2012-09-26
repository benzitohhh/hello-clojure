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

(def f (partial polynomial [3 2 7 6]))
(f 2)
; For example...
; Suppose we have 3x^3 + 2x^2 + 7x + 6
; We get coefs [3 2 7 6] and  exponents [3 2 1 0]
; The map call yields [3x^3 2x^2 7x 6]
; The apply call just adds them


(defn- derivative
  "computes the value of the derivative of a polynomial
   with the given coefficients for a given value x"
  [coefs x]
  ; The coefficients of the derivative function are obtained by
  ; multiplying all but the last coefficient by its corresponding exponent.
  ; The xtra exponent will be ignored.
  (let [exponents (reverse (range (count coefs)))
        derivative-coefs (map #(* %1 %2) (butlast coefs) exponents)]
    (polynomial derivative-coefs x)))

(def f-prime (partial derivative [2 1 3]))
(f-prime 2)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; But wait! We can do this even more elegantly.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- polynomial-francesco
  "computes the value of a polynomial
   with the given coefficients for a given value x"
  [coefs x]
  (reduce #(+ (* x %1) %2) coefs))

; For example...
; Suppose we have 3x^3 + 2x^2 + 7x + 6

(def f (partial polynomial-francesco [3 2 7 6]))
(f 2)

; Looking at each iteration of the above reduce
; 3x + 2                       [3 2]
; 3x^2 + 2x + 7                [res 7]
; 3x^3 + 2x^2 + 7x + 6         [res   6]

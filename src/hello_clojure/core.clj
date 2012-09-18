(ns hello-clojure.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn factorial
  "calculate the factorial of a number"
  [x]
  ((if (<= x 1)
     1
     (* x (factorial (- x 1))))))

(require 'hello-clojure.core)

(hello-clojure.core/foo 777)

(factorial 3)

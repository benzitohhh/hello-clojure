(ns hello-clojure.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(require 'hello-clojure.core)

(hello-clojure.core/foo 3)

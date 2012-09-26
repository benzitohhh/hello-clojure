(ns hello-clojure.temp)

(def vowel? (set "aeiou"))

(defn pig-latin [word]
  ; word is expected to be a string (treated as a sequence of chars)
  (let [first-letter (first word)]      ; assigns a local binding
    (if (vowel? first-letter)
      (str word "ay")                   ; then part of if
      (str (subs word 1) first-letter "ay")))) ; else part of if

(pig-latin "orange")

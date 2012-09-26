
(defn parting
  "returns a String parting in a given language"
  ([] (parting "World"))
  ([name] (parting name "en"))
  ([name language]
     ; condp is similar to a case statement in other languages
     (condp = language
       "en" (str "Goodbye, " name)
       "es" (str "Adios, " name)
       (throw (IllegalArgumentException.
               (str "unsupported language " language))))))

(parting "Mark")
(parting "Mark" "en")
(parting "Mark" "es")
(parting "Mark" "sw")

(ns wiki-translate
    (:require [clj-http.client :as h])
)

(defn get-url
    ([lg term] (str "http://" lg ".wikipedia.org/wiki/" term))
)

(defn fetch-url
    ([url] (h/string (h/http-agent url)))
)

(defn get-translations
    ([cnt]  (apply sorted-map (flatten (for  [s (re-seq #"(?i)interwiki-([^\"]+).*wiki\/([^\"]+)\".*/a>" cnt)] [(s 1) (s 2)])))))

(defn translate
    [term src-lg tgt-lg] (
        (def translations (get-translations (fetch-url (get-url src-lg term)))  )
        (flush)
        (if (contains?  translations tgt-lg) (get translations tgt-lg) "<NOT FOUND>")
    )
)

(println (translate "Shark" "en" "fr"))

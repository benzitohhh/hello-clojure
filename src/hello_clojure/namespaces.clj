; The "user" namespace provides access to all the symbols in the clojure.core namespace.
; The same is true of any namespace that is made the default through use of the ns macro.

; In order to access items that are not in the default namespace they must be namespace-qualified. This is done by preceding a name with a namespace name and a slash. For example, the clojure.string library defines the join function. It creates a string by concatenating a given separator string between the string representation of all the items in a sequence. The namespace-qualified name of this function is clojure.string/join.

; The require function loads Clojure libraries. It takes one or more quoted namespace names. For example:

(require 'clojure.xml)

; This merely loads the library. Names in it must still be namespace-qualified in order to refer to them. Note that namespace names are separated from a name with a slash, whereas Java package names are separated from a class name with a period. For example:

(doc clojure.xml/parse)

; The alias function creates an alias for a namespace to reduce the amount of typing required to namespace-qualify symbols. Aliases are defined and only known within the current namespace. For example:

(alias 'x 'clojure.xml)
(doc x/parse)

; The refer function makes all the symbols in a given namespace accessible in the current namespace without namespace-qualifying them.
; An exception is thrown if a name in the given namespace is already defined in the current namespace. For example:

(refer 'clojure.xml)
(doc parse)

; The combination of require and refer is used often, so the shortcut function use is provided to do both.

(use 'clojure.xml)

; The ns macro, mentioned earlier, changes the default namespace.
; It is typically used at the top of a source file.
; It supports the directives :require, :use and :import (for importing Java classes)
; that are alternatives to using the above function forms.
; In the example below, note the use of :as to create an alias for a namespae.
; Also note the use of :only to load only part of a Clojure library

(ns com.benimmanuel.brap
  (:require [clojure.string :as su])
  ; assumes this dependency: [org.clojure/math.numeric-tower "0.0.1"]
  (:use [clojure.math.numeric-tower :only (gcd, sqrt)])
  (:import (java.text NumberFormat) (javax.swing JFrame JLabel)))

(println (su/join "$" [1 2 3])) ; 1$2$3
(println (gcd 27 72)) ; -> 9
(println (sqrt 5)) ; -> 2.236067...
(println (.format (NumberFormat/getInstance) Math/PI)) ; -> 3.142

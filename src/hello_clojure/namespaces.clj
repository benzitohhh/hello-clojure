; The "user" namespace provides access to all the symbols in the clojure.core namespace. The same is true of any namespace that is made the default through use of the ns macro.

; In order to access items that are not in the default namespace they must be namespace-qualified. This is done by preceding a name with a namespace name and a slash. For example, the clojure.string library defines the join function. It creates a string by concatenating a given separator string between the string representation of all the items in a sequence. The namespace-qualified name of this function is clojure.string/join.

; The require function loads Clojure libraries. It takes one or more quoted namespace names. For example:

(require 'clojure.string)

; This merely loads the library. Names in it must still be namespace-qualified in order to refer to them. Note that namespace names are separated from a name with a slash, whereas Java package names are separated from a class name with a period. For example:

(clojure.string/join "$" [1 2 3]) ; -> "1$2$3"

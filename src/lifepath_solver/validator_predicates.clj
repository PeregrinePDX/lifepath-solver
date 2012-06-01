(ns lifepath-solver.validator-predicates)

(defn position 
  "Handles position requirements"
  [operand req]
  (fn [coll] (operand req (get coll :position))))

(defn restrict-and
  "Composes multiple requirements returning true if all return true."
  [& req-list]
  (fn [coll] (every? true? (map (fn [fun] (fun coll)) req-list))))

(defn restrict-or
  "Composes multiple requirements"
  [& req-list]
  (fn [coll] (some every? true? (map (fn [fun] (fun coll)) req-list))))

(defn skill-req
  "Applies an operand to the conjed list of skills of previous lps
   FIXME: STUB"
  [operand req]
  (fn [coll] (true)))

(defn lifepath-req
  "Applies an operand to the list of previous lifepaths
   FIXME: STUB"
  [operand req]
  (fn [coll] (true)))

(defn setting-req
  "Applies an operand to the conjed list of previous settings
   FIXME: STUB"
  [operand req]
  (fn [coll] (true)))

(defn has? [list key] true)
(def has-not? (complement has?))
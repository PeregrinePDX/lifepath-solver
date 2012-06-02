(ns lifepath-solver.validator-predicates)

(defn restrict-and
  "Composes multiple requirements returning true if all return true."
  [& req-list]
  (fn [coll] (every? true? (map (fn [fun] (fun coll)) req-list))))

(defn restrict-or
  "Composes multiple requirements"
  [& req-list]
  (fn [coll] (some every? true? (map (fn [fun] (fun coll)) req-list))))

(defn position 
  "Handles position requirements"
  [operand req]
  (fn [coll] (operand req (:position coll))))

(defn list-length
  "Verifies LP list length requirements. <= 3 "
  [operand req]
  (fn [coll] (operand req (:lp-list-length coll) )))

(defn property-req
  "Applies an operand to the property  from previous lps"
  [property operand req]
  (fn [coll] (operand req (get coll property))))

(def skill-req (partial property-req :skill-list))
(def lifepath-req (partial property-req :lp-list))
(def setting-req (partial property-req :setting))


(defn has-not?   [key list]
  (nil? (some (if (set? key) key #{key}) list)))
(def has? (complement has-not?))


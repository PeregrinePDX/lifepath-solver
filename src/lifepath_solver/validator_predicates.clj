(ns lifepath-solver.validator-predicates)

; Copyright Chris Lappe <chris.lappe@gmail.com> 2012
; This file implements the various DSL predicates to verify a lifepaths 
; requirements.

(defn restrict-and
  "Composes multiple requirements returning true if all return true."
  [& req-list]
  (fn [coll] (every? true? (map (fn [fun] (fun coll)) req-list))))

(defn restrict-or
  "Composes multiple requirements"
  [& req-list]
  (fn [coll] (some every? true? (map (fn [fun] (fun coll)) req-list))))

(defn position 
  "Handles position requirements. The arguments are an operand such as =, not=, <= and the actual requirement which should be a integer. So to implement a lifepath that has to be the first lp chosen you would do postion = 1"
  [operand req]
  (fn [coll] (operand req (:position coll))))

(defn list-length
  "Verifies LP list length requirements. The arguments are an operand such as =, not=, <= and the actual requirement which should be an integer. So to implement a lifepath that you could only take if you had at most 3 lifepaths you would write list-length <= 3 "
  [operand req]
  (fn [coll] (operand req (:lp-list-length coll) )))

(defn property-req
  "Applies an operand to the property from previous lps. This is meant as a generic function to be refined further. The arguments are a property to check, an operand such as =, not=, or has? and the actual requirement "
  [property operand req]
  (fn [coll] (operand req (get coll property))))

; The following 3 functions use the property-req function for implementation.

(def skill-req (partial property-req :skill-list))
(def lifepath-req (partial property-req :lp-list))
(def setting-req (partial property-req :setting))

(defn has-not?
  [key list]
  (nil? (some (if (set? key) key #{key}) list)))
(def has? (complement has-not?))

(defn count-lp?
  [key list]
  (let [lp (first key)
        operand (second key)
        times (nth key 2)]
    (operand (count (filter #(= lp %) list)) times)))

(ns lifepath-solver.core
  (:require [lifepath-solver.testlp-db :as db]))

(defn check-lp? 
  "Takes a vector of Index, [Vector of setting and lp] the entire lp-list and verifies that lp is valid at that position"
  [pos lp-list]
    (let [item (get lp-list pos)]
      (let [setting (get item :setting) name (get item :name) ]
        (let [restriction (get (get (get db/lp-db setting) name) :restriction)]
          (def previous-lps (take pos lp-list))
          (def skill-list (flatten (map (fn [coll] (get (get (get db/lp-db (get coll :setting)) (get coll :name)) :skills))  previous-lps)))
          (def setting-list (map (fn [coll] (get coll :setting)) previous-lps))
          (def prevlp-list (map (fn [coll] (get coll :name)) previous-lps))
          (restriction {:position (inc pos) :name name :setting setting-list :lp-list prevlp-list :skill-list skill-list})))))

(defn valid-lp-list?
  "Takes a lifepath list and verifies that all lp restrictions are met. Returns True if list is valid. False if it's not."
  [lp-list]
  (every? true? (map-indexed (fn [idx _] (check-lp? idx lp-list)) lp-list)))

(defn -main
  "Do a test of our validator"
  [& args]
  (valid-lp-list? [{:setting :Test :name "Born Tester"} {:setting :Test2 :name "Acceptance Tester"}]))

(ns lifepath-solver.core
  [:require lifepath-solver.lifepaths-db :as db])

(defn check-lp? 
  "Takes a vector of Index, [Vector of setting and lp] the entire lp-list and verifies that lp is valid at that position"
  [pos lp-list]
    (let [item (get lp-list pos)]
      (let [setting (get item :setting) name (get item :name) ]
        (let [restriction (get (get (get db/lp-db setting) name) :restriction)]
          (restriction {:position (inc pos) :name name :setting setting :lp-list lp-list})))))

(defn valid-lp-list?
  "Takes a lifepath list and verifies that all lp restrictions are met. Returns True if list is valid. False if it's not."
  [lp-list]
  (every? true? (map-indexed (fn [idx _] (check-lp? idx lp-list)) lp-list)))

(defn -main
  "Do a test of our validator"
  [& args]
  (valid-lp-list? [{:setting :Villager :name "Village Born"} {:setting :Villager :name "Kid"}]))

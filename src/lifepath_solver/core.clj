(ns lifepath-solver.core
  (:require [lifepath-solver.testlp-db :as db]))

; Copyright Chris Lappe <chris.lappe@gmail.com> 2012
; This code is intended to verify the validity of a series of lifepaths for
; the roleplaying game Burning Wheel. Lifepaths are used during character 
; creation and have a complex set of requirements that sometimes makes it hard
; to verify if something is valid or not.
;
; The core namespace is going to have a minimal set of functions in it. 
; check-lp? will check that a specific lp meets all of it's requirements.
; valid-lp-list? will check that a list of lp's is valid.
;
; We'll implement this by writing a simple DSL to describe the constraints of
; a lifepath. 
;
; The namespace lifepath-solver.testlp-db is a testing database of fake
; lifepaths used for testing and publishing on the internet since the actual
; lifepath database is copyright and I do not have permission to post it 
; publicly.

(defn check-lp? 
  "Takes an ndex and the entire lp-list and verifies that the lp at that position meets the requirements. Returns true if it is valid and false if it is not."
  [pos lp-list]
    (let [item (get lp-list pos)      ; Retrieves the item from the list to be tested. 
          setting (:setting item )    ; Retrieves the setting of that item.
          name (:name item )          ; Retrieves the lifepath name.
          restriction (get-in db/lp-db [setting name :restriction]) 
          previous-lps (take pos lp-list) 
          skill-list (flatten (map (fn [coll] (get-in db/lp-db [(:setting coll) (:name coll) :skills]))  previous-lps))
          setting-list (map (fn [coll] (:setting coll)) previous-lps)
          prevlp-list (map (fn [coll] (:name coll)) previous-lps)
          lp-list-length (count lp-list)]
       (restriction {:position (inc pos) :name name :setting setting-list :lp-list prevlp-list :skill-list skill-list :lp-list-length lp-list-length})))

(defn valid-lp-list?
  "Takes a lifepath list and verifies that all lp restrictions are met. Returns True if list is valid. False if it's not."
  [lp-list]
  (every? true? (map-indexed (fn [idx _] (check-lp? idx lp-list)) lp-list)))

(defn -main
  "Do a test of our validator"
  [& args]
  (valid-lp-list? [{:setting :Test :name "Born Tester"} {:setting :Test2 :name "Acceptance Tester"}]))

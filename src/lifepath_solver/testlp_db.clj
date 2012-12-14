(ns lifepath-solver.testlp-db
  (:use [lifepath-solver.validator-predicates]))

; Copyright Chris Lappe <chris.lappe@gmail.com> 2012
; This is a quick test lifepath database. Containing the minimal necessary 
; information to test this. Eventually the database will be moved to an 
; actual database instead of being a static file. The most interesting part
; of this code is the values stored in :restriction which is a DSL to describe
; the requirements for a lifepath. The actuall restriction predicates are 
; implemented in lifepath-solver.validator-predicates.


(def lp-db {
:Test           {
  "Born Tester" 	{
       :time            3 
       :leads          [:Test2]
       :traits         ["Wierdly shaped"]
       :skills         ["General"]
       :restriction    (position = 1)}
  "Badly Abused" 		{
       :time		25
       :leads		[:Test2]
       :traits		["Caffeine addicted"]
       :skills		["Caffeinated beverage-wise" "Exception-wise"]
       :restriction	(position not= 1)}
  "Java Programmer" 		{
       :time		2
       :leads		[:Test2]
       :traits		["CamelCaser"]
       :skills		["Patterns-wise"]
       :restriction	(restrict-and (position not= 1) (skill-req has? "Exception-wise"))}
  "Programming God" {	
       :time		2
       :leads		[:Test2]
       :traits		["Foo"]
       :skills		["Patterns-wise"]
       :restriction	(restrict-and (position not= 1) (setting-req has? :Test2))}}
 
            
:Test2			{ 
   "Born in a black Box"	{
       :time		4
       :leads		[:Test]
       :skills		["General"]
       :restriction	(position = 1)}
   "Acceptance Tester"			{
       :time		1
       :leads		[:Test]
       :skills		["Trouble-wise" "Throwing Exceptions"]
       :traits		["Incompetent"]
       :restriction	(restrict-and (position not= 1) (position = 2))}
   "Clojure Programmer"			{
       :time		1
       :leads		[:Test]
       :skills		["Trouble-wise" "Throwing Exceptions"]
       :traits		["Incompetent"]
       :restriction	(restrict-and (position not= 1) (lifepath-req has? "Java Programmer"))}}

:LP-list-length      {
   "Born Limited"  {
       :time            8
       :leads           [:Test2]
       :skills          ["A" "B"]
       :traits          ["Not a trait"]
       :restriction     (position = 1)}
   "Length Limited" {
       :time            2
       :leads           [:Test]
       :skills          ["C" "B"]
       :restriction     (restrict-and (position not= 1) (list-length <= 2))}
   "ALAL" {
       :time            2
       :leads           [:Test]
       :skills          ["C" "B"]
       :restriction     (restrict-and (position not= 1) (list-length > 2))}}


:Lifepath-Reqs    {  
   "Born to a lifepath" {
       :time            8
       :leads           [:Test2]
       :skills          ["A" "B"]
       :traits          ["Not a trait"]
       :restriction     (position = 1)}
   "Do this multiple times" { 
       :time            8
       :leads           [:Test2]
       :skills          ["A" "B"]
       :traits          ["Not a trait"]
       :restriction     (restrict-and (position not= 1) (lifepath-req has? #{"Born to a lifepath"}))}
   "Must have had a previous lp multiple times" {
       :time            8
       :leads           [:Test2]
       :skills          ["A" "B"]
       :traits          ["Not a trait"]
       :restriction     (lifepath-req count-lp? ["Do this multiple times" = 3])}}})

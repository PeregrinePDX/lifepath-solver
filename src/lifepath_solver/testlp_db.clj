(ns lifepath-solver.testlp-db
  (:use [lifepath-solver.validator-predicates]))

(def lp-db {:Test           { 	"Born Tester" 	{	:time 3 
											:resources 45 
											:leads [:Test2]
											:trait-pts 45
                      :traits ["Wierdly shaped"]
											:skill-pts 3
											:skills ["General"]
											:restriction (position = 1)}
					   	"Badly Abused" 		{	:time		25
					   						:resources 	1
					   						:stat		{:p 1}
					   						:leads		[:Test2]
					   						:trait-pts	1
					   						:traits		["Caffeine addicted"]
					   						:skill-pts	8
					   						:skills		["Caffeinated beverage-wise" "Exception-wise"]
					   						:restriction	(position not= 1)}
               "Java Programmer" 		{	:time		2
					   						:resources 	1
					   						:stat		{:m 1}
					   						:leads		[:Test2]
					   						:trait-pts	1
					   						:traits		["CamelCaser"]
					   						:skill-pts	8
					   						:skills		["Patterns-wise"]
					   						:restriction	(restrict-and (position not= 1) (skill-req has? "Exception-wise"))}
               "Programming God" {	:time		2
					   						:resources 	1
					   						:stat		{:m 1}
					   						:leads		[:Test2]
					   						:trait-pts	1
					   						:traits		["Foo"]
					   						:skill-pts	8
					   						:skills		["Patterns-wise"]
					   						:restriction	(restrict-and (position not= 1) (setting-req has? :Test2))}}
 :Test2			{ 	"Born in a black Box"	{	:time		4
 											:resources	12
 											:leads		[:Test]
 											:skills-pts	3
 											:skills		["General"]
 											:trait-pts	2
 											:restriction	(position = 1)}
 						"Acceptance Tester"			{	:time		1
 											:resources	24
 											:stat		{:p 1}
 											:leads		[:Test]
 											:skill-pts	3
 											:skills		["Trouble-wise" "Throwing Exceptions"]
 											:trait-pts	1
 											:traits		["Incompetent"]
 											:restriction	(restrict-and (position not= 1) (position = 2))}
        		"Clojure Programmer"			{	:time		1
 											:resources	24
 											:stat		{:p 1}
 											:leads		[:Test]
 											:skill-pts	3
 											:skills		["Trouble-wise" "Throwing Exceptions"]
 											:trait-pts	1
 											:traits		["Incompetent"]
 											:restriction	(restrict-and (position not= 1) (lifepath-req has? "Java Programmer"))}}
 :LP-list-length      { "Born Limited"  {  :time 8
                              :resources 15
                              :stat {:m 1}
                              :leads [:Test2]
                              :skill-pts 3
                              :skills ["A" "B"]
                              :trait-pts 2
                              :traits ["Not a trait"]
                              :restriction (position = 1)}
                         "Length Limited" { :time 2
                              :resources 3
                              :stat {#{:m :p} 1}
                              :leads [:Test]
                              :skill-pts 1
                              :skills ["C" "B"]
                              :trait-pts 1
                              :restriction (restrict-and (position not= 1) (list-length <= 2))}
                         "ALAL" { :time 2
                              :resources 3
                              :stat {#{:m :p} 1}
                              :leads [:Test]
                              :skill-pts 1
                              :skills ["C" "B"]
                              :trait-pts 1
                              :restriction (restrict-and (position not= 1) (list-length > 2))}}
 :Lifepath-Reqs    {  "Born to a lifepath" { :time 8
                              :resources 15
                              :stat {:m 1}
                              :leads [:Test2]
                              :skill-pts 3
                              :skills ["A" "B"]
                              :trait-pts 2
                              :traits ["Not a trait"]
                              :restriction (position = 1)}
                       "Do this multiple times" { :time 8
                              :resources 15
                              :stat {:m 1}
                              :leads [:Test2]
                              :skill-pts 3
                              :skills ["A" "B"]
                              :trait-pts 2
                              :traits ["Not a trait"]
                              :restriction (restrict-and (position not= 1) (lifepath-req has? #{"Born to a lifepath"}))}
                       "Must have had a previous lp multiple times" {:time 8
                              :resources 15
                              :stat {:m 1}
                              :leads [:Test2]
                              :skill-pts 3
                              :skills ["A" "B"]
                              :trait-pts 2
                              :traits ["Not a trait"]
                              :restriction (lifepath-req count-lp? ["Do this multiple times" = 3])}}})

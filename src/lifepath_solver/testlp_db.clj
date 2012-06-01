(ns lifepath-solver.testlp-db
  [:use lifepath-solver.validator-predicates])

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
					   						:restriction	(restrict-and (position not= 1) (skill-req has? "Exception-wise"))}}
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
 											:restriction	(restrict-and (position not= 1) (position = 2))}}})

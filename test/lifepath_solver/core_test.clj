(ns lifepath-solver.core-test
  (:use clojure.test
        lifepath-solver.core))

(deftest check-lp
  (testing "Position Requirements"
    (is (= true (check-lp? 0 [ {:setting :Test :name "Born Tester"} {:setting :Test :name "Badly Abused"}
                 {:setting :Test :name "Java Programmer"}])))
    (is (= true (check-lp? 1 [ {:setting :Test :name "Born Tester"} {:setting :Test :name "Badly Abused"}
                 {:setting :Test :name "Java Programmer"}])))
    (is (= false (check-lp? 1 [ {:setting :Test :name "Born Tester"} {:setting :Test :name "Born Tester"}
                 {:setting :Test :name "Java Programmer"}])))
    (is (= false (check-lp? 0 [ {:setting :Test :name "Badly Abused"} {:setting :Test :name "Badly Abused"}
                 {:setting :Test :name "Java Programmer"}]))))
  (testing "Available Skill Requirements"
    (is (= true (check-lp? 2 [ {:setting :Test :name "Born Tester"} {:setting :Test :name "Badly Abused"}
                 {:setting :Test :name "Java Programmer"}])))
    (is (= false (check-lp? 2 [ {:setting :Test :name "Born Tester"} {:setting :Test :name "Born Tester"}
                 {:setting :Test :name "Java Programmer"}]))))
  (testing "Previous Lifepath Requirements"
    (is (= true (check-lp? 3 [{:setting :Test :name "Born Tester"} {:setting :Test :name "Born Tester"}
                 {:setting :Test :name "Java Programmer"} {:setting :Test2 :name "Clojure Programmer"}])))
    (is (= false (check-lp? 2 [{:setting :Test :name "Born Tester"} {:setting :Test :name "Born Tester"}
                 {:setting :Test2 :name "Clojure Programmer"}]))))
  (testing "Previous Setting Requirements"
    (is (= true (check-lp? 4 [{:setting :Test :name "Born Tester"} {:setting :Test :name "Born Tester"}
                 {:setting :Test :name "Java Programmer"} {:setting :Test2 :name "Clojure Programmer"}
                 {:setting :Test :name "Programming God"}])))
    (is (= false (check-lp? 3 [{:setting :Test :name "Born Tester"} {:setting :Test :name "Born Tester"}
                 {:setting :Test :name "Java Programmer"} {:setting :Test :name "Programming God"}])))))
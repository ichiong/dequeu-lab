(ns deque.t-core
  (:use midje.sweet)
  (:use [deque.core])
  (:import [deque.core Deque]))

(facts "about this lab"
  (fact "the student never started it."
        (+ 1 2)  => 3))

(facts "about `Deque.`"
  (fact "it creates a record with a `front` list, a `back` list, and a `size`."
     (let [deque-I     (Deque. '(1 2 3) '(4 5 6) 6)
           deque-II    (Deque. '() '(4 5 6) 3)
           deque-III   (Deque. '(1 2 3) '() 3)]
       (:front deque-I)   => '(1 2 3)
       (:back  deque-I)   => '(4 5 6)
       (:size  deque-I)   => 6
       (:front deque-II)  => '()
       (:back deque-II)   => '(4 5 6)
       (:size deque-II)   => 3
       (:front deque-III)  => '(1 2 3)
       (:back deque-III)   => '()
       (:size deque-III)   => 3))
  (fact "you use `make-deque` to create an empty deque."
     (let [nu-deque (make-deque)]
       (:front (make-deque))  => '()
       (:back  (make-deque))  => '()
       (:size  (make-deque))  => 0)))

(facts "about `deque-size"
  (fact "it returns the proper size of a deque."
     (let [deque-I     (Deque. '(1 2 3) '(4 5 6) 6)
           deque-II    (Deque. '() '(4 5 6) 3)
           deque-III   (Deque. '(1 2 3) '() 3)]
       (deque-size deque-I)   => 6
       (deque-size deque-II)  => 3
       (deque-size deque-III) => 3
       (deque-size (make-deque)) => 0)))

(facts "about `push-front`"
  (fact "it adds an element to the front list of the deque."
     (let [dq1    (Deque. '() '(4 5 6) 3)
           dq2    (Deque. '(1 2 3) '() 3)
           dq3    (Deque. '(1 2 3) '(4 5 6) 6)]
       (push-front dq1 1)   => (Deque. '(1) '(4 5 6) 4)
       (push-front dq2 9)   => (Deque. '(9 1 2 3) '() 4)
       (push-front dq3 9)   => (Deque. '(9 1 2 3) '(4 5 6) 7)
       (push-front (make-deque) 1) => (Deque. '(1) '() 1)
       (deque-size (push-front dq1 1))   => 4
       (deque-size (push-front dq2 9))   => 4
       (deque-size (push-front dq3 9))   => 7
       (deque-size (push-front (make-deque) 1)) => 1)))

(facts "about `push-back`"
  (fact "it adds an element to the back list of the deque."
     (let [dq1     (Deque. '() '(4 5 6) 3)
           dq2     (Deque. '(1 2 3) '() 3)
           dq3     (Deque. '(1 2 3) '(4 5 6) 6)]
       (push-back dq1 1)   => (Deque. '() '(1 4 5 6) 4)
       (push-back dq2 9)   => (Deque. '(1 2 3) '(9) 4)
       (push-back dq3 9)   => (Deque. '(1 2 3) '(9 4 5 6) 7)
       (push-back (make-deque) 1) => (Deque. '() '(1) 1)
       (deque-size (push-back dq1 1))   => 4
       (deque-size (push-back dq2 9))   => 4
       (deque-size (push-back dq3 9))   => 7
       (deque-size (push-back (make-deque) 1)) => 1)))

(facts "about `flip-front`"
  (fact "it flips the back list to the front list, if necessary."
     (let [dq1   (Deque. '() '(4 5 6) 3)
           dq2   (Deque. '(1 2 3) '() 3)
           dq3   (Deque. '(1 2 3) '(4 5 6) 6)]
       (flip-front dq1) => (Deque. '(6 5 4) '() 3)
       (flip-front dq2) => (Deque. '(1 2 3) '() 3)
       (flip-front dq3) => (Deque. '(1 2 3) '(4 5 6) 6)
       (flip-front (make-deque)) => (Deque. '() '() 0)
       ;; check front
       (:front (flip-front dq1)) => '(6 5 4)
       (:front (flip-front dq2)) => '(1 2 3)
       (:front (flip-front dq3)) => '(1 2 3)
       (:front (flip-front (make-deque))) => '()
       ;; check back
       (:back (flip-front dq1))  => '()
       (:back (flip-front dq2))  => '()
       (:back (flip-front dq3))  => '(4 5 6)
       (:back (flip-front (make-deque))) => '()
       ;; check size
       (deque-size (flip-front dq1))   => 3
       (deque-size (flip-front dq2))   => 3
       (deque-size (flip-front dq3))   => 6
       (deque-size (flip-front (make-deque))) => 0)))


(facts "about `flip-back`"
  (fact "it flips the front list to the back list, if necessary."
     (let [dq1   (Deque. '() '(4 5 6) 3)
           dq2   (Deque. '(1 2 3) '() 3)
           dq3   (Deque. '(1 2 3) '(4 5 6) 6)]
       (flip-back dq1) => (Deque. '() '(4 5 6) 3)
       (flip-back dq2) => (Deque. '() '(3 2 1) 3)
       (flip-back dq3) => (Deque. '(1 2 3) '(4 5 6) 6)
       (flip-back (make-deque)) => (Deque. '() '() 0)
       ;; check front
       (:front (flip-back dq1)) => '()
       (:front (flip-back dq2)) => '()
       (:front (flip-back dq3)) => '(1 2 3)
       (:front (flip-back (make-deque))) => '()
       ;; check back
       (:back (flip-back dq1))  => '(4 5 6)
       (:back (flip-back dq2))  => '(3 2 1)
       (:back (flip-back dq3))  => '(4 5 6)
       (:back (flip-back (make-deque))) => '()
       ;; check size
       (deque-size (flip-back dq1))   => 3
       (deque-size (flip-back dq2))   => 3
       (deque-size (flip-back dq3))   => 6
       (deque-size (flip-back (make-deque))) => 0)))


(facts "about `front`"
  (fact "it returns the front element of the deque. May cause a flip."
     (let [dq1   (Deque. '() '(4 5 6) 3)
           dq2   (Deque. '(1 2 3) '() 3)
           dq3   (Deque. '(1 2 3) '(4 5 6) 6)]
       (front dq1) => 6
       (front dq2) => 1
       (front dq3) => 1
       (front (make-deque)) => nil)))

(facts "about `back`"
  (fact "it returns the back element of the deque. May cause a flip."
     (let [dq1   (Deque. '() '(4 5 6) 3)
           dq2   (Deque. '(1 2 3) '() 3)
           dq3   (Deque. '(1 2 3) '(4 5 6) 6)]
       (front dq1) => 4
       (front dq2) => 3
       (front dq3) => 4
       (front (make-deque)) => nil)))

(facts "about `pop-front`"
  (fact "it pops/dequeues an element from the front of the deque."
     (let [dq1   (Deque. '(1 2) '(3 4) 4)
           dq2   (Deque. '(1 2) '() 2)
           dq3   (Deque. '() '(3 4 5) 3)]
       (pop-front dq1) => (Deque. '(2) '(3 4) 3)
       (pop-front dq2) => (Deque. '(2) '() 1)
       (pop-front dq3) => (Deque. '(4 3) '() 2)
       (pop-front (make-deque)) => (Deque. '() '() 0)
        ;; check front
       (:front (pop-front dq1)) => '(2)
       (:front (pop-front dq2)) => '(2)
       (:front (pop-front dq3)) => '(4 3)
       (:front (pop-front (make-deque))) => '()
       ;; check size (size should be decreased)
       (deque-size (pop-front dq1)) => 3
       (deque-size (pop-front dq2)) => 1
       (deque-size (pop-front dq3)) => 2
       (deque-size (pop-front (make-deque))) => 0)))

(facts "about `pop-back`"
  (fact "it pops/dequeues an element from the back of the deque."
     (let [dq1   (Deque. '(1 2) '(3 4) 4)
           dq2   (Deque. '(1 2 3) '() 3)
           dq3   (Deque. '() '(3 4) 2)]
       (pop-back dq1) => (Deque. '(1 2) '(4) 3)
       (pop-back dq2) => (Deque. '() '(2 1) 2)
       (pop-back dq3) => (Deque. '() '(4) 1)
       (pop-back (make-deque)) => (Deque. '() '() 0)
       ;; check back
       (:back (pop-back dq1)) => '(4)
       (:back (pop-back dq2)) => '(2 1)
       (:back (pop-back dq3)) => '(4)
       (:back (pop-back (make-deque))) => '()
       ;; check size (size should be decreased)
       (deque-size (pop-back dq1)) => 3
       (deque-size (pop-back dq2)) => 2
       (deque-size (pop-back dq3)) => 1
       (deque-size (pop-back (make-deque))) => 0)))
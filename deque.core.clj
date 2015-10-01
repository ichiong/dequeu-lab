(ns deque.core)

(defrecord Deque [front back size])

;; # Your Work

(defn make-deque
  "Create an empty deque."
  []
  (Deque. '() '() 0))

(defn deque-size
  "Return the size of a deque."
  [dq]
  (:size dq))

(defn push-front
  "Adds an element to the front of the deque."
  [dq elt]
  (let [{:keys [front back size]} dq]
    (Deque. (cons elt front) back (inc size))))

(defn push-back
  "Adds an element to the back fo the deque."
  [dq elt]
  (let [{:keys [front back size]} dq]
    (Deque. front (cons elt back) (inc size))))

(defn flip-front
  "Flip the back list to the front list, if necessary."
  [{:keys [front back size] :as dq}]
   (if (empty? front)
           (Deque. (reverse back) front size)
           dq))

(defn flip-back
  "Flip the front list to the back list, if necessary."
   [{:keys [front back size] :as dq}]
   (if (empty? back)
           (Deque. back (reverse front) size)
           dq))

(defn front
  "Return the front element of the deque.  May cause a flip."
   [dq]
   (if (empty? (:front dq))
        (first (:front (flip-front dq)))
        (first (:front dq))))

(defn back
  "Return the back element of the deque.  May cause a flip."
   [dq]
   (if (empty? (:back dq))
        (first (:back (flip-back dq)))
        (first (:back dq))))

(defn pop-front
  "Pops/dequeues an element from the front of the deque."
  [{:keys [front back size] :as dq}]
   (cond (= 0 size) dq
         (empty? front)
           (let [tmp (flip-front dq)]
             (Deque. (rest (:front tmp)) (:back tmp) (dec (:size tmp))))
         :else (Deque. (rest front) back (dec size))))

(defn pop-back
  "Pops/dequeues an element from the back of the deque."
 [{:keys [front back size] :as dq}]
   (cond (= 0 size) dq
         (empty? back)
           (let [tmp (flip-back dq)]
             (Deque. (:front tmp) (rest (:back tmp)) (dec (:size tmp))))
         :else (Deque. front (rest back) (dec size))))



class Node{
    constructor(key, value){
        this.key = key;
        this.value = value;
        this.prev = null; // for previous node 
        this.next = null; // for next node
    }
} 
// every get and put functions will be from thses combinitions :
function removeNode(node){
    const prevNode = node.prev;
    const nextNode = node.next;
    prevNode.next = nextNode;
    nextNode.prev = prevNode;
}
    /**
 * @param {number} capacity
 */
var LRUCache = function(capacity) {
    this.capacity = capacity;
    this.map = new Map();

    this.head = new Node(-1, -1); // dumpy head 
    this.tail = new Node(-1, -1); // dumpy tail 

    this.head.next = this.tail;
    this.tail.prev = this.head;
    // second combinitions 
    this.addToFront = function (node){
        //Before: head <-> A <-> B <-> tail and we call addToFront(C)
        // step 1: C.prev = head 
        // step 2: C.next = head.next = A 
        // step 3: A.prev = C 
        // step 4: head.next = C
        // after: After:  head <-> C <-> A <-> B <-> tail 
        node.prev = this.head;
        node.next = this.head.next;
        this.head.next.prev = node;
        this.head.next = node;
};

/** 
 * @param {number} key
 * @return {number}
 */
LRUCache.prototype.get = function(key) {
    if (!this.map.has(key)){
        return -1; // this mean no key founded
    }
    const node = this.map.get(key);
    removeNode(node); // unhook from current position 
    this.addToFront(node); // re-insert as most recent 
    return node.value;
};

/** 
 * @param {number} key 
 * @param {number} value
 * @return {void}
 */
LRUCache.prototype.put = function(key, value) {
    if (this.map.has(key)){
        const node = this.map.get(key); 
        node.value = value; // this will update the value 
        removeNode(node); // unhook 
        this.addToFront(node); // add node to the front 
        return;
    }
    const newNode = new Node(key, value);
    this.map.set(key, newNode);
    this.addToFront(newNode);

    if (this.map.size > this.capacity){
        const LRU = this.tail.prev; // least recently used , real node 
        removeNode(LRU);
        this.map.delete(LRU.key);
    }

};

/** 
 * Your LRUCache object will be instantiated and called as such:
 * var obj = new LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */


}
// now I test the solution 
const obj = new LRUCache(2);
console.log(obj.put(1, 1)); // this wil return undefined
console.log(obj.put(2, 2)); // this wil return undefined
console.log(obj.get(1)); // return 1 
console.log(obj.put(3,3 ));
console.log(obj.get(2));    // -1
console.log(obj.put(4, 4)); // evicts key 1
console.log(obj.get(1));    // -1
console.log(obj.get(3));    // 3
console.log(obj.get(4));    // 4

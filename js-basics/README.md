# variables:
* Variables are Containers for Storing Data
JavaScript Variables can be declared in 4 ways:
Automatically
Using var
Using let
Using const
* Example:
  let x=2;
  var y=5;
  const z=9;
  # DataTypes:
  JavaScript has 8 Datatypes
1. String
2. Number
3. Bigint
4. Boolean
5. Undefined
6. Null
7. Symbol
8. Object
# The Object Datatype
The object data type can contain:

1. An object
2. An array
3. A date
   # functions:
   A JavaScript function is defined with the function keyword, followed by a name, followed by parentheses ().
   Function names can contain letters, digits, underscores, and dollar signs (same rules as variables).
   The parentheses may include parameter names separated by commas:
   (parameter1, parameter2, ...)
   ```
   function myFunction(p1, p2) {
  return p1 * p2
}
```

ex:
```
function myFunction() {
  alert("Hello World!");
}
# js Object:
```
```
let car = "Fiat";
const car = {type:"Fiat", model:"500", color:"white"};
* Acessing object properties:
  objectName.propertyName
  ex:
  const person = {
  firstName: "John",
  lastName : "Doe",
  id       : 5566,
  fullName : function() {
    return this.firstName + " " + this.lastName;
  }
};
name = person.fullName();
```
```
# Control Statement
If Statement
Using If-Else Statement
Using Switch Statement
Using the Ternary Operator (Conditional Operator)
Using For loop
```
* if ( condition_is_given_here ) {
    // If the condition is met, 
    //the code  will get executed.
}
```
# switch:
```
let num = 5;
 
switch (num) {
    case 0:
        console.log("Number is zero.");
        break;
    case 1:
        console.log("Nuber is one.");
        break;
    case 2:
        console.log("Number is two.");
        break;
    default:
        console.log("Number is greater than 2.");
};
```
# for loop
```
for (let i = 0; i <= 10; i++) {
  if (i % 2 === 0) {
    console.log(i);
  }
};
```
# Arrays
JavaScript Array is used to store multiple elements in a single variable. It can hold various data types, including numbers, strings, objects, and even other arrays. It is often used when we want to store a list of elements and access them by a single variable.
ex:
```
```
let courses = ["HTML", "CSS", "JavaScript", "React"]; 
let newArray = [];  
courses.forEach(function (course) { 
	newArray.push(course); 
}); 
console.log(newArray);
```
```
let x = 10;

if (x > 0) {
    console.log("x is positive");
}

if (x > 0) {
    console.log("x is positive");
} else {
    console.log("x is either zero or negative");
}

if (x > 0) {
    console.log("x is positive");
} else if (x === 0) {
    console.log("x is zero");
} else {
    console.log("x is negative");
}

```

```
# Break:
for (let i = 1; i <= 5; i++) {
    console.log(i);

    if (i === 3) {
        console.log("loop");
        break;
    }
}

```
```
for (let i = 1; i <= 5; i++) {
    if (i === 3) {
        console.log("loop");
        continue;
    }

    console.log(i);
}
```

# ES6
* Arrow function:
```
  const x = (x, y) => x * y;
  ```
* The spread operator:
```
  const q1 = ["Jan", "Feb", "Mar"];
const q2 = ["Apr", "May", "Jun"];
const q3 = ["Jul", "Aug", "Sep"];
const q4 = ["Oct", "Nov", "May"];

const year = [...q1, ...q2, ...q3, ...q4];
```

* For/of loop
  
  const cars = ["BMW", "Volvo", "Mini"];
let text = "";

for (let x of cars) {
  text += x + " ";
}


* map object:
  ```

const fruits = new Map([
["apples", 500],
["bananas", 300],
["oranges", 200]
]);
```
* set object:
```
  const letters = new Set();
letters.add("a");
letters.add("b");
letters.add("c");
* modules:
  import message from "./message.js";
* object entries:
const fruits = ["Banana", "Orange", "Apple", "Mango"];
const f = fruits.entries();

for (let x of f) {
}
* isfinite():
* isNaN();
isFinite(10/0);       // returns false
isFinite(10/1);
isNaN("helloworld"); //return true
* new integer method:
Number.isInteger(10);
*New number properties
let x = Number.MIN_SAFE_INTEGER;
* Array.From():
// Example 1: Converting a string to an array of characters
const str = 'Hello';
const charArray = Array.from(str);
console.log(charArray); // Output: [ 'H', 'e', 'l', 'l', 'o' ]
Array.keys()
const fruits = ['apple', 'banana', 'orange'];

const keysIterator = fruits.keys();

for (const key of keysIterator) {
    console.log(key); 
}
* Array.find()
const numbers = [10, 20, 30, 40, 50];

const found = numbers.find(num => num > 25);

console.log(found); // Output: 30
* array.findIndex()
const numbers = [10, 20, 30, 40, 50];

const index = numbers.findIndex(num => num > 25);

console.log(index); // Output: 2 (index of the first element greater than 25)
* string.includes()
const sentence = 'The quick brown fox jumps over the lazy dog';

const containsWord = sentence.includes('fox');

console.log(containsWord); // Output: true
* String.startwith()
  ```
const greeting = 'Hello, world!';

const startsWithHello = greeting.startsWith('Hello');

console.log(startsWithHello); // Output: true
```
* string.endwith()
```
const fileName = 'script.js';

const endsWithJs = fileName.endsWith('.js');

console.log(endsWithJs); // Output: true


```



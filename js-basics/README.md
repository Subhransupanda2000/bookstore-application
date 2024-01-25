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
   function myFunction(p1, p2) {
  return p1 * p2
}

ex:
function myFunction() {
  alert("Hello World!");
}
# js Object:
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
# Control Statement
If Statement
Using If-Else Statement
Using Switch Statement
Using the Ternary Operator (Conditional Operator)
Using For loop
* if ( condition_is_given_here ) {
    // If the condition is met, 
    //the code  will get executed.
}
# switch:
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
# for loop
for (let i = 0; i <= 10; i++) {
  if (i % 2 === 0) {
    console.log(i);
  }
};
# Arrays
JavaScript Array is used to store multiple elements in a single variable. It can hold various data types, including numbers, strings, objects, and even other arrays. It is often used when we want to store a list of elements and access them by a single variable.
ex:
let courses = ["HTML", "CSS", "JavaScript", "React"]; 
let newArray = [];  
courses.forEach(function (course) { 
	newArray.push(course); 
}); 
console.log(newArray); 

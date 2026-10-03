# Java OOP Interview Terminology

Object-Oriented Programming (OOP): A programming paradigm that organizes software around objects, which encapsulate data (state) and methods (behavior), using core principles such as encapsulation, abstraction, inheritance, and polymorphism.

## Contents

1. [Core OOP Concepts](#1-core-oop-concepts)
2. [Polymorphism and Method Terminology](#2-polymorphism-and-method-terminology)
3. [Java Class and Member Terminology](#3-java-class-and-member-terminology)
4. [Inheritance and Type Conversion](#4-inheritance-and-type-conversion)
5. [Abstract Classes and Interfaces](#5-abstract-classes-and-interfaces)
6. [Object Class and Object-Related Methods](#6-object-class-and-object-related-methods)
7. [Relationships and Design Principles](#7-relationships-and-design-principles)
8. [SOLID Principles](#8-solid-principles)
9. [Design Patterns](#9-design-patterns)
10. [Advanced Java OOP and Object Model](#10-advanced-java-oop-and-object-model)

---

## 1. Core OOP Concepts

1. Class: A class is a user-defined reference type that defines the state and behavior its objects can have.

2. Object: An object is a runtime instance of a class that encapsulates state (fields/attributes) and behavior (methods/operations).

3. State: State is the data held by an object at a particular point in time, typically represented by its fields.

4. Behavior: Behavior is the set of operations an object can perform, typically defined by its methods.

5. Attributes: Attributes are the conceptual characteristics or data associated with an object; in Java, these are commonly represented by fields.

6. Fields: Fields are variables declared in a class or interface that represent class-level or object-level data.

7. Methods: Methods are named blocks of code declared in a class or interface that define operations or behavior.

8. Encapsulation: The OOP principle of bundling data and methods into a single unit (class) and restricting direct access to the data through access modifiers and controlled methods.

9. Abstraction: Abstraction focuses on what an object does, hiding unnecessary implementation details and exposing only essential behavior.

10. Inheritance: Inheritance allows a class to acquire properties and behavior from a parent class and extend or specialize it.

11. Polymorphism: Polymorphism allows the same method call or reference to behave differently depending on the actual object type at runtime.

12. Association: Association is a relationship in which objects of different classes are connected or interact.

13. Aggregation: Aggregation is a whole–part relationship in which the part can exist independently of the whole.

14. Composition: Composition is a strong whole–part relationship in which the whole owns the part's lifecycle conceptually.

15. Modularity: Modularity is the practice of dividing software into distinct components with clear responsibilities and interfaces.

---

## 2. Polymorphism and Method Terminology

16. Method Overloading: A compile-time polymorphism mechanism in which a class defines methods with the same name but different parameter lists, allowing the compiler to select an overload based on the arguments.

17. Method Overriding: A runtime polymorphism mechanism in which a subclass provides an implementation of an inherited instance method with the same signature and a compatible return type, without reducing access visibility.

18. Compile-Time Polymorphism: A form of polymorphism in which the compiler resolves the applicable method or operation at compile time; method overloading is a common Java example.

19. Runtime Polymorphism: A form of polymorphism in which an overridden instance-method implementation is selected at runtime based on the actual object's class.

20. Dynamic Method Dispatch: A runtime mechanism in which the JVM selects the overridden instance-method implementation based on the actual object type, rather than the reference type.

21. Static Binding: Binding resolved without runtime virtual dispatch, typically at compile time; static methods, private methods, and overloaded method selection have different forms of non-virtual resolution.

22. Dynamic Binding: Binding in which the implementation of an overridable instance method is selected at runtime based on the actual object's class.

23. Early Binding: A common term for binding resolved before runtime, often at compile time.

24. Late Binding: A common term for runtime selection of an overridable method implementation based on the actual object's class.

25. Covariant Return Type: A return type used by an overriding method that is a subtype of the return type declared by the overridden method.

26. Method Signature: In Java, a method signature consists of the method name and its parameter types; the return type is not part of the signature.

27. super Keyword: The super keyword refers to the immediate superclass context and can access superclass constructors and accessible superclass members.

28. this Keyword: The this keyword refers to the current object and can distinguish instance fields from parameters or invoke another constructor in the same class.

29. Constructor Overloading: Constructor overloading is defining multiple constructors in the same class with different parameter lists.

30. Constructor Chaining: Constructor chaining is invoking one constructor from another using this(...) or invoking a superclass constructor using super(...).

31. Static Method Hiding: Static method hiding occurs when a subclass declares a static method with a matching signature; the method selected depends on the compile-time type, not runtime dispatch.

32. Field Hiding: Field hiding occurs when a subclass declares a field with the same name as an inherited field; field access is resolved using the reference's compile-time type.

---

## 3. Java Class and Member Terminology

33. Instance Variable: An instance variable is a non-static field for which each object has its own independent copy.

34. Class Variable: A class variable is a static field for which one shared variable exists per class, subject to class-loader identity.

35. Local Variable: A local variable is declared within a method, constructor, or block and is accessible within its declared scope.

36. Instance Method: An instance method is a non-static method invoked on an object and can access that object's instance state.

37. Static Method: A static method belongs to the class rather than a particular object and cannot directly access instance members without an object reference.

38. Access Modifiers: Access modifiers—public, protected, and private—control visibility; package-private access applies when no access modifier is specified.

39. Access Specifiers: Access specifier is a commonly used alternative term for access modifier, though Java documentation generally uses access modifier.

40. Package: A package groups related types and helps organize code and control package-level access.

41. Constructor: A constructor is a special class declaration used to initialize a new object; it has the class name and no return type.

42. Default Constructor: The default constructor is the no-argument constructor generated by the compiler only when a class declares no constructor.

43. No-Argument Constructor: A no-argument constructor is any constructor that accepts no parameters; it may be written explicitly or generated by the compiler under the default-constructor rule.

44. Initialization Block: An instance initialization block is a block that runs during object initialization, in textual order with instance field initializers, before the constructor body after superclass construction.

45. Static Block: A static block is a class initialization block that runs when the class is initialized, typically to initialize static state.

46. Object Initialization: Object initialization is the process of assigning initial values to an object's fields and executing instance initializers and constructors.

47. Object Lifecycle: An object's lifecycle covers its creation, use, and eventual eligibility for garbage collection when it becomes unreachable.

48. Garbage Collection: Garbage collection automatically reclaims heap memory occupied by objects that are no longer reachable, subject to JVM implementation behavior.

49. Immutable Class: An immutable class is designed so that an object's observable state cannot change after construction.

50. Mutable Object: A mutable object is an object whose state can change after it has been created.

51. POJO: A POJO (Plain Old Java Object) is an ordinary Java object that does not depend on a particular framework-specific base class or programming model.

52. Nested Class: A nested class is a class declared within another class or interface; it may be static or non-static.

53. Inner Class: An inner class is a non-static nested class associated with an instance of its enclosing class.

54. Anonymous Class: An anonymous class is a local class expression declared and instantiated without a name, often to provide a one-off implementation or subclass.

---

## 4. Inheritance and Type Conversion

55. Single Inheritance: Single inheritance is an inheritance structure in which a class directly extends one superclass.

56. Multilevel Inheritance: Multilevel inheritance is a chain in which a class extends a subclass that itself extends another class.

57. Hierarchical Inheritance: Hierarchical inheritance occurs when multiple subclasses directly extend the same superclass.

58. Multiple Inheritance: Multiple inheritance means inheriting from more than one direct parent type; Java classes cannot extend multiple classes, but can implement multiple interfaces.

59. Hybrid Inheritance: Hybrid inheritance combines multiple inheritance patterns; Java can model many such type relationships using interfaces.

60. Upcasting: Upcasting assigns a subclass object to a superclass reference, allowing it to be treated through the superclass type; it is implicit and generally safe.

61. Downcasting: Downcasting converts a superclass reference to a subclass type, requiring an explicit cast and potentially throwing ClassCastException if the object is not compatible.

62. instanceof: The instanceof operator tests whether an object is an instance of a specified type or a compatible subtype, and can also be used with pattern matching in modern Java.

63. IS-A Relationship: An IS-A relationship represents a subtype relationship, usually established in Java through class inheritance or interface implementation.

64. HAS-A Relationship: A HAS-A relationship describes a class that holds a reference to or uses an object of another type.

65. Object Slicing: Object slicing in C++ occurs when a derived object is copied by value into a base-class object, losing derived-specific state; Java references do not slice objects during upcasting.

66. Constructor Execution Order: During construction, superclass initialization and constructor execution occur before the subclass's instance initialization and constructor body.

67. Initialization Order: Java initializes static fields and static blocks during class initialization; object creation then initializes superclass state before subclass instance initializers and constructor bodies.

68. final Class: A final class cannot be subclassed.

69. final Method: A final instance method cannot be overridden by a subclass.

70. final Variable: A final variable can be assigned only once, subject to Java's initialization rules; a final reference cannot be reassigned but the referenced object may still be mutable.

---

## 5. Abstract Classes and Interfaces

71. Abstract Class: An abstract class is declared with abstract, cannot be instantiated, and may contain both abstract and concrete methods; it serves as a base class for subclasses.

72. Abstract Method: An abstract method is declared without an implementation and must be implemented by a concrete subclass unless that class remains abstract.

73. Interface: An interface is a reference type that defines a contract for implementing classes and supports abstraction and multiple inheritance of type in Java.

74. Default Method: A default method is an interface instance method with an implementation, declared using the default keyword.

75. Static Interface Method: A static interface method belongs to the interface itself and is called using the interface name.

76. Private Interface Method: A private interface method is a helper method used by other methods within the same interface and is not inherited by implementing classes.

77. Functional Interface: A functional interface has exactly one abstract method, making it suitable as the target type for a lambda expression or method reference.

78. Marker Interface: A marker interface declares no methods and marks a class as having a particular type or meaning, such as Serializable.

79. Multiple Inheritance of Type: Multiple inheritance of type allows a Java class to implement multiple interfaces and be used through each interface type.

80. Interface Implementation: Interface implementation occurs when a class declares that it implements an interface and supplies implementations for its required abstract methods, unless the class is abstract.

81. Abstract Class vs Interface: An abstract class can hold instance state and constructors and can be extended by one class; an interface defines a type contract and a class can implement multiple interfaces.

---

## 6. Object Class and Object-Related Methods

82. Object Class: java.lang.Object is the root superclass of Java classes that do not explicitly extend another class.

83. toString(): toString() returns a string representation of an object and is commonly overridden to provide useful descriptive information.

84. equals(): equals() defines logical equality between objects according to its implementation; Object's default implementation compares object identity.

85. hashCode(): hashCode() returns an integer hash value used by hash-based collections to organize entries efficiently.

86. == Operator: For primitives, == compares values; for references, it tests whether both references identify the same object.

87. Reference Equality: Reference equality means two references point to the exact same object.

88. Logical Equality: Logical equality means two objects are considered equivalent according to their equals() implementation.

89. Hash Collision: A hash collision occurs when distinct keys produce the same hash code.

90. getClass(): getClass() returns the runtime Class object representing the class of an object.

91. clone(): Object.clone() creates a field-by-field copy when cloning is supported; the default operation is shallow, and Cloneable is used to indicate support for cloning.

92. Shallow Copy: A shallow copy duplicates an object's top-level field values, so references inside the copy still point to the same nested objects.

93. Deep Copy: A deep copy duplicates an object and the relevant objects reachable through its fields, rather than sharing those nested mutable objects.

94. Object Identity: Object identity distinguishes one particular object instance from other objects, even if they have equal state.

---

## 7. Relationships and Design Principles

95. Dependency: A dependency exists when one component uses or relies on another component to perform its work.

96. Coupling: Coupling describes the degree of dependency between software components.

97. Cohesion: Cohesion describes how closely related the responsibilities within a component are.

98. Loose Coupling: Loose coupling minimizes direct dependencies between components, making them easier to change, test, and reuse.

99. High Cohesion: High cohesion means a class or module focuses on a closely related set of responsibilities.

100. Separation of Concerns: Separation of concerns divides a system into parts that address distinct responsibilities.

101. Dependency Injection: Dependency injection supplies a component's dependencies from outside rather than having the component create them itself.

102. Inversion of Control (IoC): Inversion of Control shifts responsibility for some control decisions, such as object creation or lifecycle management, to a framework or container.

103. Programming to an Interface: Programming to an interface means depending on an abstraction rather than a concrete implementation, reducing coupling and enabling substitution.

104. Delegation: Delegation is a design technique in which an object forwards a task to another object that performs it.

105. Composition Over Inheritance: Composition over inheritance is the design principle of preferring collaboration between contained objects when it provides more flexible reuse than inheritance.

106. Information Hiding: Information hiding conceals internal implementation decisions and exposes only a controlled public interface.

107. Encapsulation vs Information Hiding: Encapsulation bundles state and behavior within a unit; information hiding restricts access to internal details and design decisions.

---

## 8. SOLID Principles

108. Single Responsibility Principle (SRP): A class should have one responsibility and one primary reason to change.

109. Open/Closed Principle (OCP): Software entities should be open to extension but closed to modification, so new behavior can be added with minimal changes to stable code.

110. Liskov Substitution Principle (LSP): Objects of a subtype should be usable wherever objects of its supertype are expected without breaking the program's correctness.

111. Interface Segregation Principle (ISP): Clients should not be forced to depend on interface methods they do not use; prefer focused interfaces over oversized ones.

112. Dependency Inversion Principle (DIP): High-level and low-level modules should depend on abstractions rather than directly depending on one another's concrete implementations.

---

## 9. Design Patterns

113. Singleton: The Singleton pattern restricts a class to one intended instance and provides a common access point to it.

114. Factory Method: The Factory Method pattern defines a creation method whose implementation can choose which concrete product type to instantiate.

115. Abstract Factory: The Abstract Factory pattern provides an interface for creating families of related objects without specifying their concrete classes.

116. Builder: The Builder pattern separates the step-by-step construction of a complex object from its final representation.

117. Prototype: The Prototype pattern creates new objects by copying an existing prototype instance.

118. Strategy: The Strategy pattern encapsulates interchangeable algorithms behind a common interface so that the algorithm can vary independently of its client.

119. Observer: The Observer pattern establishes a one-to-many dependency so that registered observers are notified when the subject's state changes.

120. Decorator: The Decorator pattern adds responsibilities to an object by wrapping it, without changing the wrapped object's class.

121. Adapter: The Adapter pattern converts one interface into another interface expected by a client.

122. Facade: The Facade pattern provides a simplified interface to a complex subsystem.

123. Proxy: The Proxy pattern provides a stand-in object that controls access to another object.

124. Template Method: The Template Method pattern defines an algorithm's overall steps in a base class while allowing subclasses to customize selected steps.

125. Command: The Command pattern encapsulates a request as an object, allowing requests to be queued, logged, or undone when supported.

126. State: The State pattern lets an object's behavior vary with its internal state by delegating state-specific behavior to state objects.

---

## 10. Advanced Java OOP and Object Model

127. Reference Type: A reference type is a type whose variables hold references to objects or arrays rather than primitive values directly.

128. Primitive Type: A primitive type is one of Java's eight built-in value types: boolean, byte, short, char, int, long, float, and double.

129. Wrapper Class: A wrapper class represents a primitive value as an object, such as Integer for int or Boolean for boolean.

130. Autoboxing: Autoboxing is the automatic conversion of a primitive value to its corresponding wrapper type when required by the language.

131. Unboxing: Unboxing is the automatic conversion of a wrapper object to its corresponding primitive value; unboxing null throws NullPointerException.

132. Widening Conversion: A widening primitive conversion converts a value to a compatible type with a broader range or precision and is often implicit.

133. Narrowing Conversion: A narrowing conversion converts a value to a type with a smaller range or precision and may lose information; an explicit cast is commonly required for primitive narrowing.

134. Covariance: In Java method overriding, covariance refers to allowing the overriding method to return a subtype of the original method's reference return type.

135. Invariance: Java generic types are generally invariant: for example, List<Integer> is not a subtype of List<Number> even though Integer is a subtype of Number.

136. Generic Type: A generic type is a class or interface parameterized by type variables to provide stronger compile-time type checking and reuse.

137. Wildcard: A generic wildcard (?) represents an unknown type argument and can be bounded with extends or super.

138. Type Erasure: Type erasure removes most generic type-parameter information during compilation while inserting necessary casts and preserving type safety.

139. Reifiable Type: A reifiable type is a type whose relevant type information is fully available at runtime, such as String or List<?>.

140. Class Loading: Class loading is the process by which the JVM locates class bytecode and creates the corresponding Class representation.

141. Class Initialization: Class initialization is the JVM process of executing class variable initializers and static initialization blocks.

142. Reflection: Reflection allows a program to inspect and, where permitted, interact with classes, fields, methods, and constructors at runtime.

143. Annotations: Annotations provide metadata about program elements and can be used by the compiler, tools, or runtime frameworks.

144. Sealed Class: A sealed class or interface restricts which types may directly extend or implement it, using permits or declarations in the same compilation context as allowed by Java rules.

145. Record: A record is a concise Java class form intended to model data, with component-based fields and generated methods such as accessors, equals(), hashCode(), and toString().

146. Enum: An enum is a Java reference type that defines a fixed set of named constants and can also contain fields, methods, and constructors.

---

## High-priority interview reminders

- Overloading uses different parameter lists; return type alone does not distinguish overloads.

- Overriding applies to instance methods. Static methods are hidden, and fields are hidden rather than dynamically dispatched.

- Runtime polymorphism depends on the actual object type for an overridable instance-method call; the reference type controls which members are accessible at compile time.

- Java supports multiple inheritance of type through interfaces, but a class can extend only one class.

- If two objects are equal according to equals(), they must return the same hashCode(); the reverse is not required.

- Upcasting does not slice an object in Java. Object slicing is a C++ value-copy behavior.

- A final reference cannot be reassigned, but the object it refers to may still be mutable.

- The compiler generates a default no-argument constructor only when no constructor is declared in the class.
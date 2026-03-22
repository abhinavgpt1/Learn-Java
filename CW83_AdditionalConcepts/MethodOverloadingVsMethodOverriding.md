# Method Overloading and Method Overriding in Java

## Method Overloading

Method overloading allows a class to have multiple methods with the same name but different parameter lists. This is a form of compile-time polymorphism (static binding).

### Key Characteristics:
- **Same method name** in the same class
- **Different parameters**: different number, types, or order of parameters
- **Return type** can be the same or different. It's not a necessary condition but it's not sufficient enough for performing method overloading.
- **Access modifiers** can vary
- Resolved at compile time based on method signature

### Example:
```java
class Calculator {
    int add(int a, int b) {
        return a + b;
    }
    
    double add(double a, double b) {
        return a + b;
    }
    
    int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

## Method Overriding

Method overriding occurs when a subclass provides a specific implementation for a method that is already defined in its superclass. This is a form of runtime polymorphism (dynamic binding).

### Key Characteristics:
- **Same method name and parameters** in superclass and subclass
- **Inheritance relationship** required (subclass extends superclass)
- **Return type** must be the same or covariant (subclass type)
- **Access modifier** cannot be more restrictive than superclass method
- **Checked exceptions** in override cannot declare new checked exceptions
- Resolved at runtime based on actual object type

### Example:
```java
class Animal {
    void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog barks");
    }
}
```

### Important Rules for Overriding:
- Cannot override `final`, `static`, or `private` methods
- Constructors cannot be overridden
- Overriding method can throw unchecked exceptions even if superclass method doesn't
- Use `@Override` annotation for clarity and compile-time checking

## Key Differences

| Aspect | Overloading | Overriding |
|--------|-------------|------------|
| Method signature | Same name, different parameters | Same name, same parameters |
| Class relationship | Same class | Inheritance (superclass-subclass) |
| Polymorphism type | Compile-time | Runtime |
| Return type | Can differ | Must be same or covariant |
| Access modifier | Can differ | Cannot be more restrictive |
| Exceptions | No restrictions | Checked exceptions restricted |
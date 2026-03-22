# Java Value Categories: Assignment Targets vs Expressions

## Overview
Java doesn't have lvalue/rvalue in the C/C++ sense, but has similar concepts:
- **Assignment Targets (lvalue-like)**: Variables and references that can be assigned to.
- **Expressions (rvalue-like)**: Computed values and temporaries that cannot be assigned to.
- **No Value (void)**: Methods/expressions that return no value.

---

## 1. Assignment Targets (lvalue-like)
- Variables that can appear on the **left side** of an assignment.
- Must be a valid storage location that persists beyond the expression.

### Examples:
```java
int x;                    // variable
x = 5;                    // x is assignment target

int[] arr = {1, 2, 3};
arr[0] = 10;              // array element is assignment target

class Person { int age; }
Person p = new Person();
p.age = 25;               // object member is assignment target

int a = 5, b = 3;
a = b;                    // both a and b are assignment targets
```

---

## 2. Expressions (rvalue-like)
- Computed or temporary values that **cannot** appear on the left side of assignment.
- Result of operations, method calls, or literals.
- Can be assigned **to** a variable but not **from**.

### Examples:
```java
int a = 5;
int b = a + 3;            // `a + 3` is expression (cannot assign to it)

// INVALID: 
// (a + 3) = 2;           // COMPILE ERROR: left side must be variable

String msg = "hello".toUpperCase();  // method result is expression

int square(int x) { return x * x; }
int result = square(4);   // `square(4)` is expression

// INVALID:
// square(4) = 10;        // COMPILE ERROR: method result not assignable
```

### Common expression types:
- Literals: `42`, `3.14`, `"hello"`, `true`
- Arithmetic results: `a + b`, `x * 4`, `a / b`
- Method returns: `str.length()`, `list.get(0)`
- Casts: `(int) 3.14`
- Lambda expressions: `x -> x * 2`

---

## 3. No Value (void)
- Methods that return `void` produce no value.
- Cannot be assigned or used in expressions.

### Examples:
```java
void sayHello() { 
    System.out.println("Hello"); 
}

// INVALID:
// String msg = sayHello();    // COMPILE ERROR: void cannot be assigned

// VALID - just call it (discard result):
sayHello();                     // valid, result is discarded
```

---

## 4. Key Differences from C/C++

| Aspect | C/C++ | Java |
|--------|-------|------|
| Lvalue/Rvalue | Explicit, strict separation | Implicit via assignment context |
| References | Pointers and references | Only object references (always indirect) |
| Temporary lifetime | Short, can be managed | Managed by garbage collector |
| Move semantics | `std::move()` and rvalue refs | No explicit move (but references are always indirect) |
| Address operator | `&` available | No `&` operator; references auto-managed |

---

## 5. Practical Rules in Java

1. **Left side of assignment**: Must be a variable, array element, or object member.
2. **Right side of assignment**: Can be any expression (variable, literal, computation, method call).
3. **Method parameters**: Pass-by-value (primitives) or pass-by-reference (objects).
4. **void methods**: Useful for side effects (I/O, state changes), not for value return.

### Examples:
```java
int x = 5;
x = x + 3;                // valid: x is lvalue-like, x+3 is rvalue-like

int[] arr = new int[5];
arr[0] = arr[1];          // valid: arr[0] and arr[1] are both assignable

// INVALID:
// x + 3 = 5;             // left side must be assignable location
// 42 = x;                // literal cannot be assignment target
```

---

## 6. Summary

| Category | Can appear on left of `=` | Can appear on right of `=` | Has persistent location |
|----------|---------------------------|-----------------------------|------------------------|
| Variable | ✓ Yes | ✓ Yes | ✓ Yes |
| Array element | ✓ Yes | ✓ Yes | ✓ Yes |
| Object member | ✓ Yes | ✓ Yes | ✓ Yes |
| Literal/Computed value | ✗ No | ✓ Yes | ✗ No |
| Method return | ✗ No | ✓ Yes | ✗ No (if not void) |
| void method | ✗ No | ✗ No | N/A |

---

## Key Takeaway
In Java, think in terms of **assignable locations** (variables, fields, array elements) vs **expressions** (computed values, method results). This is Java's way of managing the lvalue/rvalue concept without explicit syntax.

# Word Analogies – ATU 2026

![Status](https://img.shields.io/badge/status-Completed-brightgreen)
![Language](https://img.shields.io/badge/language-Java-blue)
![Java Version](https://img.shields.io/badge/Java-21-orange)
![Concurrency](https://img.shields.io/badge/concurrency-Virtual%20Threads-purple)
![Algorithm](https://img.shields.io/badge/algorithm-Cosine%20Similarity-blue)
![Type](https://img.shields.io/badge/type-CLI%20Application-lightgrey)
![Course](https://img.shields.io/badge/Project-ATU%20Assignment-lightgrey)

--- 

## Overview

This project is a Java command-line application that performs word analogy operations using vector arithmetic on word embeddings.

> The system computes a resulting vector and finds the most semantically similar words using cosine similarity. The application supports virtual threads (Java 21) to improve performance when searching large embedding datasets.

---

## Key Features

- Load word embeddings from a file
- Perform vector arithmetic (+ and - operations)
- Compute similarity using cosine similarity
- Return top N closest matching words
- Configurable settings (Top N results, threading mode)
- Output results to a user-defined file
- Uses virtual threads for concurrent processing
- Displays execution time for performance comparison

---

## Technologies Used

- Java 21
- Virtual Threads
- Java Collections (ConcurrentHashMap, List, etc.)
- File I/O (BufferedReader, FileWriter)
- Command Line Interface (CLI)

---

## How to Run

1. Compile or use the provided JAR file:
```bash
java -cp analogies.jar ie.atu.sw.Runner
```
2. Follow the menu options:
```bash
(1) Load embeddings file
(2) Enter vector operation
(3) Configure options
(4) Set output file
```
3. Example input:
```bash
king - man + woman
```

---

## Testing & Validation

The application was tested using:
- Provided embedding datasets
- Various analogy inputs (e.g. king - man + woman)
- Edge cases:
 - Missing words
 - Invalid input format
 - Empty file paths
- Performance was validated by comparing:
 - Sequential execution
 - Virtual thread execution

---

## Skills Demonstrated

- Object-Oriented Design (modular class structure)
- Data Structures (HashMaps for fast lookup)
- Algorithm Design (vector arithmetic & similarity search)
- Concurrency using virtual threads
- File handling and parsing
- CLI application design
- Performance measurement and optimisation

---

## Project Requirements

This project satisfies the following requirements:
- Command-line interface for user interaction
- Load and process word embeddings
- Perform vector arithmetic operations
- Compute similarity scores and return top N results
- Output results to a file
- Fully threaded implementation using virtual threads
- JavaDoc documentation with Big-O analysis
- UML class diagram provided

---

## Project Structure

```bash
src/
 └── ie/atu/sw/
     ├── Runner.java
     ├── Menu.java
     ├── EmbeddingLoader.java
     ├── VectorCalculator.java
     ├── SimilarityCalculator.java
     ├── AnalogyFinder.java
     ├── ResultWriter.java
     ├── ProgressMeter.java
     └── ConsoleColour.java
```

---

## Learning Outcomes

- Understanding of word embeddings and vector representations
- Implementation of cosine similarity
- Practical use of Java concurrency (virtual threads)
- Improved understanding of algorithm complexity (Big-O)
- Experience building a scalable CLI application

---

## Author

- Robert Nolan
- ATU Student - Software Development
- Module: Software Design and Data Structures

---

## Disclaimer

This project was created for academic purposes as part of coursework.
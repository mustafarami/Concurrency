# Learning Concurrency in Java

This project demonstrates how to use Java's ExecutorService and Future to calculate the number of prime numbers up to a certain number in a concurrent way. We distribute the tasks to an ExecutorService that manages a fixed thread pool, and retrieve results asynchronously using Future objects.

# Implementation

The implementation revolves around the PrimeCalculator class and the Main class.

## PrimeCalculator 

'PrimeCalculator' implements the 'Callable \<Integer\>' interface. It is designed to calculate the number of prime numbers up to a certain number specified at the creation of the 'PrimeCalculator' object.

When an 'ExecutorService' executes a 'PrimeCalculator' task, it will count the number of prime numbers up to the specified number and return the result as an 'Integer'.

## Main

The Main class is where we create our ExecutorService with a fixed thread pool of four threads, and submit PrimeCalculator tasks to it.

We submit 10 tasks, each calculating the number of prime numbers up to (i+1) * 50000, where i is the task number (from 0 to 9).

We keep track of the Future<Integer> objects returned by ExecutorService.submit in a list. We can then check if any tasks are still running, wait until all tasks are done, retrieve the results, and calculate the total number of prime numbers.

Finally, we call ExecutorService.shutdown to tell the executor service that it can release all resources because no more tasks will be submitted to it.


## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

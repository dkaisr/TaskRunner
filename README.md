# TaskRunner
A simple task runner framework and example implementation in Java

The goal of this project is to provide a clean, flexible framework for exploring different task processing strategies.

## Architecture
A TaskRunner takes Tasks into its queue and executes them with a single runAll() call.
There are two demo TaskRunners: 
- The SerialTaskRunner executes all tasks in sequence
- The ConcurrentTaskRunner executes multiple tasks at once
Each Task has a unique id, a status, as well as data and a strategy to process this data.
It returns a result on success or an Exception on failure.
Currently there's only the demo task StringReverseTask which executes the StringReverseStrategy on its data.


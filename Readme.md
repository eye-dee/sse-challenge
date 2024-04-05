# SSE Challenge

## Input data

. `RawSseEndpoint` produces [Server-Side-Events](https://html5doctor.com/server-sent-events/) in json format
. Each event is serialized `RawSseResponse`

## Output

. `PersonEndpoint` produces SSE in json format
. Each event is serialized `Person`

## Exercise

. Each `RawSseResponse` has `data` field inside
. Each `data` is a part of serialized list of `Person`
. Concatenate `data` from coming events and produce SSE event as response whenever parts received from raw sse form full json object
. Implementation should be done using kotlin coroutines and flows

## Example

* There are 2 raw sse events with the following data accordingly as input
    * First
      ```json
          {
        "data": "{\"name\": \"name\", \"age\": 12, \"email\": \"email\","
      }
      ```
    * Second
      ```json
              {"data": "\"address\": \"address\"}"}
      ```
* One SSE event is output:
  
  ```json
  {"name": "name", "age": "age", "email": "email", "address": "address"}
  ```
  
## Entry Point

For the testing please use `PersonApiTest`

The most important requirement:
. HTTP client **MUST receive SSE events** as long as json-string/bytes form valid person object.
So even if the test passes, that's not checking all requirements of the exercise

Good luck and have fun!
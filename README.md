Для отправки POST запроса:
* curl -d '{"packageId":"11", "jsScript":"var divideFn = function(a,b) { return a/b} ", "functionName":"divideFn", "tests": [ {"testName":"test1", "expectedResult":"2.0", "params":[2,1] }, {"testName":"test2", "expectedResult":"2.0", "params":[4,2]}]}' -H "Content-Type: application/json" -X POST http://localhost:8080

Для отправки GET запроса:
* curl -X GET 'http://localhost:8080/?packageId=11'

Пример работы программы:
* {"test2":true,"test1":true}

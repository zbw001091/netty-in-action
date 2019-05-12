[echoserver.http]
1) an http server, start the server, listen at 8080
2) postman, http://localhost:8080/test, method POST, post body:{"name":"zbw"}
3) at server side, first HttpRequestDecoder, second HttpObjectAggregator(), third MyCustomHandler

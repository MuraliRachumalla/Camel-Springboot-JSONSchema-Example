# API to post an item price
End Point: /api/post-price
port: 9091

Ex: End Point: http://localhost:9091/api/post-price

###Request: 
{
	"id":1234,
	"name":"testItem",
	"price":3.8
}

##Response
success: "Received Item price", 200
Failure: "Json Schema validation failed", 400
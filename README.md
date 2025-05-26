# kafka-order-processing-application-appss


## Project Architechture


![image](https://github.com/user-attachments/assets/14e9fe6c-d7a9-448e-bf9d-f6fc6da66658)



CUSTOMER_SERVICE

POST:

curl -v -H "Host: gateway" http://ingress-nginx-controller.ingress-nginx.svc.cluster.local/api/v1/customers -H "Content-Type: application/json" -d "{\"firstname\": \"Amir\", \"lastname\": \"Khan\", \"email\": \"amir.456@gmail.com\", \"address\": {\"street\": \"A Block\", \"houseNumber\": \"24/23\", \"zipCode\": \"122021\"}}"


* Host ingress-nginx-controller.ingress-nginx.svc.cluster.local:80 was resolved.
* IPv6: (none)
* IPv4: 10.109.87.17
*   Trying 10.109.87.17:80...
* Connected to ingress-nginx-controller.ingress-nginx.svc.cluster.local (10.109.87.17) port 80
> POST /api/v1/customers HTTP/1.1
> Host: gateway
> User-Agent: curl/8.5.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 151
>
< HTTP/1.1 201
< Date: Sun, 25 May 2025 18:57:30 GMT
< Content-Type: application/json
< Transfer-Encoding: chunked
< Connection: keep-alive
<
* Connection #0 to host ingress-nginx-controller.ingress-nginx.svc.cluster.local left intact
{"id":2,"firstname":"Amir","lastname":"Khan","email":"amir.456@gmail.com","address":{"street":"A Block","houseNumber":"24/23","zipCode":"122021"}}






DATABASE:

 


ORDER_SERVICE:

POST:

# curl -v -H "Host: gateway" http://ingress-nginx-controller.ingress-nginx.svc.cluster.local/api/v1/orders \
  -H "Content-Type: application/json" \
  -d '{
    "reference": "TM-20250510",
    "amount": 1099.00,
    "paymentMethod": "CREDIT_CARD",
    "customerId": 2,
    "products": [
      {
        "productId": 4,
        "quantity": 1
      }]}'
> > > > > > > > > > > * Host ingress-nginx-controller.ingress-nginx.svc.cluster.local:80 was resolved.
* IPv6: (none)
* IPv4: 10.109.87.17
*   Trying 10.109.87.17:80...
* Connected to ingress-nginx-controller.ingress-nginx.svc.cluster.local (10.109.87.17) port 80
> POST /api/v1/orders HTTP/1.1
> Host: gateway
> User-Agent: curl/8.5.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 195
>
< HTTP/1.1 201 
< Date: Sun, 25 May 2025 20:38:53 GMT
< Content-Type: application/json
< Transfer-Encoding: chunked
< Connection: keep-alive
<
* Connection #0 to host ingress-nginx-controller.ingress-nginx.svc.cluster.local left intact
{"id":null,"reference":"TM-20250510","amount":1099.00,"paymentMethod":"CREDIT_CARD","customerId":2,"products":[{"productId":4,"quantity":1.0}]}


DATABASES:
Customer_order

 

Customer_order_line

Information of products that has been ordered with number of quantities.

 
PRODUCT_SERVICE

Note: Once the Order has been completed the Product Table will get updated with latest available quantities.

Before Order the Product Tabel
	The product id : 4 has available_quanity : 199 for the product T-Shirt.

 


After Order Confirmation the Product Table
	The product id : 4 has available_quanity : 198 for the product T-Shirt.

 




PAYMENT_SERVICE

DATABASE: 

	The the Payment Table is updated as expected for id 155.

 


NOTIFICATION_SERVICE

The customer will be notified about his/her order once the order has been placed.

DATABASE

 

[
  {
    "id": 7,
    "type": "PAYMENT_CONFIRMATION",
    "amount": 1099.00,
    "total_amount": null,
    "customer_email": "amir.456@gmail.com",
    "payment_method": "CREDIT_CARD",
    "order_reference": "TM-20250510",
    "customer_lastname": "Khan",
    "notification_date": "2025-05-25 20:38:53.099526",
    "customer_firstname": "Amir"
  },
  {
    "id": 8,
    "type": "ORDER_CONFIRMATION",
    "amount": 1099.00,
    "total_amount": 1099.00,
    "customer_email": "amir.456@gmail.com",
    "payment_method": "CREDIT_CARD",
    "order_reference": "TM-20250510",
    "customer_lastname": "Khan",
    "notification_date": "2025-05-25 20:38:53.099522",
    "customer_firstname": "Amir"
  }
]


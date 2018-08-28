# job-processor
A MultiThreaded JobProcessor To download Content of Website Parallely


Use Case:
 Build a System which will be able to Download the content/resource of the List of Urls provided in the request.

Functional Requirements:
1. User should be able to download the resource of the provided URLs
2. User should get a JobId to upon submitting the request with valid URLs.
3. User should be able to track the progress of job by providing the valid JobId.

Non Functional Requirements:
1. System should be able to parallely process the Data of each URL.
2. System should maintain data consistency while updating the job status.

Assumptions:
To make the system simple,This application keeps all the data inmemory.

EndPoints:

API 1: 
  To Post Data containg list of URls

curl -X POST \
  http://localhost:8080/job/submit \
  -H 'accept: application/json' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 6af72340-cfce-db7b-dafe-407a88a50740' \
  -d '{
      "urlList": ["http://www.oracle.com","http://www.google.com"]
}'
API 2:
  To Track the status of a given JobId

curl -X GET \
  http://localhost:8080/job/track/JOB_2 \
  -H 'accept: application/json' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: f8359b84-c1c7-dcfb-9d6a-c88afadcfd8c' \
 

const http = require('http')
const server = http.createServer((request, response) => {
  response.writeHead(200, {'Content-Type': 'text/plain'});
  response.end ("helloworld!");
});

server.listen(8000);


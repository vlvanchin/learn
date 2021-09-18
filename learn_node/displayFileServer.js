const http=require('http')
const fs = require('fs')

let server = http.createServer((request, response) => {
  response.writeHead(200, {'Content-Type':'text/html'});
  fs.readFile('./output',(err,file) => {
    response.end(file);
  });
});

server.listen(8000);

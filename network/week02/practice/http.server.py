from http.server import BaseHTTPRequestHandler, HTTPServer

class HandleRequests(BaseHTTPRequestHandler):
    def _set_headers(self):
        self.send_response(200)
        self.send_header('Content-type', 'text/html')
        self.end_headers()

    def do_GET(self):
        self._set_headers()
        self.wfile.write('received get request'.encode('utf-8'))

    def do_POST(self):
        self._set_headers()
        content_len = int(self.headers['Content-Length'])
        post_body = self.rfile.read(content_len)
        self.wfile.write("received post request:<br>{}".format(post_body).encode('utf-8'))

host = ''
port = 80
HTTPServer((host, port), HandleRequests).serve_forever()

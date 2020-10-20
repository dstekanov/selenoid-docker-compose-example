thanks to https://github.com/eroshenkoam/docker-example

1. Pull selenoid browser images (see versions inside init/selenoid/browser.json) e.g. docker pull selenoid/vnc:chrome_86.0
2. Run command: docker-compose up -d
3. Look logs inside docker: docker-compose logs -f selenoid
4. Run the test

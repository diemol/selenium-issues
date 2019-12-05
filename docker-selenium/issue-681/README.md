## Example on how to build an image based on docker-selenium to run a couple of tests.

### Build

```bash
docker build -t issue-681 .
```

### Run

```bash
docker run --rm -it issue-681
```


### Short explanation about the CMD

`./start-virtual-desktop.sh` will start XVfb, which is need to start the browser. If the browser runs headless, 
this is not needed. After this, the `mocha` command is executed, and the test runs.
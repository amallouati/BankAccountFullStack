"use strict";

const express = require("express");
const fs = require("fs");
const path = require("path");
const bodyParser = require("body-parser");

const app = express();

const config = {
  port: 3000,
};

const cors = () => {
  return (request, response, next) => {
    response.header("Access-Control-Allow-Origin", "*");
    response.header(
      "Access-Control-Allow-Methods",
      "GET,PUT,POST,DELETE,OPTIONS,PATCH"
    );
    response.header(
      "Access-Control-Allow-Headers",
      "Content-Type, Authorization, Content-Length, X-Requested-With"
    );

    //Handle Preflight
    if (request.method === "OPTIONS") {
      response.status(200).send();
    } else {
      next();
    }
  };
};

app.set("config", config);

app.use(bodyParser.json()); // for parsing application/json
app.use(bodyParser.urlencoded({ extended: true })); // for parsing application/x-www-form-urlencoded
app.use(cors());

// Just use a simple json with name as configuration
// const files = fs.readdirSync('./server/mocks');
// files.forEach(file => {
//   const [, verb, route] = file.match(/([^_]+)_(.+)\.json/);
//   app[verb.toLowerCase()]('/api/' + route, (req, res) => res.send(require('./mocks/' + file)));
// });

// For others case, create your own route with express
const apiFiles = fs.readdirSync("./server/api");
apiFiles.forEach((file) => {
  require("./api/" + file)(app);
});

if (!module.parent) {
  // Allow the launcher to start the servers itself when run directly
  // `node server.js` case.
  start(app, config);

  // Handle graceful shutdown
  process.on("message", (msg) => {
    // We only handle shutdown messages
    if (msg !== "shutdown") {
      return;
    }

    stop(server);
  });
} else {
  exports.app = app;
  exports.config = config;
  exports.start = start;
  exports.stop = stop;
}

function start(app, config) {
  return app.listen(config.port, () => {
    console.log(`Listening on port ${config.port} - ${app.settings.env} mode`);
  });
}

function stop(server) {
  console.log("Server is going down... 1s before force shutdown.");
  server.close();

  setTimeout(() => {
    console.log("Forcing shutdown...");
    process.exit(0);
  }, 1000);
}

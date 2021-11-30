"use strict";

var fs = require("fs");

Number.prototype.round = function (places) {
  return +(Math.round(this + "e+" + places) + "e-" + places);
};

module.exports = (app) => {
  const eurFile = "mock/EUR.json";
  const symbolsFile = "mock/symbols.json";

  // https://exchangeratesapi.io/documentation/#supportedsymbols
  app.get("/api/symbols", function (req, res) {
    fs.readFile(symbolsFile, function (err, data) {
      if (err) {
        res.sendStatus(500);
        console.log(err);
        return;
      }

      const symbols = JSON.parse(data).symbols;
      const response = {
        success: true,
        symbols,
      };
      setTimeout(function () {
        res.send(response);
      }, 500);
    });
  });

  // https://exchangeratesapi.io/documentation/#latestrates
  app.get("/api/latest", function (req, res) {
    getRates(req, res, null);
  });

  // https://exchangeratesapi.io/documentation/#historicalrates
  app.get("/api/:date", function (req, res) {
    const date = new Date(req.params.date);
    if (isNaN(date.getTime())) {
      res.sendStatus(400);
      console.log(`${req.params.date} is not a valid date`);
      return;
    } else {
      getRates(req, res, date);
    }
  });

  function getRates(req, res, date) {
    fs.readFile(eurFile, function (err, data) {
      if (err) {
        res.sendStatus(500);
        console.log(err);
        return;
      }

      const base = req.query.base || "EUR";
      const ratesEUR = JSON.parse(data).rates;

      const newBase = ratesEUR[base];
      if (!newBase) {
        res.sendStatus(400);
        console.log(`${base} does not exist`);
        return;
      }

      const rates = {};
      for (const [key, rate] of Object.entries(ratesEUR)) {
        let newRate = rate / newBase;
        if (date && key !== base) {
          // Apply a variation within +/- 5% on historical rates
          const variation = (Math.random() - 0.5) / 10 + 1;
          newRate = newRate * variation;
        }
        rates[key] = newRate.round(4);
      }

      if (!date) {
        date = new Date();
      }

      const response = {
        success: true,
        timestamp: Date.now(),
        base,
        date: date.toISOString().split("T")[0],
        rates,
      };
      setTimeout(function () {
        res.send(response);
      }, 500);
    });
  }
};

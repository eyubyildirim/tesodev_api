const ErrorResponse = require("../utils/errorResponse");

const errorHandler = (err, req, res, next) => {
  let error = null;
  let errors = [];
  var uuidRegex =
    /^[0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12}$/i;

  // Log console for dev
  console.log(err);

  if (err.errors) {
    errors = err.errors;
  }

  // Object with id not found
  if (err.statusCode === 404) {
    if (err.value != 0) {
      if (uuidRegex.test(err.value)) {
        error = new ErrorResponse(
          `There is no customer with id ${err.value}`,
          404,
          err.value
        );
      } else {
        error = new ErrorResponse(`${err.value} is not a valid UUID`, 404);
      }
    } else {
      error = new ErrorResponse(`Invalid id`, 404);
    }
  }

  for (const [key, value] of Object.entries(errors)) {
    if (error) {
      if (key === "name") {
        error = new ErrorResponse(`${error.message}, name`, 400);
      } else if (key === "email") {
        if (errors[key].kind === "required") {
          error = new ErrorResponse(`${error.message}, email`, 400);
        } else {
          error = new ErrorResponse(`${error.message}, valid email`, 400);
        }
      } else if (key === "address.cityCode") {
        error = new ErrorResponse(`${error.message}, city code`, 400);
      } else if (key === "address.city") {
        error = new ErrorResponse(`${error.message}, city`, 400);
      } else if (key === "address.country") {
        error = new ErrorResponse(`${error.message}, country`, 400);
      }
    } else {
      if (key === "name") {
        error = new ErrorResponse(`Please provide a name`, 400);
      } else if (key === "email") {
        if (errors[key].kind === "required") {
          error = new ErrorResponse(`Please provide an email`, 400);
        } else {
          error = new ErrorResponse(`Please provide a valid email`, 400);
        }
      } else if (key === "address.cityCode") {
        error = new ErrorResponse(`Please provide a city code`, 400);
      } else if (key === "address.city") {
        error = new ErrorResponse(`Please provide a city`, 400);
      } else if (key === "address.country") {
        error = new ErrorResponse(`Please provide a country`, 400);
      }
    }
  }

  if (err.code) {
    if (err.code === 11000) {
      error = new ErrorResponse(
        `There is already a customer with email address: ${err.keyValue.email}`,
        400
      );
    } else if (err.code === 66) {
      error = new ErrorResponse(
        `You cannot change the field: ${err.message}`,
        400
      );
    }
  }

  if (!error) {
    error = { statusCode: err.statusCode, message: err.message };
  }

  res.status(error.statusCode || 500).json({
    success: false,
    error: error.message || "Server Error",
  });
};

module.exports = errorHandler;

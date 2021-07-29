const Customer = require("../models/Customer");
const ErrorResponse = require("../utils/errorResponse");
const asyncHandler = require("../middleware/async");
const { v4: uuidv4 } = require("uuid");
const url = require("url");

// @desc    Get all customers
// @route   GET /api/v1/customers
exports.getCustomers = asyncHandler(async (req, res, next) => {
  const customers = await Customer.find();

  res.status(200).json({ success: true, data: customers });
});

// @desc    Get a customer
// @route   GET /api/v1/customers?id=
exports.getCustomer = asyncHandler(async (req, res, next) => {
  const customer = await Customer.findById(req.query.id);

  if (!customer) {
    return next(
      new ErrorResponse(
        `Customer not found with the ID: ${req.query.id}`,
        404,
        req.query.id
      )
    );
  }

  res.status(200).json({ success: true, data: customer });
});

// @desc    Create a customer
// @route   POST /api/v1/customers
exports.createCustomer = asyncHandler(async (req, res, next) => {
  req.body._id = uuidv4();
  date = new Date().getTime();
  req.body.updatedAt = date;
  req.body.createdAt = date;

  const customer = await Customer.create(req.body);
  res.status(201).json({ success: true, data: customer });
});

// @desc    Update a customer
// @route   PUT /api/v1/customers?id=
exports.updateCustomer = asyncHandler(async (req, res, next) => {
  if (!req.query.id) {
    return next(new ErrorResponse(`Please provide an id`, 400));
  }

  req.body.updatedAt = new Date().getTime();
  const customer = await Customer.findByIdAndUpdate(req.query.id, req.body, {
    new: true,
    runValidators: true,
  });

  if (!customer) {
    return next(
      new ErrorResponse(
        `Customer not found with the ID: ${req.query.id}`,
        404,
        req.query.id
      )
    );
  }

  res.status(200).json({ success: true, data: customer });
});

// @desc    Delete a customer
// @route   DELETE /api/v1/customers?id=
exports.deleteCustomer = asyncHandler(async (req, res, next) => {
  req.body.updatedAt = new Date().getTime();
  const customer = await Customer.findByIdAndDelete(req.query.id);

  if (!customer) {
    return next(
      new ErrorResponse(
        `Customer not found with the ID: ${req.query.id}`,
        404,
        req.query.id
      )
    );
  }

  res.status(200).json({ success: true, data: {} });
});

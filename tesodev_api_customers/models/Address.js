const mongoose = require("mongoose");

const AddressSchema = new mongoose.Schema({
  addressLine: String,
  city: {
    type: String,
    required: true,
  },
  country: {
    type: String,
    required: true,
  },
  cityCode: {
    type: Number,
    required: true,
  },
});

module.exports = AddressSchema;

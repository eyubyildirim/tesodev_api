const mongoose = require("mongoose");
const AddressSchema = require("./Address");

const CustomerSchema = new mongoose.Schema({
  _id: String,
  name: {
    type: String,
    required: true,
  },
  email: {
    type: String,
    required: true,
    unique: true,
    match: [
      /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/,
      "Please add a valid email",
    ],
  },
  address: AddressSchema,
  // {
  //   addressLine: String,
  //   city: {
  //     type: String,
  //     required: true,
  //   },
  //   country: {
  //     type: String,
  //     required: true,
  //   },
  //   cityCode: {
  //     type: Number,
  //     required: true,
  //   },
  // },
  updatedAt: Number,
  createdAt: Number,
});

module.exports = mongoose.model("Customer", CustomerSchema);

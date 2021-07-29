const express = require("express");
const {
  createCustomer,
  deleteCustomer,
  getCustomer,
  getCustomers,
  updateCustomer,
} = require("../controllers/customers");

const router = express.Router();

// router.route("/").get(getCustomers).post(createCustomer);

// router
//   .route("/:id")
//   .delete(deleteCustomer)
//   .put(updateCustomer)
//   .get(getCustomer);

router.route("/get-all-customers").get(getCustomers);
router.route("/create-customer").post(createCustomer);
router.route("/get-customer").get(getCustomer);
router.route("/delete-customer").delete(deleteCustomer);
router.route("/update-customer").put(updateCustomer);

module.exports = router;

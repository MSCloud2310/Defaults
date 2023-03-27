package com.defaults.marketplace.msorders.models;

public enum PaymentMethod {
	CREDIT_CARD {
		@Override
		public String toString() {
			return "CC";
		}
	},
	DEBIT_CARD {
		@Override
		public String toString() {
			return "DC";
		}
	}
}

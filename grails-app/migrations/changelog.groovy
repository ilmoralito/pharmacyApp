databaseChangeLog = {

	changeSet(author: "sherlock (generated)", id: "1466744234073-1") {
		createTable(tableName: "brand") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "brandPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-2") {
		createTable(tableName: "brand_details") {
			column(name: "detail_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "brand_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "details_idx", type: "integer")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-3") {
		createTable(tableName: "brand_product") {
			column(name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "brand_productPK")
			}
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-4") {
		createTable(tableName: "brand_product_brands") {
			column(name: "brand_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "brand_product_id", type: "bigint")

			column(name: "brands_idx", type: "integer")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-5") {
		createTable(tableName: "client") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "clientPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "address", type: "varchar(1000)")

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar(255)")

			column(name: "enabled", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "full_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "telephone_number", type: "varchar(255)")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-6") {
		createTable(tableName: "company") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "companyPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "address", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "city", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "contact_email", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "contact_full_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "contact_telephone_number", type: "varchar(8)") {
				constraints(nullable: "false")
			}

			column(name: "credit_limit", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "telephone_number", type: "varchar(8)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-7") {
		createTable(tableName: "configuration") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "configurationPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "min_low_stocks", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "min_medicine_orders_about_to_expire", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "min_purchase_orders_payment_date_close", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-8") {
		createTable(tableName: "contact") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "contactPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "full_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "telephone_number", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-9") {
		createTable(tableName: "detail") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "detailPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-10") {
		createTable(tableName: "distributor") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "distributorPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "contact_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "days_to_pay", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "telephone_number", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-11") {
		createTable(tableName: "distributor_providers") {
			column(name: "distributor_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "provider_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "providers_idx", type: "integer")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-12") {
		createTable(tableName: "employee") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "employeePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "company_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "full_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "identification_card", type: "varchar(16)") {
				constraints(nullable: "false")
			}

			column(name: "inss", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "telephone_number", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "employees_idx", type: "integer")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-13") {
		createTable(tableName: "expense") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "expensePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "quantity", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-14") {
		createTable(tableName: "item") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "itemPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "fixed_quantity", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "product_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "purchase_order_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "purchase_price", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "quantity", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "selling_price", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "class", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "due_date", type: "timestamp")

			column(name: "measure_id", type: "bigint")

			column(name: "presentation_id", type: "bigint")

			column(name: "brand_id", type: "bigint")

			column(name: "detail_id", type: "bigint")

			column(name: "items_idx", type: "integer")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-15") {
		createTable(tableName: "measure") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "measurePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-16") {
		createTable(tableName: "medicine") {
			column(name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "medicinePK")
			}

			column(name: "code", type: "varchar(255)")

			column(name: "generic_name", type: "varchar(255)")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-17") {
		createTable(tableName: "medicine_presentations") {
			column(name: "presentation_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "medicine_id", type: "bigint")

			column(name: "presentations_idx", type: "integer")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-18") {
		createTable(tableName: "payment") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "paymentPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "amount", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "attended_by_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "credit_sale_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "made_by", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "made_by_identity_card", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "receipt_number", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "reference", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "payments_idx", type: "integer")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-19") {
		createTable(tableName: "presentation") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "presentationPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-20") {
		createTable(tableName: "presentation_measures") {
			column(name: "presentation_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "measure_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "measures_idx", type: "integer")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-21") {
		createTable(tableName: "product") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "productPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "provider_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "products_idx", type: "integer")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-22") {
		createTable(tableName: "provider") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "providerPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "address", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "telephone_number", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-23") {
		createTable(tableName: "purchase_order") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "purchase_ordePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "distributor_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "invoice_number", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "payment_date", type: "timestamp")

			column(name: "payment_status", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "payment_type", type: "varchar(100)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-24") {
		createTable(tableName: "registration_code") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "registration_PK")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "token", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-25") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rolePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-26") {
		createTable(tableName: "sale") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "salePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "annotation", type: "varchar(255)")

			column(name: "balance", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "canceled", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "date_of_cancelation", type: "timestamp")

			column(name: "last_updated", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "reazon_of_canelation", type: "varchar(255)")

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "class", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "employee_id", type: "bigint")

			column(name: "invoice_number", type: "varchar(255)")

			column(name: "paid_out", type: "boolean")

			column(name: "client_id", type: "bigint")

			column(name: "money_received", type: "decimal(19,2)")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-27") {
		createTable(tableName: "sale_detail") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sale_detailPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "item_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "quantity", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "sale_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "total", type: "decimal(19,2)") {
				constraints(nullable: "false")
			}

			column(name: "sale_details_idx", type: "integer")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-28") {
		createTable(tableName: "user") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "userPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "full_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "telephone_number", type: "varchar(8)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-29") {
		createTable(tableName: "user_role") {
			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-30") {
		addPrimaryKey(columnNames: "role_id, user_id", constraintName: "user_rolePK", tableName: "user_role")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-64") {
		createIndex(indexName: "name_uniq_1466744233825", tableName: "brand", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-65") {
		createIndex(indexName: "email_uniq_1466744233850", tableName: "client", unique: "true") {
			column(name: "email")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-66") {
		createIndex(indexName: "address_uniq_1466744233851", tableName: "company", unique: "true") {
			column(name: "address")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-67") {
		createIndex(indexName: "contact_email_uniq_1466744233852", tableName: "company", unique: "true") {
			column(name: "contact_email")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-68") {
		createIndex(indexName: "contact_telephone_number_uniq_1466744233852", tableName: "company", unique: "true") {
			column(name: "contact_telephone_number")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-69") {
		createIndex(indexName: "name_uniq_1466744233854", tableName: "company", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-70") {
		createIndex(indexName: "telephone_number_uniq_1466744233854", tableName: "company", unique: "true") {
			column(name: "telephone_number")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-71") {
		createIndex(indexName: "email_uniq_1466744233856", tableName: "contact", unique: "true") {
			column(name: "email")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-72") {
		createIndex(indexName: "telephone_number_uniq_1466744233856", tableName: "contact", unique: "true") {
			column(name: "telephone_number")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-73") {
		createIndex(indexName: "name_uniq_1466744233857", tableName: "detail", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-74") {
		createIndex(indexName: "name_uniq_1466744233859", tableName: "distributor", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-75") {
		createIndex(indexName: "telephone_number_uniq_1466744233859", tableName: "distributor", unique: "true") {
			column(name: "telephone_number")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-76") {
		createIndex(indexName: "identification_card_uniq_1466744233861", tableName: "employee", unique: "true") {
			column(name: "identification_card")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-77") {
		createIndex(indexName: "inss_uniq_1466744233862", tableName: "employee", unique: "true") {
			column(name: "inss")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-78") {
		createIndex(indexName: "name_uniq_1466744233869", tableName: "measure", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-79") {
		createIndex(indexName: "receipt_number_uniq_1466744233874", tableName: "payment", unique: "true") {
			column(name: "receipt_number")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-80") {
		createIndex(indexName: "name_uniq_1466744233877", tableName: "presentation", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-81") {
		createIndex(indexName: "unique_name", tableName: "product", unique: "true") {
			column(name: "provider_id")

			column(name: "name")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-82") {
		createIndex(indexName: "address_uniq_1466744233885", tableName: "provider", unique: "true") {
			column(name: "address")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-83") {
		createIndex(indexName: "name_uniq_1466744233885", tableName: "provider", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-84") {
		createIndex(indexName: "telephone_number_uniq_1466744233886", tableName: "provider", unique: "true") {
			column(name: "telephone_number")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-85") {
		createIndex(indexName: "invoice_number_uniq_1466744233887", tableName: "purchase_order", unique: "true") {
			column(name: "invoice_number")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-86") {
		createIndex(indexName: "authority_uniq_1466744233889", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-87") {
		createIndex(indexName: "invoice_number_uniq_1466744233892", tableName: "sale", unique: "true") {
			column(name: "invoice_number")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-88") {
		createIndex(indexName: "email_uniq_1466744233895", tableName: "user", unique: "true") {
			column(name: "email")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-89") {
		createIndex(indexName: "telephone_number_uniq_1466744233896", tableName: "user", unique: "true") {
			column(name: "telephone_number")
		}
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-31") {
		addForeignKeyConstraint(baseColumnNames: "brand_id", baseTableName: "brand_details", constraintName: "FKF19E6D8ABCCEAAE", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "brand", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-32") {
		addForeignKeyConstraint(baseColumnNames: "detail_id", baseTableName: "brand_details", constraintName: "FKF19E6D8A29D89446", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "detail", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-33") {
		addForeignKeyConstraint(baseColumnNames: "id", baseTableName: "brand_product", constraintName: "FK82534837DFAE8F5E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "product", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-34") {
		addForeignKeyConstraint(baseColumnNames: "brand_id", baseTableName: "brand_product_brands", constraintName: "FKBF6B7ED4BCCEAAE", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "brand", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-35") {
		addForeignKeyConstraint(baseColumnNames: "brand_product_id", baseTableName: "brand_product_brands", constraintName: "FKBF6B7ED451D20CB7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "brand_product", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-36") {
		addForeignKeyConstraint(baseColumnNames: "contact_id", baseTableName: "distributor", constraintName: "FK4F8A97E7332E330E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "contact", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-37") {
		addForeignKeyConstraint(baseColumnNames: "distributor_id", baseTableName: "distributor_providers", constraintName: "FKB174D64A7E2CFFAE", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "distributor", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-38") {
		addForeignKeyConstraint(baseColumnNames: "provider_id", baseTableName: "distributor_providers", constraintName: "FKB174D64A922AD546", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "provider", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-39") {
		addForeignKeyConstraint(baseColumnNames: "company_id", baseTableName: "employee", constraintName: "FK4722E6AEF84179EE", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "company", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-40") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "expense", constraintName: "FKB1F4C858D6A94506", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-41") {
		addForeignKeyConstraint(baseColumnNames: "brand_id", baseTableName: "item", constraintName: "FK317B13BCCEAAE", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "brand", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-42") {
		addForeignKeyConstraint(baseColumnNames: "detail_id", baseTableName: "item", constraintName: "FK317B1329D89446", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "detail", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-43") {
		addForeignKeyConstraint(baseColumnNames: "measure_id", baseTableName: "item", constraintName: "FK317B139A2EC24E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "measure", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-44") {
		addForeignKeyConstraint(baseColumnNames: "presentation_id", baseTableName: "item", constraintName: "FK317B135E1862A6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "presentation", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-45") {
		addForeignKeyConstraint(baseColumnNames: "product_id", baseTableName: "item", constraintName: "FK317B13482B4CAE", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "product", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-46") {
		addForeignKeyConstraint(baseColumnNames: "purchase_order_id", baseTableName: "item", constraintName: "FK317B13CE732E4B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "purchase_order", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-47") {
		addForeignKeyConstraint(baseColumnNames: "id", baseTableName: "medicine", constraintName: "FKCA50563ADFAE8F5E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "product", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-48") {
		addForeignKeyConstraint(baseColumnNames: "medicine_id", baseTableName: "medicine_presentations", constraintName: "FK68DA9F54972A04A6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "medicine", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-49") {
		addForeignKeyConstraint(baseColumnNames: "presentation_id", baseTableName: "medicine_presentations", constraintName: "FK68DA9F545E1862A6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "presentation", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-50") {
		addForeignKeyConstraint(baseColumnNames: "attended_by_id", baseTableName: "payment", constraintName: "FKD11C3206F259C9F4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-51") {
		addForeignKeyConstraint(baseColumnNames: "credit_sale_id", baseTableName: "payment", constraintName: "FKD11C3206A8F955D9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sale", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-52") {
		addForeignKeyConstraint(baseColumnNames: "measure_id", baseTableName: "presentation_measures", constraintName: "FKB405965A9A2EC24E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "measure", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-53") {
		addForeignKeyConstraint(baseColumnNames: "presentation_id", baseTableName: "presentation_measures", constraintName: "FKB405965A5E1862A6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "presentation", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-54") {
		addForeignKeyConstraint(baseColumnNames: "provider_id", baseTableName: "product", constraintName: "FKED8DCCEF922AD546", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "provider", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-55") {
		addForeignKeyConstraint(baseColumnNames: "distributor_id", baseTableName: "purchase_order", constraintName: "FK71A56A907E2CFFAE", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "distributor", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-56") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "purchase_order", constraintName: "FK71A56A90D6A94506", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-57") {
		addForeignKeyConstraint(baseColumnNames: "client_id", baseTableName: "sale", constraintName: "FK35C0472C9E2A06", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "client", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-58") {
		addForeignKeyConstraint(baseColumnNames: "employee_id", baseTableName: "sale", constraintName: "FK35C047CF94C026", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "employee", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-59") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "sale", constraintName: "FK35C047D6A94506", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-60") {
		addForeignKeyConstraint(baseColumnNames: "item_id", baseTableName: "sale_detail", constraintName: "FK4D1FC7495D8BA006", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "item", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-61") {
		addForeignKeyConstraint(baseColumnNames: "sale_id", baseTableName: "sale_detail", constraintName: "FK4D1FC7494E812386", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sale", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-62") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK143BF46A317E8126", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "sherlock (generated)", id: "1466744234073-63") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK143BF46AD6A94506", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}

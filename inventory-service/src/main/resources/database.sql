select column_name from information_schema.columns where table_schema = 'inventory_service' and table_name = 'inventory';
insert into inventory (id, sku_code, quantity) values (123, 'abc', 12);
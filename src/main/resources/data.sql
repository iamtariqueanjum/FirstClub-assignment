-- SILVER plans
INSERT INTO membership_plans (name, price, active, duration, tier) VALUES ('Silver Monthly', 99.00, true, 'MONTHLY', 'SILVER');
INSERT INTO membership_plans (name, price, active, duration, tier) VALUES ('Silver Quarterly', 299.00, true, 'QUARTERLY', 'SILVER');
INSERT INTO membership_plans (name, price, active, duration, tier) VALUES ('Silver Yearly', 999.00, true, 'YEARLY', 'SILVER');

-- GOLD plans
INSERT INTO membership_plans (name, price, active, duration, tier) VALUES ('Gold Monthly', 199.00, true, 'MONTHLY', 'GOLD');
INSERT INTO membership_plans (name, price, active, duration, tier) VALUES ('Gold Quarterly', 599.00, true, 'QUARTERLY', 'GOLD');
INSERT INTO membership_plans (name, price, active, duration, tier) VALUES ('Gold Yearly', 1999.00, true, 'YEARLY', 'GOLD');

-- PLATINUM plans
INSERT INTO membership_plans (name, price, active, duration, tier) VALUES ('Platinum Monthly', 399.00, true, 'MONTHLY', 'PLATINUM');
INSERT INTO membership_plans (name, price, active, duration, tier) VALUES ('Platinum Quarterly', 999.00, true, 'QUARTERLY', 'PLATINUM');
INSERT INTO membership_plans (name, price, active, duration, tier) VALUES ('Platinum Yearly', 3499.00, true, 'YEARLY', 'PLATINUM');

-- GOLD: 10+ orders OR monthly spend > 5000
INSERT INTO tier_criteria (tier, min_order_count, min_monthly_order_value, cohort, enabled)
VALUES ('GOLD', 10, null, null, true);

-- GOLD: monthly spend > 5000
INSERT INTO tier_criteria (tier, min_order_count, min_monthly_order_value, cohort, enabled)
VALUES ('GOLD', null, 5000.00, null, true);

-- PLATINUM: 25+ orders
INSERT INTO tier_criteria (tier, min_order_count, min_monthly_order_value, cohort, enabled)
VALUES ('PLATINUM', 25, null, null, true);

-- PLATINUM: monthly spend > 15000
INSERT INTO tier_criteria (tier, min_order_count, min_monthly_order_value, cohort, enabled)
VALUES ('PLATINUM', null, 15000.00, null, true);

-- PLATINUM:  VIP cohort
INSERT INTO tier_criteria (tier, min_order_count, min_monthly_order_value, cohort, enabled)
VALUES ('PLATINUM', null, null, 'VIP', true);
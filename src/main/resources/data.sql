INSERT INTO entitlement (id, created, entitlement_holder_id, quantity, company_id) VALUES
    ('1', parsedatetime('2020-01-12 18:47:52', 'yyyy-MM-dd hh:mm:ss'), '1', 1, '1'),
    ('2', parsedatetime('2020-01-12 18:47:52', 'yyyy-MM-dd hh:mm:ss'), '2', 2, '1')
    ;

INSERT INTO entitlement_entity (id, created, entitlement_holder_id, quantity, company_id) VALUES
    ('1', parsedatetime('2020-01-12 18:47:52', 'yyyy-MM-dd hh:mm:ss'), '1', 1, '1'),
    ('2', parsedatetime('2020-01-12 18:47:52', 'yyyy-MM-dd hh:mm:ss'), '2', 2, '1')
    ;

INSERT INTO entitlement_holder (id, created, user_id, company_id) VALUES
    ('1', parsedatetime('2020-01-12 18:47:52', 'yyyy-MM-dd hh:mm:ss'), '1', null),
    ('2', parsedatetime('2020-01-12 18:47:52', 'yyyy-MM-dd hh:mm:ss'), '2', null)
    ;

INSERT INTO company (id, created) VALUES
    ('1', parsedatetime('2020-01-12 18:47:52', 'yyyy-MM-dd hh:mm:ss'))
    ;

INSERT INTO user (id, created) VALUES
    ('1', parsedatetime('2020-01-12 18:47:52', 'yyyy-MM-dd hh:mm:ss')),
    ('2', parsedatetime('2020-01-12 18:47:52', 'yyyy-MM-dd hh:mm:ss'))
    ;
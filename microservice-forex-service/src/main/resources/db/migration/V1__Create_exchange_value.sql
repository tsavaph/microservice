CREATE TABLE exchange_value
(
    id                      BIGSERIAL       NOT NULL,
    currency_from           VARCHAR(255)    NOT NULL,
    currency_to             VARCHAR(255)    NOT NULL,
    year                    INT             NOT NULL,
    month                   INT             NOT NULL,
    conversion_multiple     BIGINT          NOT NULL,
    CONSTRAINT pk_access_key_config PRIMARY KEY (id)
);
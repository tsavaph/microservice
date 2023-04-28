CREATE TABLE exchange_value
(
    id                      BIGINT          NOT NULL,
    currency_from           VARCHAR(255)    NOT NULL,
    currency_to             VARCHAR(255)    NOT NULL,
    conversion_multiple     BIGINT          NOT NULL,
    port                    INT             NOT NULL,
    CONSTRAINT pk_access_key_config PRIMARY KEY (id)
);
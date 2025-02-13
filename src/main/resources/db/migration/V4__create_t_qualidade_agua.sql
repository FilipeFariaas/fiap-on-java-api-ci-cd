CREATE SEQUENCE T_QUALIDADE_AGUA_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE T_QUALIDADE_AGUA (
    ID INTEGER DEFAULT T_QUALIDADE_AGUA_SEQ.NEXTVAL NOT NULL,
    ATUALIZACAO DATE NOT NULL,
    QUALIDADE INTEGER NOT NULL,
    DESCRICAO VARCHAR2(20) NOT NULL
)

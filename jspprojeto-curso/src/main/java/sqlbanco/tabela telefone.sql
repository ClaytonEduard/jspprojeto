create table telefone(
	id integer not null default nextval('telefone_id_seq'::regclass),
	numero character varying(50)NOT NULL,
	usuario_pai_id bigint NOT NULL,
	usuario_cad_id bigint NOT NULL,
CONSTRAINT telefone_pkey PRIMARY KEY (id),
CONSTRAINT usuario_pai_fk FOREIGN KEY (usuario_pai_id)REFERENCES model_login (id),
 CONSTRAINT usuario_cad_fk FOREIGN KEY (usuario_cad_id)REFERENCES model_login (id)

)
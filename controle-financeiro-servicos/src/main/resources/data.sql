INSERT INTO `CATEGORIA` (`ID`, `NOME`, `TIPO`) VALUES (1, 'Aula', 'G'), (2, 'Faculdade', 'D');

INSERT INTO `CARTAO` (`ID`, `BANDEIRA`, `LIMITE`, `NOME`, `NUMERO`) VALUES (1, 'Mastercard', '5000', 'Nubank', '5173863405996183');

INSERT INTO `DESPESA` (`ID`, `DATA`, `DESCRICAO`, `VALOR`, `CARTAO_ID`, `CATEGORIA_ID`) VALUES (1, '2022-06-08', 'Aulas', '1000', '1', '2');


INSERT INTO `GANHO` (`ID`, `DATA`, `DESCRICAO`, `VALOR`, `CATEGORIA_ID`) VALUES (1, '2022-06-09', 'Profissão', 1000, 1);

INSERT INTO `USUARIO` (`ID`, `CPF`, `EMAIL`, `FOTO`, `NOME`, `SENHA`) VALUES (1, '51027504027', 'usuario@gmail.com', '', 'Usuário', '$2a$10$0Rue5UW4z4Vpz5oLeCfU1.GsQ541PrCCsz/2oOA.bCgsxoP7wnfnC');





INSERT INTO `CATEGORIA` (`ID`, `NOME`, `TIPO`) VALUES (1, 'Aula', 'G'), (2, 'Faculdade', 'D');

INSERT INTO `CARTAO` (`ID`, `BANDEIRA`, `LIMITE`, `NOME`, `NUMERO`) VALUES (1, 'Mastercard', '5000', 'Nubank', '5173863405996183');

INSERT INTO `DESPESA` (`ID`, `DATA`, `DESCRICAO`, `VALOR`, `CARTAO_ID`, `CATEGORIA_ID`) VALUES (1, '2022-06-08', 'Aulas', '1000', '1', '2');


INSERT INTO `GANHO` (`ID`, `DATA`, `DESCRICAO`, `VALOR`, `CATEGORIA_ID`) VALUES (1, '2022-06-09', 'Profissão', 1000, 1);




